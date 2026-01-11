package mx.ipn.upiicsa.web.controlacceso.external.rest.controller;

import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import mx.ipn.upiicsa.web.controlacceso.external.rest.dto.CitaDto;
import mx.ipn.upiicsa.web.controlacceso.internal.bs.input.CitaService;

@Path("/citas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CitaController {

    @Inject CitaService citaService;

    @POST
    public Response agendar(CitaDto dto) {
        Either<Integer, Boolean> result = citaService.agendarCita(dto);
        return result.isRight()
                ? Response.status(Response.Status.CREATED).build()
                : Response.status(Response.Status.BAD_REQUEST).entity(result.getLeft()).build();
    }
}