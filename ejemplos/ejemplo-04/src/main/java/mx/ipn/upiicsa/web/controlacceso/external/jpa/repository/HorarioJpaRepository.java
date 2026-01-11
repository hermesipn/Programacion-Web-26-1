package mx.ipn.upiicsa.web.controlacceso.external.jpa.repository;

import mx.ipn.upiicsa.web.controlacceso.external.jpa.model.HorarioJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HorarioJpaRepository extends JpaRepository<HorarioJpa, Integer> {
    // Busca horarios por sucursal y día de la semana (1-7)
    List<HorarioJpa> findByIdSucursalAndIdDia(Integer idSucursal, Integer idDia);
}