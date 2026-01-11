package mx.ipn.upiicsa.web.controlacceso.external.jpa.model;

import jakarta.persistence.*;
import lombok.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "tce02_sucursal")
public class SucursalJpa {
    @Id
    @Column(name = "id_sucursal")
    private Integer id;

    @Column(name = "fk_id_establecimiento")
    private Integer idEstablecimiento;

    @Column(name = "tx_nombre")
    private String nombre;

    @Column(name = "gm_ubicacion")
    private byte[] ubicacion;

    @ManyToOne
    @JoinColumn(name = "fk_id_establecimiento", insertable = false, updatable = false)
    private EstablecimientoJpa establecimiento;

}
