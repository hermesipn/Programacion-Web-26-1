package mx.ipn.upiicsa.web.controlacceso.external.jpa.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tci02_servicio_lista_precio")
@IdClass(ServicioListaPrecioId.class) // Clase de ID compuesto
public class ServicioListaPrecioJpa {
    @Id @Column(name = "fk_id_servicio")
    private Integer idServicio;

    @Id @Column(name = "fk_id_lista_precio")
    private Integer idListaPrecio;

    @Column(name = "nu_precio")
    private Integer precio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_servicio", insertable = false, updatable = false)
    private ServicioJpa servicio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_lista_precio", insertable = false, updatable = false)
    private ListaPrecioJpa listaPrecio;
}
