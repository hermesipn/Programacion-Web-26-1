package mx.ipn.upiicsa.web.controlacceso.external.jpa.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "tci01_estado_lista_precio")
public class EstadoListaPrecioJpa {
    @Id
    @Column(name = "id_estado")
    private Integer id;
    @Column(name = "tx_nombre")
    private String nombre;
}