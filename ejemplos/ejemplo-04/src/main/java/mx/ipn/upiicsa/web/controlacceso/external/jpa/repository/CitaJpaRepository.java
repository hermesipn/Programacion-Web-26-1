package mx.ipn.upiicsa.web.controlacceso.external.jpa.repository;

import mx.ipn.upiicsa.web.controlacceso.external.jpa.model.CitaJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CitaJpaRepository extends JpaRepository<CitaJpa, Integer> {
    @Query("SELECT c FROM CitaJpa c WHERE c.cliente.id = :idPersona")
    List<CitaJpa> buscarPorPersonaId(@Param("idPersona") Integer idPersona);
}