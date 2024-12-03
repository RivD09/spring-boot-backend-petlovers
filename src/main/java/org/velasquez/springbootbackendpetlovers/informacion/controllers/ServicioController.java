package org.velasquez.springbootbackendpetlovers.informacion.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.velasquez.springbootbackendpetlovers.SpringBootBackendPetloversApplication;
import org.velasquez.springbootbackendpetlovers.clientes.models.entity.Cliente;
import org.velasquez.springbootbackendpetlovers.informacion.models.entity.Servicio;
import org.velasquez.springbootbackendpetlovers.informacion.models.services.IServicioService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/apiServicio")
public class ServicioController {
    @Autowired
    private IServicioService servicioService;

    private final Logger log = LoggerFactory.getLogger(ServicioController.class);

    @GetMapping("/servicios")
    public List<Servicio> obtenerServicios() {
        return servicioService.findAll();
    }

    @GetMapping("/servicio/{id}")
    public ResponseEntity<?> obtenerServicioById(@PathVariable Long id) {
        Servicio servicio = null;
        Map<String, Object> response = new HashMap<>();
        try {
            servicio = servicioService.findById(id);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la consulta en la bd");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (servicio == null) {
            response.put("mensaje", "Servicio no encontrado | id: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Servicio>(servicio, HttpStatus.OK);
    }

}
