package mx.ipn.upiicsa.web.controlacceso.external.rest.dto;

import lombok.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DisponibilidadDto {
    private Integer idSucursal;
    private String nombreSucursal;
    private Double latitud;
    private Double longitud;
    private List<String> horariosDisponibles; // Ejemplo: ["09:00", "10:00", ...]
}