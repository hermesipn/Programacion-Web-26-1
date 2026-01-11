package mx.ipn.upiicsa.web.controlacceso.external.jpa.dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.model.BloqueCitaJpa;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.model.CitaJpa;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.repository.BloqueCitaJpaRepository;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.repository.CitaJpaRepository;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.repository.ServicioJpaRepository;
import mx.ipn.upiicsa.web.controlacceso.internal.bs.entity.Cita;
import mx.ipn.upiicsa.web.controlacceso.internal.output.CitaRepository;
import java.time.LocalDateTime;

@ApplicationScoped
public class CitaDao implements CitaRepository {

    @Inject CitaJpaRepository citaRepo;
    @Inject BloqueCitaJpaRepository bloqueRepo;
    @Inject ServicioJpaRepository servicioRepo;

    @Override
    public Integer guardarCita(Cita entity) {
        CitaJpa jpa = CitaJpa.builder()
                .idPersona(entity.getIdPersona())
                .idServicio(entity.getIdServicio())
                .idSucursal(entity.getIdSucursal())
                .idEmpleado(entity.getIdEmpleado())
                .idListaPrecio(1) // Referencia a tci03_lista_precio
                .build();
        citaRepo.save(jpa); // tci05_cita
        return jpa.getId();
    }

    @Override
    public void guardarBloqueo(Integer idSucursal, Integer idCita, LocalDateTime inicio, LocalDateTime fin) {
        BloqueCitaJpa bloque = BloqueCitaJpa.builder()
                .id(new BloqueCitaJpa.BloqueCitaKey(idSucursal, idCita))
                .fechaInicio(inicio)
                .fechaFin(fin)
                .build();
        bloqueRepo.save(bloque); // tce07_bloque_cita
    }

    @Override
    public Integer obtenerDuracionServicio(Integer idServicio) {
        return servicioRepo.findById(idServicio)
                .map(s -> s.getDuracion()) // Columna nu_duracion en cci01_servicio
                .orElse(30);
    }
}