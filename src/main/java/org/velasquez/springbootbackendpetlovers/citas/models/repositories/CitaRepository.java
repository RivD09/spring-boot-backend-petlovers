package org.velasquez.springbootbackendpetlovers.citas.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.velasquez.springbootbackendpetlovers.citas.models.entity.Cita;
import org.velasquez.springbootbackendpetlovers.clientes.models.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface CitaRepository extends CrudRepository<Cita, Long> {

    List<Cita> findByCliente(Cliente cliente);
}
