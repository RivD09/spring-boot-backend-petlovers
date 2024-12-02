package org.velasquez.springbootbackendpetlovers.clientes.controllers;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.velasquez.springbootbackendpetlovers.administracion.utilities.MascotaRequest;
import org.velasquez.springbootbackendpetlovers.autenticacion.models.entity.Usuario;
import org.velasquez.springbootbackendpetlovers.clientes.models.entity.Cliente;
import org.velasquez.springbootbackendpetlovers.clientes.models.entity.Mascota;
import org.velasquez.springbootbackendpetlovers.clientes.models.services.IClienteService;
import org.velasquez.springbootbackendpetlovers.clientes.models.services.IMascotaService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/apiCliente")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;
    @Autowired
    private IMascotaService mascotaService;

    private final Logger log = LoggerFactory.getLogger(ClienteController.class);

    @GetMapping("/clientes")
    public List<Cliente> indexUsuarios(){
        return clienteService.findAll();
    }

    @GetMapping("/mascotas")
    public List<Mascota> indexMascotas(){
        return mascotaService.findAll();
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<?> showCliente(@PathVariable Long id){
        Cliente cliente = null;
        Map<String, Object> response = new HashMap<>();
        try {
            cliente = clienteService.findById(id);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la consulta en la bd");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (cliente == null) {
            response.put("mensaje", "Cliente no encontrado | id: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
    }

    @GetMapping("/cliente/m/{id}")
    public List<Mascota> showMascotasCliente(@PathVariable Long id){
        return mascotaService.findAllByCliente(id);
    }

    @PostMapping("/mascota/{id}")
    public ResponseEntity<?> createMascota(@Valid @RequestBody Mascota mascota, BindingResult result, @PathVariable Long id){
        Mascota mascotanew = null;
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
            mascotanew = mascotaService.save(mascota,id);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar el registro en la base de datos");
            response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "La mascota ha sido creado con exito");
        response.put("mascota", mascotanew);

        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
    }

    @GetMapping("/mascota/{id}")
    public ResponseEntity<?> getMascota(@PathVariable Long id){
        Mascota mascota = null;
        Map<String, Object> response = new HashMap<>();
        try {
            mascota = mascotaService.findById(id);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la consulta en la bd");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (mascota == null) {
            response.put("mensaje", "Mascota no encontrada | id: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Mascota>(mascota, HttpStatus.OK);
    }

    @PutMapping("/updateMascota/{id}")
    public ResponseEntity<?> updateMascota(@Valid @RequestBody MascotaRequest mascota, BindingResult result, @PathVariable Long id){
        if (result.hasErrors()) {
            return validation(result);
        }
        Optional<MascotaRequest> mascotaRequestOptional = mascotaService.update(mascota, id);

        if (mascotaRequestOptional.isPresent()) {
            return ResponseEntity.ok(mascotaRequestOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/mascotas/{id}")
    public ResponseEntity<?> deleteMascota(@PathVariable Long id){
        Mascota mascota = null;
        Map<String, Object> response = new HashMap<>();
        try{
            mascota = mascotaService.findById(id);
            mascotaService.delete(id);
        }catch (DataAccessException e){
            response.put("mensaje","Error al eliminar datos de la mascota en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (mascota == null) {
            response.put("mensaje", "La mascota ID:".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }
        response.put("mensaje", "Los datos del usuario han sido eliminados con exito");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(error -> {
            errors.put(error.getField(), "El campo " + error.getField() + " " + error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
