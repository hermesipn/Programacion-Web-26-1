package mx.ipn.upiicsa.web.controlacceso.external.jpa.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "tce01_establecimiento")
public class EstablecimientoJpa {
    @Id
    @Column(name = "id_establecimiento")
    private Integer id;

    @Column(name = "tx_nombre")
    private String nombre;

}