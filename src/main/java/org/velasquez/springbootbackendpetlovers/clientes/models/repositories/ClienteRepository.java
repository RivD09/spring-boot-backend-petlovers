package org.velasquez.springbootbackendpetlovers.clientes.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.velasquez.springbootbackendpetlovers.autenticacion.models.entity.Usuario;
import org.velasquez.springbootbackendpetlovers.clientes.models.entity.Cliente;

import java.util.Optional;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {
    Optional<Cliente> findByUsuario(Usuario usuario);
}
