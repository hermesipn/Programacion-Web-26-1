package mx.ipn.upiicsa.web.controlacceso.external.jpa.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "tce06_empleado_horario")
public class EmpleadoHorarioJpa {

    @EmbeddedId
    private EmpleadoHorarioKey id;

    @ManyToOne
    @MapsId("idHorario")
    @JoinColumn(name = "fk_id_horario")
    private HorarioJpa horario;

    // Asumimos que mapeas contra la tabla Empleado, aunque la columna se llame fk_id_persona
    @ManyToOne
    @MapsId("idPersona")
    @JoinColumn(name = "fk_id_persona", referencedColumnName = "id_empleado")
    private EmpleadoJpa empleado;

    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EmpleadoHorarioKey implements java.io.Serializable {
        @Column(name = "fk_id_horario")
        private Integer idHorario;
        @Column(name = "fk_id_persona")
        private Integer idPersona;
    }
}
