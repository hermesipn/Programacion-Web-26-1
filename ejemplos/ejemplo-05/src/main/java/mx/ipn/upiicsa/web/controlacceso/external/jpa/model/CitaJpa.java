package mx.ipn.upiicsa.web.controlacceso.external.jpa.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "tci05_cita")
public class CitaJpa {
    @Id
    @Column(name = "id_cita")
    private Integer id;

    @Column(name = "fk_id_persona")
    private Integer idPersona; // FK al módulo Control Acceso

    @Column(name = "fk_id_servicio")
    private Integer idServicio;

    @Column(name = "fk_id_lista_precio")
    private Integer idListaPrecio;

    @Column(name = "fk_id_sucursal")
    private Integer idSucursal; // FK al módulo Establecimiento

    @Column(name = "fk_id_empleado")
    private Integer idEmpleado; // FK al módulo Establecimiento

    // Relaciones JPA
    @ManyToOne
    @JoinColumn(name = "fk_id_servicio", insertable = false, updatable = false)
    private ServicioJpa servicio;

    @ManyToOne
    @JoinColumn(name = "fk_id_lista_precio", insertable = false, updatable = false)
    private ListaPrecioJpa listaPrecio;

    @ManyToOne
    @JoinColumn(name = "fk_id_sucursal", insertable = false, updatable = false)
    private SucursalJpa sucursal;

    @ManyToOne
    @JoinColumn(name = "fk_id_empleado", insertable = false, updatable = false)
    private EmpleadoJpa empleado;

    @ManyToOne
    @JoinColumn(name = "fk_id_persona", insertable = false, updatable = false)
    private PersonaJpa persona;
}
