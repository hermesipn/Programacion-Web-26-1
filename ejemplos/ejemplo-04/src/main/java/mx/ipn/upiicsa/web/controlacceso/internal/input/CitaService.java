package mx.ipn.upiicsa.web.controlacceso.internal.input;

import mx.ipn.upiicsa.web.controlacceso.external.mvc.dto.CitaRequestDto;
import mx.ipn.upiicsa.web.controlacceso.internal.bs.entity.Persona;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface CitaService {
    List<LocalTime> obtenerHorasDisponibles(Integer idSucursal, Integer idEmpleado, LocalDate fecha);
    void agendarCita(Persona persona, CitaRequestDto dto);
}