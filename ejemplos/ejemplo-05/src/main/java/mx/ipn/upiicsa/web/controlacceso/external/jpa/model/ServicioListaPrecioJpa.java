package mx.ipn.upiicsa.web.controlacceso.external.jpa.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "tci02_servicio_lista_precio")
public class ServicioListaPrecioJpa {
    @EmbeddedId
    private ServicioListaPrecioKey id;

    @Column(name = "nu_precio")
    private Integer precio;

    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ServicioListaPrecioKey implements java.io.Serializable {
        @Column(name = "fk_id_servicio")
        private Integer idServicio;
        @Column(name = "fk_id_lista_precio")
        private Integer idListaPrecio;
    }
}
