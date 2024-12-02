package org.velasquez.springbootbackendpetlovers.administracion.controllers;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.velasquez.springbootbackendpetlovers.administracion.utilities.ClienteRequest;
import org.velasquez.springbootbackendpetlovers.administracion.utilities.MascotaRequest;
import org.velasquez.springbootbackendpetlovers.autenticacion.models.utilities.UsuarioRequest;
import org.velasquez.springbootbackendpetlovers.clientes.controllers.ClienteController;
import org.velasquez.springbootbackendpetlovers.clientes.models.entity.Cliente;
import org.velasquez.springbootbackendpetlovers.clientes.models.entity.Mascota;
import org.velasquez.springbootbackendpetlovers.clientes.models.services.IClienteService;
import org.velasquez.springbootbackendpetlovers.clientes.models.services.IMascotaService;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/apiAdmin")
public class AdminController {

    @Autowired
    private IClienteService clienteService;
    @Autowired
    private IMascotaService mascotaService;

    private final Logger log = LoggerFactory.getLogger(ClienteController.class);

    @GetMapping("/clientes")
    public List<ClienteRequest> indexClientes(){
        List<Cliente> clientes = clienteService.findAll();
        List<ClienteRequest> clienteRequests = clientes.stream()
                .map(cliente -> new ClienteRequest(
                        cliente.getIdCliente(),
                        cliente.getUsuario().getNombre(),
                        cliente.getDireccion(),
                        cliente.getUsuario().getTelefono(),
                        cliente.getInformacionAdicional(),
                        cliente.getMascotas().size()))
                .collect(Collectors.toList());
        return clienteRequests;
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<?> getClient(@PathVariable Long id){
        Cliente cliente = clienteService.findById(id);
        ClienteRequest clienteRequest = new ClienteRequest(cliente.getIdCliente(), cliente.getUsuario().getNombre(), cliente.getDireccion(), cliente.getUsuario().getTelefono(), cliente.getInformacionAdicional(), cliente.getMascotas().size());
        return new ResponseEntity<>(clienteRequest, HttpStatus.OK);
    }

    @GetMapping("/mascotas/{id}")
    public List<Mascota> indexMascotasOfClient(@PathVariable Long id){
        List<Mascota> mascotas = mascotaService.findAllByCliente(id);
        return mascotas;
    }

    @PutMapping("/updateCliente/{id}")
    public ResponseEntity<?> updateClient(@Valid @RequestBody ClienteRequest cliente, BindingResult result, @PathVariable Long id){
        if (result.hasErrors()) {
            return validation(result);
        }
        Optional<ClienteRequest> clienteRequestOptional = clienteService.update(cliente,id);

        if (clienteRequestOptional.isPresent()) {
            return ResponseEntity.ok(clienteRequestOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("deleteCliente/{id}")
    private ResponseEntity<?> deleteCliente(@PathVariable Long id){
        Cliente cliente = null;
        Map<String,Object> response = new HashMap<>();

        try {
            cliente = clienteService.findById(id);
            clienteService.delete(id);
        }catch (DataAccessException e) {
            response.put("mensaje","Error al eliminar datos del cliente en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (cliente == null) {
            response.put("mensaje","El usuario ID:".concat(id.toString().concat(" no existe en la base de datos")));
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
