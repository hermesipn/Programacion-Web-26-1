package mx.ipn.upiicsa.web.controlacceso.external.jpa.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "tce08_horario")
public class HorarioJpa {
    @Id
    @Column(name = "id_horario")
    private Integer id;

    @Column(name = "fk_id_sucursal")
    private Integer idSucursal;

    @Column(name = "fk_id_dia")
    private Integer idDia;

    @Column(name = "tm_inicio")
    private java.time.LocalTime horaInicio;

    @Column(name = "tm_fin")
    private java.time.LocalTime horaFin;

    @ManyToOne
    @JoinColumn(name = "fk_id_sucursal", insertable = false, updatable = false)
    private SucursalJpa sucursal;

    @ManyToOne
    @JoinColumn(name = "fk_id_dia", insertable = false, updatable = false)
    private DiaLaboralJpa diaLaboral;
}
