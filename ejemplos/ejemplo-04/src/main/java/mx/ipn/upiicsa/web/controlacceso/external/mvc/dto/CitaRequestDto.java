package mx.ipn.upiicsa.web.controlacceso.external.mvc.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CitaRequestDto {

    @NotNull(message = "Debe seleccionar una sucursal")
    private Integer idSucursal;

    @NotNull(message = "Debe seleccionar un empleado")
    private Integer idEmpleado;

    @NotNull(message = "Debe seleccionar un servicio")
    private Integer idServicio;

    @NotNull(message = "Debe seleccionar una lista de precios")
    private Integer idListaPrecio;

    @NotNull(message = "La fecha es obligatoria")
    @FutureOrPresent(message = "La fecha no puede ser en el pasado")
    private LocalDate fecha;

    @NotNull(message = "La hora es obligatoria")
    private LocalTime hora;

    // Campos opcionales para capturar coordenadas si deseas usarlas en la vista
    private Double latitud;
    private Double longitud;
}