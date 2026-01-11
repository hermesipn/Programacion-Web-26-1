package mx.ipn.upiicsa.web.controlacceso.external.rest.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import mx.ipn.upiicsa.web.controlacceso.internal.bs.implemet.SucursalBs;

@Path("/sucursales")
@Produces(MediaType.APPLICATION_JSON)
public class SucursalController {

    @Inject
    SucursalBs sucursalBs;

    @GET
    @Path("/")
    public Response consultarDisponibilidad() {
        return Response.ok(sucursalBs.obtenerDisponibilidadCitas()).build();
    }
}