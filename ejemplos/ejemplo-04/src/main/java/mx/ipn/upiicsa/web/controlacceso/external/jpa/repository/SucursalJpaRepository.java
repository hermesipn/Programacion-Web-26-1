package mx.ipn.upiicsa.web.controlacceso.external.jpa.repository;

import mx.ipn.upiicsa.web.controlacceso.external.jpa.model.SucursalJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SucursalJpaRepository extends JpaRepository<SucursalJpa, Integer> {
    // Aquí el ORM hereda automáticamente el método findAll()
}