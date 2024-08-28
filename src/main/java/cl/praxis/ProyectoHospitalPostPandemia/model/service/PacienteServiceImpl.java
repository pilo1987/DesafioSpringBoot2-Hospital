package cl.praxis.ProyectoHospitalPostPandemia.model.service;

import cl.praxis.ProyectoHospitalPostPandemia.ProyectoHospitalPostPandemiaApplication;
import cl.praxis.ProyectoHospitalPostPandemia.model.dto.Paciente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PacienteServiceImpl implements PacienteService {

    private static final Logger logger = LoggerFactory.getLogger(ProyectoHospitalPostPandemiaApplication.class);
    private List<Paciente> pacienteList;

    public PacienteServiceImpl() {
        pacienteList = new ArrayList<>();

        pacienteList.add(new Paciente(0, "Juan Alberto", "Bustamante Hernandez", "Isapre Banmedica"));
        pacienteList.add(new Paciente(1, "Pedro Augusto", "Hevia Elgueta", "Isabre Consalud"));
        pacienteList.add(new Paciente(2, "Ana María", "González Pérez", "Isapre Colmena"));
        pacienteList.add(new Paciente(3, "Francisca Soledad", "Sarabia Colorado", "Isapre Cruz Blanca"));
        pacienteList.add(new Paciente(4, "Carlos Eduardo", "Maldonado Rivas", "Isapre Vida Tres"));
        pacienteList.add(new Paciente(5, "María José", "Fernández García", "Isapre Banmedica"));
    }

    public PacienteServiceImpl(List<Paciente> pacienteList) {
        this.pacienteList = pacienteList;
    }

    @Override
    public List<Paciente> findAll() {
        logger.info("Ejecutando findAll de PacienteServiceImpl - Listando todos los pacientes");
        return pacienteList;
    }

    @Override
    public Paciente findOne(int id) {
        logger.info("Ejecutando findOne de PacienteServiceImpl - Buscando paciente con ID: {}", id);
        return pacienteList.stream()
                .filter(paciente -> paciente.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean create(Paciente paciente) {
        paciente.setId(newId());
        pacienteList.add(paciente);
        logger.info("Creando paciente con ID: {}", paciente.getId());
        return true;
    }

    private int newId() {
        if (!pacienteList.isEmpty()) {
            Paciente p = pacienteList.stream().max(Comparator.comparing(Paciente::getId)).orElseThrow(NoSuchElementException::new);
            return p.getId() + 1;
        }
        return 1;
    }

    @Override
    public boolean update(Paciente p) {
        logger.info("Ejecutando update de PacienteServiceImpl - Actualizando paciente con ID: {}", p.getId());
        Paciente paciente = findOne(p.getId());
        paciente.setNombre(p.getNombre());
        paciente.setApellido(p.getApellido());
        paciente.setPrevision(p.getPrevision());
        return true;
    }

    @Override
    public boolean delete(int id) {
        logger.info("Ejecutando delete de PacienteServiceImpl - Eliminando paciente con ID: {}", id);
        Paciente paciente = findOne(id);
        if (paciente != null) {
            pacienteList.remove(paciente);

        }
        return true;
    }
}
