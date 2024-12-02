package org.velasquez.springbootbackendpetlovers.autenticacion.controllers;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.velasquez.springbootbackendpetlovers.administracion.utilities.ClienteRequest;
import org.velasquez.springbootbackendpetlovers.autenticacion.models.entity.Rol;
import org.velasquez.springbootbackendpetlovers.autenticacion.models.entity.Usuario;
import org.velasquez.springbootbackendpetlovers.autenticacion.models.services.IRolService;
import org.velasquez.springbootbackendpetlovers.autenticacion.models.services.IUsuarioService;
import org.velasquez.springbootbackendpetlovers.autenticacion.models.utilities.UsuarioRequest;
import org.velasquez.springbootbackendpetlovers.autenticacion.utilities.UserClientRequest;
import org.velasquez.springbootbackendpetlovers.clientes.models.entity.Cliente;
import org.velasquez.springbootbackendpetlovers.clientes.models.services.IClienteService;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/apiAutenticacion")
public class AutenticacionController {

    @Autowired
    private IRolService rolService;
    @Autowired
    private IUsuarioService usuarioService;
    @Autowired
    private IClienteService clienteService;

    private final Logger log = LoggerFactory.getLogger(AutenticacionController.class);

    @GetMapping("/usuarios")
    public List<Usuario> indexUsuarios(){return usuarioService.findAll();}

    @GetMapping("/roles")
    public List<Rol> indexRoles(){return rolService.findAll();}

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<?> showUsuario(@PathVariable Long id){
        Usuario usuario = null;
        Map<String, Object> response = new HashMap<>();
        try {
            usuario = usuarioService.findById(id);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la consulta en la bd");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (usuario == null) {
            response.put("mensaje", "Usuario no encontrado | id: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }

    @GetMapping("/usuario/{email}")
    public ResponseEntity<?> getUser(@PathVariable String email){
        Optional<Usuario> optionalUsuario = null;
        Usuario usuario = null;
        Map<String, Object> response = new HashMap<>();
        try {
            optionalUsuario = usuarioService.findByEmail(email);
            usuario = optionalUsuario.orElseThrow();
            usuario.setPassword(null);
        }catch (NoSuchElementException e){
            response.put("mensaje", "Usuario no encontrado");
            response.put("error", "\"error\", e.getMessage().concat(\": \").concat(e.getMostSpecificCause().getMessage())");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }

    @GetMapping("/cliente/{email}")
    public ResponseEntity<?> getClient(@PathVariable String email){
        Optional<ClienteRequest> optionalClienteRequest = null;
        ClienteRequest clienteRequest = null;
        Map<String, Object> response = new HashMap<>();
        try {
            optionalClienteRequest = clienteService.findByEmail(email);
            clienteRequest = optionalClienteRequest.orElseThrow();
        }catch (NoSuchElementException e){
            response.put("mensaje", "Usuario no encontrado");
            response.put("error", "\"error\", e.getMessage().concat(\": \").concat(e.getMostSpecificCause().getMessage())");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ClienteRequest>(clienteRequest, HttpStatus.OK);
    }

    @PostMapping("/usuarios")
    public ResponseEntity<?> create(@Valid @RequestBody Usuario usuario, BindingResult result){
        Usuario usuarionew = null;

        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()){
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' "+err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try{
            usuarionew = usuarioService.save(usuario);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar el registro en la base de datos");
            response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El usuario ha sido creado con exito");
        response.put("usuario", usuarionew);

        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
    }
    @PostMapping("/usuarioCliente")
    public ResponseEntity<?> createUserClient(@Valid @RequestBody UserClientRequest userClientRequest, BindingResult result){
        Usuario usuarionew = new Usuario();
        usuarionew.setNombre(userClientRequest.getNombre());
        usuarionew.setAdmin(false);
        usuarionew.setEmail(userClientRequest.getEmail());
        usuarionew.setPassword(userClientRequest.getPassword());
        usuarionew.setTelefono(userClientRequest.getTelefono());

        Cliente clientenew = new Cliente();
        clientenew.setDireccion(userClientRequest.getDireccion());
        clientenew.setInformacionAdicional(userClientRequest.getInfoAdicional());


        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()){
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' "+err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            usuarionew = usuarioService.save(usuarionew);
            clientenew.setUsuario(usuarionew);
            clientenew = clienteService.save(clientenew);
        } catch (DataAccessException e){
            response.put("mensaje", "Error al realizar el registro en la base de datos");
            response.put("error", e.getMessage().concat(": ".concat(e.getMostSpecificCause().getMessage())));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El usuario ha sido creado con exito");
        response.put("usuario", usuarionew);

        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);

    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody UsuarioRequest usuario, BindingResult result, @PathVariable Long id){
        if (result.hasErrors()){
            return validation(result);
        }
        Optional<Usuario> userOptional = usuarioService.update(usuario, id);

        if (userOptional.isPresent()){
            return ResponseEntity.ok(userOptional.orElseThrow());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Usuario usuario = null;
        Map<String,Object> response = new HashMap<>();

        try {
            usuario = usuarioService.findById(id);
            usuarioService.delete(id);
        }catch (DataAccessException e){
            response.put("mensaje","Error al eliminar datos del cliente en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (usuario == null){
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



