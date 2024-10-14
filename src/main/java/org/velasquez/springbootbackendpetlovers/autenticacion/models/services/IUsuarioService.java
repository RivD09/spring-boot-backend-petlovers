package org.velasquez.springbootbackendpetlovers.autenticacion.models.services;

import org.velasquez.springbootbackendpetlovers.autenticacion.models.entity.Usuario;
import org.velasquez.springbootbackendpetlovers.autenticacion.models.utilities.UsuarioRequest;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    List<Usuario> findAll();
    Usuario findById(Long id);
    Usuario save(Usuario usuario);
    void delete(Long id);
    Optional<Usuario> update(UsuarioRequest usuario, Long id);
}
