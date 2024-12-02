package org.velasquez.springbootbackendpetlovers.clientes.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.velasquez.springbootbackendpetlovers.clientes.models.entity.Cliente;
import org.velasquez.springbootbackendpetlovers.clientes.models.entity.Mascota;

import java.util.List;

public interface MascotaRepository extends CrudRepository<Mascota, Long> {

    List<Mascota> findAllByCliente(Cliente cliente);

}
