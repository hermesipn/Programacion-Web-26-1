package mx.ipn.upiicsa.web.controlacceso.external.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Schema(name = "SucursalDto", description = "Información de la sucursal y su ubicación")
public class SucursalDto {
    @JsonProperty
    private Integer id;

    @JsonProperty
    private String nombre;

    @JsonProperty
    @Schema(description = "Latitud para el mapa")
    private Double latitud;

    @JsonProperty
    @Schema(description = "Longitud para el mapa")
    private Double longitud;
}