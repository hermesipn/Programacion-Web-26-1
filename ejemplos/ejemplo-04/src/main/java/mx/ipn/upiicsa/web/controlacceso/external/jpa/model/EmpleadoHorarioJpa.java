package mx.ipn.upiicsa.web.controlacceso.external.jpa.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tce06_empleado_horario")
@IdClass(EmpleadoHorarioId.class)
public class EmpleadoHorarioJpa {

    @Id
    @Column(name = "fk_id_horario")
    private Integer idHorario;

    @Id
    @Column(name = "fk_id_persona")
    private Integer idPersona;

    // Relación con el Horario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_horario", insertable = false, updatable = false)
    private HorarioJpa horario;

    // Relación con el Empleado (que hereda de Persona)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_persona", insertable = false, updatable = false)
    private EmpleadoJpa empleado;
}