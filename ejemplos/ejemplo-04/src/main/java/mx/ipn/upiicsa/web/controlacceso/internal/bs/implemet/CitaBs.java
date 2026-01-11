package mx.ipn.upiicsa.web.controlacceso.internal.bs.implemet;

import lombok.extern.slf4j.Slf4j;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.model.BloqueCitaJpa;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.model.CitaJpa;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.model.HorarioJpa;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.repository.BloqueCitaJpaRepository;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.repository.CitaJpaRepository;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.repository.HorarioJpaRepository;
import mx.ipn.upiicsa.web.controlacceso.external.mvc.dto.CitaRequestDto;
import mx.ipn.upiicsa.web.controlacceso.internal.bs.entity.Persona;
import mx.ipn.upiicsa.web.controlacceso.internal.input.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CitaBs implements CitaService {

    @Autowired
    private HorarioJpaRepository horarioRepo;

    @Autowired
    private BloqueCitaJpaRepository bloqueRepo;

    @Autowired
    private CitaJpaRepository citaRepo;

    /**
     * Calcula las horas disponibles cruzando el horario de la sucursal
     * con los bloques ya ocupados en la tabla tce07_bloque_cita.
     */
    @Override
    public List<LocalTime> obtenerHorasDisponibles(Integer idSucursal, Integer idEmpleado, LocalDate fecha) {
        log.info("Consultando disponibilidad para sucursal: {}, empleado: {}, fecha: {}", idSucursal, idEmpleado, fecha);

        // 1. Obtener el día de la semana (Postgres/Java: 1=Lunes, 7=Domingo)
        int diaSemana = fecha.getDayOfWeek().getValue();

        // 2. Buscar horarios base definidos para esa sucursal en ese día
        List<HorarioJpa> horariosBase = horarioRepo.findByIdSucursalAndIdDia(idSucursal, diaSemana);

        List<LocalTime> horasDisponibles = new ArrayList<>();

        for (HorarioJpa horario : horariosBase) {
            LocalTime inicioTurno = horario.getInicio();
            LocalTime finTurno = horario.getFin();

            // Iterar en bloques de 60 minutos
            LocalTime auxiliar = inicioTurno;
            while (auxiliar.plusMinutes(60).isBefore(finTurno) || auxiliar.plusMinutes(60).equals(finTurno)) {
                LocalDateTime tiempoInicio = LocalDateTime.of(fecha, auxiliar);

                // 3. Verificar si el empleado tiene un conflicto en ese horario
                boolean ocupado = bloqueRepo.existeConflicto(idEmpleado, idSucursal, tiempoInicio);

                if (!ocupado) {
                    horasDisponibles.add(auxiliar);
                }
                auxiliar = auxiliar.plusMinutes(60);
            }
        }

        return horasDisponibles;
    }

    /**
     * Proceso transaccional para registrar la cita y bloquear el horario.
     * Utiliza el ID de la persona que viene de la sesión.
     */
    @Override
    @Transactional
    public void agendarCita(Persona persona, CitaRequestDto dto) {
        log.info("Agendando cita para persona ID: {}", persona.getId());

        // 1. Crear la cabecera de la cita (tci05_cita)
        CitaJpa cita = CitaJpa.builder()
                .idPersona(persona.getId()) // ID de la sesión del LoginController
                .idEmpleado(dto.getIdEmpleado())
                .idServicio(dto.getIdServicio())
                .idSucursal(dto.getIdSucursal())
                .idListaPrecio(dto.getIdListaPrecio())
                .build();

        CitaJpa citaGuardada = citaRepo.save(cita);
        log.info("Cita generada con ID: {}", citaGuardada.getId());

        // 2. Crear el registro de ocupación (tce07_bloque_cita)
        LocalDateTime inicio = LocalDateTime.of(dto.getFecha(), dto.getHora());

        BloqueCitaJpa bloque = BloqueCitaJpa.builder()
                .idSucursal(dto.getIdSucursal())
                .idCita(citaGuardada.getId())
                .fechaInicio(inicio)
                .fechaFin(inicio.plusMinutes(60)) // Bloque de 1 hora
                .build();

        bloqueRepo.save(bloque);
        log.info("Bloque de tiempo reservado exitosamente");
    }
}