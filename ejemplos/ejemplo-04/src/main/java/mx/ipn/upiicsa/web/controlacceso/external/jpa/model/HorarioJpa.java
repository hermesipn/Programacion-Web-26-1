package mx.ipn.upiicsa.web.controlacceso.external.jpa.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tce08_horario")
public class HorarioJpa {
    @Id
    @SequenceGenerator(name = "tce08_horario_id_horario_seq", sequenceName = "tce08_horario_id_horario_seq", allocationSize = 1)
    @GeneratedValue(generator = "tce08_horario_id_horario_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_horario")
    private Integer id;

    @Column(name = "fk_id_sucursal")
    private Integer idSucursal;

    @Column(name = "fk_id_dia")
    private Integer idDia;

    @Column(name = "tm_inicio")
    private LocalTime inicio;

    @Column(name = "tm_fin")
    private LocalTime fin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_dia", insertable = false, updatable = false)
    private DiaLaboralJpa dia;
}