package cl.praxis.ProyectoHospitalPostPandemia.controller;

import cl.praxis.ProyectoHospitalPostPandemia.ProyectoHospitalPostPandemiaApplication;
import cl.praxis.ProyectoHospitalPostPandemia.model.dto.Paciente;
import cl.praxis.ProyectoHospitalPostPandemia.model.service.PacienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {
    private static final Logger logger = LoggerFactory.getLogger(ProyectoHospitalPostPandemiaApplication.class);
    PacienteService service;

    public PacienteController(PacienteService service) {
        this.service = service;
    }

    @GetMapping()
    public String findAll(Model model) {
        logger.warn("Ejacutando findAll()");
        model.addAttribute("pacientes", service.findAll());
        return "pacienteList";
    }

    @GetMapping("/new")
    public String newPaciente(){
        logger.info("Ejecutando newPaciente de PacienteController");
        return "newpaciente";
    }

    @PostMapping("/new")
    public String createPaciente(@ModelAttribute Paciente paciente){
        logger.info("Ejecutando createPaciente de PacienteController");
        boolean result = service.create(paciente);
        return "redirect:/pacientes";
    }

    @GetMapping("/del/{id}")
    public String delete(@PathVariable("id") int id){
        logger.info("Ejecutando delete de PatientController");
        boolean result = service.delete(id);

        if (result){
            logger.info("Paciente eliminado correctamente");
        } else {
            logger.error("Error eliminando paciente");
        }
        return "redirect:/pacientes";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id,Model model){
        model.addAttribute("paciente", service.findOne(id));
        return "editarpaciente";
    }

    @PostMapping
    public String update(@ModelAttribute Paciente paciente){
        logger.info("Ejecutando update de PatientController");
        boolean result = service.update(paciente);
        return "redirect:/pacientes";
    }

    @GetMapping("/{id}")
    public String findOne(@PathVariable("id") int id, Model model){
        logger.info("Ejecutando findOne de PacienteController");
        model.addAttribute("paciente", service.findOne(id));
        return "detallepaciente";
    }
}
