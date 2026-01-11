package mx.ipn.upiicsa.web.controlacceso.external.jpa.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tci05_cita")
public class CitaJpa {
    @Id
    @SequenceGenerator(name = "tci05_cita_id_cita_seq", sequenceName = "tci05_cita_id_cita_seq", allocationSize = 1)
    @GeneratedValue(generator = "tci05_cita_id_cita_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_cita")
    private Integer id;

    @Column(name = "fk_id_persona")
    private Integer idPersona;

    @Column(name = "fk_id_servicio")
    private Integer idServicio;

    @Column(name = "fk_id_lista_precio")
    private Integer idListaPrecio;

    @Column(name = "fk_id_sucursal")
    private Integer idSucursal;

    @Column(name = "fk_id_empleado")
    private Integer idEmpleado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_persona", insertable = false, updatable = false)
    private PersonaJpa cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_empleado", insertable = false, updatable = false)
    private EmpleadoJpa empleado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_sucursal", insertable = false, updatable = false)
    private SucursalJpa sucursal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "fk_id_servicio", referencedColumnName = "fk_id_servicio", insertable = false, updatable = false),
            @JoinColumn(name = "fk_id_lista_precio", referencedColumnName = "fk_id_lista_precio", insertable = false, updatable = false)
    })
    private ServicioListaPrecioJpa detallePrecio;
}