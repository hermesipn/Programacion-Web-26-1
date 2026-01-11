package mx.ipn.upiicsa.web.controlacceso.internal.bs.entity;

import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cita {
    private Integer id;
    private Integer idPersona;
    private Integer idServicio;
    private Integer idSucursal;
    private Integer idEmpleado;
    private LocalDateTime fechaHora;
}