package mx.ipn.upiicsa.web.controlacceso.external.jpa.repository;

import mx.ipn.upiicsa.web.controlacceso.external.jpa.model.ServicioListaPrecioJpa;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.model.ServicioListaPrecioJpa.ServicioListaPrecioKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicioListaPrecioJpaRepository extends JpaRepository<ServicioListaPrecioJpa, ServicioListaPrecioKey> {
}
