package org.velasquez.springbootbackendpetlovers.autenticacion.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.velasquez.springbootbackendpetlovers.autenticacion.models.entity.Rol;
import org.velasquez.springbootbackendpetlovers.autenticacion.models.entity.Usuario;

import java.util.Optional;

public interface RolRepository extends CrudRepository<Rol, Long> {

    Optional<Rol> findByNombreRol(String nombre);

}
