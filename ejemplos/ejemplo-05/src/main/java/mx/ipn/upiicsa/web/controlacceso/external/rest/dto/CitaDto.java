package mx.ipn.upiicsa.web.controlacceso.external.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.time.LocalDateTime;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Schema(name = "CitaDto", description = "Datos necesarios para agendar una nueva cita")
public class CitaDto {
    @JsonProperty
    private Integer idPersona;

    @JsonProperty
    private Integer idServicio;

    @JsonProperty
    private Integer idSucursal;

    @JsonProperty
    private Integer idEmpleado;

    @JsonProperty
    @Schema(description = "Fecha y hora de la cita (ISO format)")
    private LocalDateTime fechaHora;
}