package mx.ipn.upiicsa.web.controlacceso.internal.output;

import mx.ipn.upiicsa.web.controlacceso.external.jpa.model.HorarioJpa;
import java.time.LocalDateTime;
import java.util.List;

public interface CitaRepository {
    List<HorarioJpa> findHorariosBySucursal(Integer idSucursal, Integer diaSemana);
    boolean existsConflict(Integer idEmpleado, Integer idSucursal, LocalDateTime inicio);
    void saveFullCita(Integer idPersona, Integer idEmpleado, Integer idServicio, Integer idSucursal, Integer idLista, LocalDateTime inicio);
}