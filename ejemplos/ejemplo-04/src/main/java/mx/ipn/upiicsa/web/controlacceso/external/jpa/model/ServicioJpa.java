package mx.ipn.upiicsa.web.controlacceso.external.jpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cci01_servicio")
public class ServicioJpa {
    @Id
    @Column(name = "id_servicio")
    private Integer id;

    @Column(name = "tx_nombre")
    private String nombre;

    @Column(name = "tx_descripcion")
    private String descripcion;

    @Column(name = "st_activo")
    private Integer activo;

    @Column(name = "nu_duracion")
    private Integer duracion;
}