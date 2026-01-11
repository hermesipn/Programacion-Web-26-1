package mx.ipn.upiicsa.web.controlacceso.external.jpa.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Builder @AllArgsConstructor @NoArgsConstructor @Getter @Setter
@Entity @Table(name = "tce07_bloque_cita")
@IdClass(BloqueCitaId.class)
public class BloqueCitaJpa {
    @Id
    @Column(name = "fk_id_sucursal")
    private Integer idSucursal;

    @Id
    @Column(name = "fk_id_cita")
    private Integer idCita;

    @Id
    @Column(name = "fh_inicio")
    private LocalDateTime fechaInicio;

    @Column(name = "fh_fin")
    private LocalDateTime fechaFin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_cita", insertable = false, updatable = false)
    private CitaJpa cita;
}