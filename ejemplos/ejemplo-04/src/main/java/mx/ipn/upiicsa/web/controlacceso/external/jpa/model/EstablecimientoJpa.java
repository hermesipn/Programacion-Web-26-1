package mx.ipn.upiicsa.web.controlacceso.external.jpa.model;

import jakarta.persistence.*;
import lombok.*;

@Builder @AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Entity @Table(name = "tce01_establecimiento")
public class EstablecimientoJpa {
    @Id
    @SequenceGenerator(name = "tce01_establecimiento_id_establecimiento_seq", sequenceName = "tce01_establecimiento_id_establecimiento_seq", allocationSize = 1)
    @GeneratedValue(generator = "tce01_establecimiento_id_establecimiento_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_establecimiento")
    private Integer id;

    @Column(name = "tx_nombre")
    private String nombre;
}