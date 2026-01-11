package mx.ipn.upiicsa.web.controlacceso.external.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BloqueCitaId implements Serializable {
    private Integer idSucursal; // Coincide con el nombre del atributo en BloqueCitaJpa
    private Integer idCita;
    private LocalDateTime fechaInicio;
}
