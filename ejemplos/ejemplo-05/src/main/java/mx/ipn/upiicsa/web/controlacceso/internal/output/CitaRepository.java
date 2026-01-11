package mx.ipn.upiicsa.web.controlacceso.internal.output; // Sin el .bs

import mx.ipn.upiicsa.web.controlacceso.internal.bs.entity.Cita;
import java.time.LocalDateTime;

public interface CitaRepository {
    Integer guardarCita(Cita cita);
    Integer obtenerDuracionServicio(Integer idServicio);
    void guardarBloqueo(Integer idSucursal, Integer idCita, LocalDateTime inicio, LocalDateTime fin);
}