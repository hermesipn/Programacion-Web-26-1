package mx.ipn.upiicsa.web.controlacceso.external.jpa.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tce04_dia_laboral")
public class DiaLaboralJpa {
    @Id
    @SequenceGenerator(name = "tce04_dia_laboral_id_dia_seq", sequenceName = "tce04_dia_laboral_id_dia_seq", allocationSize = 1)
    @GeneratedValue(generator = "tce04_dia_laboral_id_dia_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_dia")
    private Integer id;

    @Column(name = "tx_nombre")
    private String nombre;

    @Column(name = "st_activo")
    private Integer activo;
}