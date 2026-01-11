package mx.ipn.upiicsa.web.controlacceso.external.jpa.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tci03_lista_precio")
public class ListaPrecioJpa {
    @Id
    @SequenceGenerator(name = "tci03_lista_precio_id_lista_precio_seq", sequenceName = "tci03_lista_precio_id_lista_precio_seq", allocationSize = 1)
    @GeneratedValue(generator = "tci03_lista_precio_id_lista_precio_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_lista_precio")
    private Integer id;

    @Column(name = "fk_id_estado")
    private Integer idEstado;

    @Column(name = "tx_nombre")
    private String nombre;

    @Column(name = "fh_inicio")
    private LocalDateTime fechaInicio;

    @Column(name = "fh_fin")
    private LocalDateTime fechaFin;
}
