package mx.ipn.upiicsa.web.controlacceso.internal.bs.implemet;

import io.vavr.control.Either;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import mx.ipn.upiicsa.web.controlacceso.external.rest.dto.CitaDto;
import mx.ipn.upiicsa.web.controlacceso.internal.bs.entity.Cita;
import mx.ipn.upiicsa.web.controlacceso.internal.bs.input.CitaService;
import mx.ipn.upiicsa.web.controlacceso.internal.output.CitaRepository;

@ApplicationScoped
public class CitaBs implements CitaService {

    @Inject CitaRepository repository;

    @Override
    @Transactional
    public Either<Integer, Boolean> agendarCita(CitaDto dto) {
        try {
            // 1. Mapear DTO a Entidad de Dominio
            Cita cita = Cita.builder()
                    .idPersona(dto.getIdPersona())
                    .idServicio(dto.getIdServicio())
                    .idSucursal(dto.getIdSucursal())
                    .idEmpleado(dto.getIdEmpleado())
                    .fechaHora(dto.getFechaHora())
                    .build();

            // 2. Persistir Cita y obtener su ID
            Integer idCita = repository.guardarCita(cita);

            // 3. Calcular tiempos y bloquear agenda
            Integer duracion = repository.obtenerDuracionServicio(dto.getIdServicio());
            repository.guardarBloqueo(
                    dto.getIdSucursal(),
                    idCita,
                    dto.getFechaHora(),
                    dto.getFechaHora().plusMinutes(duracion)
            );

            return Either.right(true);
        } catch (Exception e) {
            return Either.left(500);
        }
    }
}