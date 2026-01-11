package mx.ipn.upiicsa.web.controlacceso.external.jpa.model;

import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Point;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tce02_sucursal")
public class SucursalJpa {
    @Id
    @SequenceGenerator(name = "tce02_sucursal_id_sucursal_seq", sequenceName = "tce02_sucursal_id_sucursal_seq", allocationSize = 1)
    @GeneratedValue(generator = "tce02_sucursal_id_sucursal_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_sucursal")
    private Integer id;

    @Column(name = "fk_id_establecimiento")
    private Integer idEstablecimiento;

    @Column(name = "tx_nombre")
    private String nombre;

    @Column(name = "gm_ubicacion", columnDefinition = "geometry(Point, 4326)")
    private Point ubicacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_establecimiento", insertable = false, updatable = false)
    private EstablecimientoJpa establecimiento;
}
