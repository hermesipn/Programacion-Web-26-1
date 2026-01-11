package mx.ipn.upiicsa.web.controlacceso.external.jpa.repository;

import mx.ipn.upiicsa.web.controlacceso.external.jpa.model.BloqueCitaJpa;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.model.BloqueCitaJpa.BloqueCitaKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BloqueCitaJpaRepository extends JpaRepository<BloqueCitaJpa, BloqueCitaKey> {
}
