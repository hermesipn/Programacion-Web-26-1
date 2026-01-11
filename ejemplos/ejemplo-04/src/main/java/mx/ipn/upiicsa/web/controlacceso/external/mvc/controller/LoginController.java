package mx.ipn.upiicsa.web.controlacceso.external.mvc.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.repository.GeneroJpaRepository;
import mx.ipn.upiicsa.web.controlacceso.external.mvc.dto.SigninDto;
import mx.ipn.upiicsa.web.controlacceso.internal.bs.implemet.LoginBs;
import mx.ipn.upiicsa.web.controlacceso.external.mvc.dto.LoginDto;
import mx.ipn.upiicsa.web.controlacceso.internal.input.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        // Si ya existe la persona en sesión, mándalo directo a welcome
        if (session.getAttribute("persona") != null) {
            return "redirect:/citas/welcome";
        }

        model.addAttribute("loginDto", new LoginDto());
        return "index";
    }

    //@PostMapping("/")
    //public String login(@RequestParam String username, @RequestParam String password, Model model){
    //    System.out.println("USERNAME: " + username);
    //    System.out.println("PASSWORD: " + password);
    //    return "index";
    //}

    @PostMapping("/")
    public String login(@Valid @ModelAttribute LoginDto loginDto, BindingResult bindingResult, Model model, HttpSession session) {
        String resultado = null;
        var resultadoLogin = loginService.login(loginDto);
        if (resultadoLogin.isRight()) {
            var persona = resultadoLogin.get();
            log.info("LOGIN EXITOSO");
            log.info("Persona: {} {} {}", persona.getNombre(), persona.getPrimerApellido(), persona.getSegundoApellido());
            log.info("Usuario: {} {} {}", persona.getUsuario().getLogin(), persona.getUsuario().getPassword(), persona.getUsuario().getActivo());
            model.addAttribute("persona", persona);
            session.setAttribute("persona", persona);
            resultado = "welcome";
        } else {
            log.info("LOGIN NO ENCONTRADO: {}", resultadoLogin.getLeft());
            //ObjectError error = new ObjectError("peticion","Error, usuario y/o contraseña incorrectos");
            //bindingResult.addError(error);
            if (resultadoLogin.getLeft() == 1) {
                model.addAttribute("errorLogin", "Error, usuario y/o contraseña incorrectos");
            } else {
                model.addAttribute("errorLogin", "El usuario está inactivo");
            }
            resultado = "index";
        }
        return resultado;
    }

    @Autowired private GeneroJpaRepository generoRepo; // Necesitas crear esta interfaz

    @GetMapping("/signin")
    public String mostrarRegistro(Model model) {
        // Cargamos los géneros de la base de datos de forma dinámica
        model.addAttribute("generos", generoRepo.findAll());
        model.addAttribute("signinDto", new SigninDto());
        return "signin";
    }

    @PostMapping("/signin")
    public String signin(@Valid @ModelAttribute SigninDto signinDto, BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            // 1. Obtenemos la entidad JPA (donde podemos manipular el Rol)
            var personaJpa = signinDto.toEntity();

            // 2. Asignamos el Rol 3 al usuario interno
            if (personaJpa.getUsuario() != null) {
                personaJpa.getUsuario().setIdRol(3);
            }

            // 3. ¡AQUÍ ESTÁ EL TRUCO!
            // Convertimos el JPA de nuevo al objeto 'Signin' que el servicio espera.
            // Usaremos los datos que ya tiene personaJpa.
            mx.ipn.upiicsa.web.controlacceso.internal.bs.entity.Signin signinNegocio =
                    mx.ipn.upiicsa.web.controlacceso.internal.bs.entity.Signin.builder()
                            .idGenero(personaJpa.getIdGenero())
                            .nombre(personaJpa.getNombre())
                            .primerApellido(personaJpa.getPrimerApellido())
                            .segundoApellido(personaJpa.getSegundoApellido())
                            .fechaNacimiento(personaJpa.getFechaNacimiento())
                            .login(personaJpa.getUsuario().getLogin())
                            .password(personaJpa.getUsuario().getPassword())
                            .idRol(personaJpa.getUsuario().getIdRol()) // Aquí pasamos el 3
                            .build();

            // 4. Ahora enviamos el objeto correcto al servicio
            var signinResultado = loginService.signin(signinNegocio);

            if(signinResultado.isRight()) {
                model.addAttribute("signinSuccess", "Registro exitoso");
                model.addAttribute("loginDto", new LoginDto());
                return "index";
            } else {
                // Manejo de errores...
                model.addAttribute("generos", generoRepo.findAll());
                return "signin";
            }
        }
        model.addAttribute("generos", generoRepo.findAll());
        return "signin";
    }
    // Dentro de LoginController.java
    @GetMapping("/welcome")
    public String welcome(HttpSession session, Model model) {
        // Recuperamos la persona de la sesión para que la vista no falle
        var persona = session.getAttribute("persona");
        if (persona == null) {
            return "redirect:/"; // Si no hay sesión, al login
        }
        model.addAttribute("persona", persona);
        return "welcome"; // Retorna el template welcome.html
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Borra todos los datos de la sesión (incluyendo el objeto Persona)
        return "redirect:/";  // Te manda de nuevo al login
    }
}