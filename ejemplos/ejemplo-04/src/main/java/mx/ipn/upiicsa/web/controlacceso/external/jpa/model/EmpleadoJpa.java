package mx.ipn.upiicsa.web.controlacceso.external.jpa.model;

import jakarta.persistence.*;
import lombok.*;

@Builder @AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Entity @Table(name = "tce03_empleado")
public class EmpleadoJpa {
    @Id
    @Column(name = "id_empleado") // PK compartida con Persona
    private Integer id;

    @Column(name = "fk_id_sucursal")
    private Integer idSucursal;

    @OneToOne(fetch = FetchType.EAGER) // Esto asegura que la persona se cargue junto con el empleado
    @MapsId
    @JoinColumn(name = "id_empleado")
    private PersonaJpa persona;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_sucursal", insertable = false, updatable = false)
    private SucursalJpa sucursal;
}