package org.velasquez.springbootbackendpetlovers.autenticacion.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.velasquez.springbootbackendpetlovers.autenticacion.models.entity.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    Optional<Usuario> findByNombre(String nombre);
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
}
