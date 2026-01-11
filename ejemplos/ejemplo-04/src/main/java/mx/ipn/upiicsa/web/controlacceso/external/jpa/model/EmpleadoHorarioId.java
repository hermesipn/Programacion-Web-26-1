package mx.ipn.upiicsa.web.controlacceso.external.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoHorarioId implements Serializable {
    private Integer idHorario;
    private Integer idPersona; // En tu DDL es fk_id_persona la que apunta al empleado
}