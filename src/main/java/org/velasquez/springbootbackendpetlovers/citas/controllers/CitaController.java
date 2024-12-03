package org.velasquez.springbootbackendpetlovers.citas.controllers;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.velasquez.springbootbackendpetlovers.autenticacion.models.repositories.UsuarioRepository;
import org.velasquez.springbootbackendpetlovers.autenticacion.models.services.IUsuarioService;
import org.velasquez.springbootbackendpetlovers.citas.models.entity.Cita;
import org.velasquez.springbootbackendpetlovers.citas.models.repositories.CitaRepository;
import org.velasquez.springbootbackendpetlovers.citas.models.services.ICitaService;
import org.velasquez.springbootbackendpetlovers.citas.utilities.CitaDetailsRequest;
import org.velasquez.springbootbackendpetlovers.citas.utilities.CitaRequest;
import org.velasquez.springbootbackendpetlovers.clientes.controllers.ClienteController;
import org.velasquez.springbootbackendpetlovers.clientes.models.entity.Cliente;
import org.velasquez.springbootbackendpetlovers.clientes.models.entity.Mascota;
import org.velasquez.springbootbackendpetlovers.clientes.models.repositories.ClienteRepository;
import org.velasquez.springbootbackendpetlovers.clientes.models.services.IClienteService;
import org.velasquez.springbootbackendpetlovers.clientes.models.services.IMascotaService;
import org.velasquez.springbootbackendpetlovers.informacion.models.entity.Servicio;
import org.velasquez.springbootbackendpetlovers.informacion.models.services.IServicioService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/apiCita")
public class CitaController {
    @Autowired
    private ICitaService citaService;
    @Autowired
    private IUsuarioService usuarioService;
    @Autowired
    private IClienteService clienteService;
    @Autowired
    private IMascotaService mascotaService;
    @Autowired
    private IServicioService servicioService;

    private final Logger log = LoggerFactory.getLogger(CitaController.class);

    @GetMapping("/citas")
    public List<CitaRequest> totalCitas() {
        return citaService.findAll();
    }

    @GetMapping("/citas/{id}")
    public List<CitaRequest> totalCitasByClient(@PathVariable Long id) {
        Cliente cliente = clienteService.findById(id);
        return citaService.findByCliente(cliente);
    }

    @DeleteMapping("/citas/{id}")
    public ResponseEntity<?> deleteCita(@PathVariable Long id) {
        Cita cita = null;
        Map<String, Object> response = new HashMap<>();
        try {
            cita = citaService.findById(id);
            citaService.delete(cita);
        }catch (DataAccessException e){
            response.put("mensaje","Error al eliminar datos de la cita en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (cita == null) {
            response.put("mensaje", "La cita ID:".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }
        response.put("mensaje", "Los datos de la cita han sido eliminados con exito");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/cita")
    public ResponseEntity<?> createCita(@Valid @RequestBody CitaDetailsRequest citaDetailsRequest, BindingResult result) {
        Cliente cliente = clienteService.findById(citaDetailsRequest.getIdCliente());
        Mascota mascota = mascotaService.findById(citaDetailsRequest.getIdMascota());
        Servicio servicio = servicioService.findById(citaDetailsRequest.getIdServicio());
        Cita citanew = new Cita();

        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' "+err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try{
            citanew.setCliente(cliente);
            citanew.setMascota(mascota);
            citanew.setServicio(servicio);
            citanew.setFechaCita(citaDetailsRequest.getFechaCita());
            citanew.setEstado(citaDetailsRequest.getEstado());
            citanew.setDetallesVisita(citaDetailsRequest.getDetallesVisita());
            citanew.setMotivo(citaDetailsRequest.getMotivo());
            citanew = citaService.save(citanew);
        }catch (DataAccessException e){

        }
        response.put("mensaje", "La cita ha sido creado con exito");
        response.put("cita", citanew);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(error -> {
            errors.put(error.getField(), "El campo " + error.getField() + " " + error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
