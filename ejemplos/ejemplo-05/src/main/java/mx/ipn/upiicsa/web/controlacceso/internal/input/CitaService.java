package mx.ipn.upiicsa.web.controlacceso.internal.bs.input;

import io.vavr.control.Either;
import mx.ipn.upiicsa.web.controlacceso.external.rest.dto.CitaDto;

/**
 * Puerto de entrada para la gestión de citas siguiendo el patrón de LoginService
 */
public interface CitaService {
    /**
     * @return Either con código de error (Integer) o confirmación de éxito (Boolean)
     */
    Either<Integer, Boolean> agendarCita(CitaDto cita);
}