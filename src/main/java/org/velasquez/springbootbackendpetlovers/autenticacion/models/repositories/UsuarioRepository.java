package org.velasquez.springbootbackendpetlovers.autenticacion.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.velasquez.springbootbackendpetlovers.autenticacion.models.entity.Usuarios;

public interface UsuariosRepository extends CrudRepository<Usuarios, Long> {

}
