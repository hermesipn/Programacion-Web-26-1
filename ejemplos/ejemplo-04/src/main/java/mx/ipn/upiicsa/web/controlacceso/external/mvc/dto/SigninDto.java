package mx.ipn.upiicsa.web.controlacceso.external.mvc.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.model.PersonaJpa;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.model.UsuarioJpa;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SigninDto {

    @NotNull(message = "Favor de proporcionar el genero")
    private Integer idGenero;

    @NotEmpty(message = "Favor de proporcionar el nombre")
    private String nombre;

    @NotEmpty(message = "Favor de proporcionar el primer apellido")
    private String primerApellido;

    @NotEmpty(message = "Favor de proporcionar el segundo apellido")
    private String segundoApellido;

    @NotNull(message = "Favor de proporcionar la fecha de nacimiento")
    @DateTimeFormat(pattern = "yyyy-MM-dd") // Ajustado para compatibilidad con navegadores
    private LocalDate fechaNacimiento;

    @NotEmpty(message = "Favor de proporcionar el correo electrónico")
    @Email(message = "Formato de correo incorrecto")
    private String login;

    @NotEmpty(message = "Favor de proporcionar la contraseña")
    private String password;

    /**
     * Convierte el DTO a una entidad PersonaJpa que incluye un UsuarioJpa.
     * Esto permite al controlador asignar el Rol 3 fácilmente.
     */
    public PersonaJpa toEntity() {
        // 1. Creamos el Usuario (Hijo)
        UsuarioJpa usuario = new UsuarioJpa();
        usuario.setLogin(this.login);
        usuario.setPassword(this.password);
        usuario.setActivo(true);
        // El idRol se asignará en el Controller

        // 2. Creamos la Persona (Padre)
        PersonaJpa persona = new PersonaJpa();
        persona.setNombre(this.nombre);
        persona.setPrimerApellido(this.primerApellido);
        persona.setSegundoApellido(this.segundoApellido);
        persona.setIdGenero(this.idGenero);
        persona.setFechaNacimiento(this.fechaNacimiento);

        // 3. Establecemos la relación bidireccional
        persona.setUsuario(usuario);
        usuario.setPersona(persona);

        return persona;
    }
}