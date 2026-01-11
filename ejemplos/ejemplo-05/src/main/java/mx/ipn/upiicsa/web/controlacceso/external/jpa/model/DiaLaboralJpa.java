package mx.ipn.upiicsa.web.controlacceso.external.jpa.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "tce04_dia_laboral")
public class DiaLaboralJpa {
    @Id
    @Column(name = "id_dia")
    private Integer id;

    @Column(name = "tx_nombre")
    private String nombre;

    @Column(name = "tx_descripcion")
    private String descripcion;

    @Column(name = "st_activo")
    private Integer activo; // En el diagrama dice int4, si fuera boolean cámbialo a Boolean
}
