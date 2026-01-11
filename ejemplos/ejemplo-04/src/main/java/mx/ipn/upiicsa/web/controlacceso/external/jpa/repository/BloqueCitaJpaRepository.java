package mx.ipn.upiicsa.web.controlacceso.external.jpa.repository;

import mx.ipn.upiicsa.web.controlacceso.external.jpa.model.BloqueCitaJpa;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.model.BloqueCitaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;

public interface BloqueCitaJpaRepository extends JpaRepository<BloqueCitaJpa, BloqueCitaId> {
    @Query("SELECT COUNT(b) > 0 FROM BloqueCitaJpa b " +
            "JOIN CitaJpa c ON b.idCita = c.id " +
            "WHERE c.idEmpleado = :idEmpleado AND c.idSucursal = :idSucursal " +
            "AND b.fechaInicio = :inicio")
    boolean existeConflicto(@Param("idEmpleado") Integer idEmpleado,
                            @Param("idSucursal") Integer idSucursal,
                            @Param("inicio") LocalDateTime inicio);
}