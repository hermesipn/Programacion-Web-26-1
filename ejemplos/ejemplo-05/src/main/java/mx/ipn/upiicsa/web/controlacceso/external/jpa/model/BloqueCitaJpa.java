package mx.ipn.upiicsa.web.controlacceso.external.jpa.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "tce07_bloque_cita")
public class BloqueCitaJpa {
    @EmbeddedId
    private BloqueCitaKey id;

    @Column(name = "fh_inicio")
    private java.time.LocalDateTime fechaInicio;

    @Column(name = "fh_fin")
    private java.time.LocalDateTime fechaFin;

    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BloqueCitaKey implements java.io.Serializable {
        @Column(name = "fk_id_sucursal")
        private Integer idSucursal;
        @Column(name = "fk_id_cita")
        private Integer idCita;
    }
}
