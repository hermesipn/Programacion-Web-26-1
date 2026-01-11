package mx.ipn.upiicsa.web.controlacceso.external.jpa.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "tce03_empleado")
public class EmpleadoJpa {
    @Id
    @Column(name = "id_empleado")
    private Integer id; // Es FK a Persona y PK de Empleado

    @Column(name = "fk_id_sucursal")
    private Integer idSucursal;

    @ManyToOne
    @JoinColumn(name = "fk_id_sucursal", insertable = false, updatable = false)
    private SucursalJpa sucursal;


     @OneToOne
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_persona", insertable = false, updatable = false)
    private mx.ipn.upiicsa.web.controlacceso.external.jpa.model.PersonaJpa persona;
}
