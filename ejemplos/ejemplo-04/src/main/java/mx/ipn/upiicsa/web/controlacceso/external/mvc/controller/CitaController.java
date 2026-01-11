package mx.ipn.upiicsa.web.controlacceso.external.mvc.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.model.CitaJpa;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.repository.CitaJpaRepository;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.repository.EmpleadoJpaRepository;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.repository.ServicioJpaRepository;
import mx.ipn.upiicsa.web.controlacceso.external.jpa.repository.SucursalJpaRepository;
import mx.ipn.upiicsa.web.controlacceso.external.mvc.dto.CitaRequestDto;
import mx.ipn.upiicsa.web.controlacceso.internal.bs.entity.Persona;
import mx.ipn.upiicsa.web.controlacceso.internal.input.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/citas")
public class CitaController {

    @Autowired private CitaService citaService;
    @Autowired private SucursalJpaRepository sucursalRepo;
    @Autowired private EmpleadoJpaRepository empleadoRepo;
    // 1. Inyectar el repositorio de servicios
    @Autowired private ServicioJpaRepository servicioRepo;

    /**
     * Carga los catálogos necesarios para los formularios de forma dinámica.
     */
    private void cargarCatalogos(Model model) {
        model.addAttribute("sucursales", sucursalRepo.findAll());
        model.addAttribute("empleados", empleadoRepo.findAll());

        // 2. Línea clave para que aparezcan los servicios en el HTML
        model.addAttribute("servicios", servicioRepo.findAll());
    }

    @GetMapping("/welcome")
    public String welcome(HttpSession session, Model model) {
        Persona persona = (Persona) session.getAttribute("persona");
        if (persona == null) return "redirect:/";
        model.addAttribute("persona", persona);
        return "welcome";
    }

    @GetMapping("/agendar")
    public String mostrarFormulario(Model model, HttpSession session) {
        Persona persona = (Persona) session.getAttribute("persona");
        if (persona == null) return "redirect:/";

        cargarCatalogos(model);
        model.addAttribute("citaRequest", new CitaRequestDto());
        return "agendar";
    }

    @PostMapping("/disponibilidad")
    public String consultarDisponibilidad(@ModelAttribute("citaRequest") CitaRequestDto dto,
                                          Model model,
                                          HttpSession session) {
        Persona persona = (Persona) session.getAttribute("persona");
        if (persona == null) return "redirect:/";

        List<LocalTime> horas = citaService.obtenerHorasDisponibles(
                dto.getIdSucursal(),
                dto.getIdEmpleado(),
                dto.getFecha()
        );

        cargarCatalogos(model); // Recargar para que los select no se vacíen
        model.addAttribute("horasDisponibles", horas);
        model.addAttribute("citaRequest", dto);
        return "agendar";
    }

    @PostMapping("/confirmar")
    public String confirmarCita(@Valid @ModelAttribute("citaRequest") CitaRequestDto dto,
                                BindingResult bindingResult,
                                HttpSession session,
                                Model model) {

        Persona persona = (Persona) session.getAttribute("persona");
        if (persona == null) return "redirect:/";

        if (bindingResult.hasErrors()) {
            cargarCatalogos(model);
            return "agendar";
        }

        try {
            // Valores por defecto si aún no tienes el catálogo de servicios dinámico
            if(dto.getIdServicio() == null) dto.setIdServicio(1);
            if(dto.getIdListaPrecio() == null) dto.setIdListaPrecio(1);

            citaService.agendarCita(persona, dto);
            return "redirect:/citas/welcome?exito=true";
        } catch (Exception e) {
            log.error("Error al agendar: {}", e.getMessage());
            cargarCatalogos(model);
            model.addAttribute("error", "Error interno al procesar la cita.");
            return "agendar";
        }
    }
    @Autowired private CitaJpaRepository citaRepo; // Inyecta el repositorio de citas

    @GetMapping("/mis-citas")
    public String verMisCitas(HttpSession session, Model model) {
        Persona persona = (Persona) session.getAttribute("persona");
        if (persona == null) return "redirect:/";

        // Cambia el método aquí:
        List<CitaJpa> misCitas = citaRepo.buscarPorPersonaId(persona.getId());

        model.addAttribute("persona", persona);
        model.addAttribute("citas", misCitas);
        return "mis-citas";
    }
}