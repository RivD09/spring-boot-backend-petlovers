package org.velasquez.springbootbackendpetlovers.citas.models.services;

import org.velasquez.springbootbackendpetlovers.citas.models.entity.Cita;
import org.velasquez.springbootbackendpetlovers.citas.utilities.CitaRequest;
import org.velasquez.springbootbackendpetlovers.clientes.models.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface ICitaService {

    List<CitaRequest> findAll();
    Cita findById(Long id);
    List<CitaRequest> findByCliente(Cliente cliente);
    Cita save(Cita cita);
    void delete(Cita cita);
    Optional<Cita> update(Cita cita);

}
