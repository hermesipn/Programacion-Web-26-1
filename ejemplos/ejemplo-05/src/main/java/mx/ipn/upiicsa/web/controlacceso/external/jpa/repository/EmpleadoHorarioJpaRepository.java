package mx.ipn.upiicsa.web.controlacceso.external.jpa.repository;

import mx.ipn.upiicsa.web.controlacceso.external.jpa.model.EmpleadoHorarioJpa;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.model.EmpleadoHorarioJpa.EmpleadoHorarioKey;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoHorarioJpaRepository extends JpaRepository<EmpleadoHorarioJpa, EmpleadoHorarioKey> {
}
