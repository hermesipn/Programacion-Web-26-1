package mx.ipn.upiicsa.web.controlacceso.external.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServicioListaPrecioId implements Serializable {
    private Integer idServicio;
    private Integer idListaPrecio;
}
