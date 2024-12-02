package org.velasquez.springbootbackendpetlovers.clientes.models.services;

import org.velasquez.springbootbackendpetlovers.administracion.utilities.ClienteRequest;
import org.velasquez.springbootbackendpetlovers.autenticacion.models.entity.Usuario;
import org.velasquez.springbootbackendpetlovers.clientes.models.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface IClienteService {

    List<Cliente> findAll();
    Cliente findById(Long id);
    Cliente save(Cliente cliente);
    void delete(Long id);
    Optional<ClienteRequest> update(ClienteRequest cliente, Long id);
    Optional<ClienteRequest> findByEmail(String email);
}
