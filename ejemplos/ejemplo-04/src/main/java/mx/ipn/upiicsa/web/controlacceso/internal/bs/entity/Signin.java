package mx.ipn.upiicsa.web.controlacceso.internal.bs.entity;

import lombok.*;
import java.time.LocalDate;

@Builder
@Data // Esto incluye @Getter, @Setter y otros
@AllArgsConstructor
@NoArgsConstructor
public class Signin {
    private Integer idGenero;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private LocalDate fechaNacimiento;
    private String login;
    private String password;

    // AGREGA ESTA LÍNEA
    private Integer idRol;
}