package mx.ipn.upiicsa.web.controlacceso.external.jpa.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "tci03_lista_precio")
public class ListaPrecioJpa {
    @Id
    @Column(name = "id_lista_precio")
    private Integer id;
    @Column(name = "fk_id_estado")
    private Integer idEstado;
    @Column(name = "tx_nombre")
    private String nombre;
    @Column(name = "fh_inicio")
    private java.time.LocalDateTime fechaInicio;
    @Column(name = "fh_fin")
    private java.time.LocalDateTime fechaFin;

    @ManyToOne
    @JoinColumn(name = "fk_id_estado", insertable = false, updatable = false)
    private EstadoListaPrecioJpa estado;
}