package org.velasquez.springbootbackendpetlovers.autenticacion.models.services;

import org.velasquez.springbootbackendpetlovers.autenticacion.models.entity.Usuario;
import org.velasquez.springbootbackendpetlovers.autenticacion.models.utilities.UsuarioRequest;
import org.velasquez.springbootbackendpetlovers.clientes.models.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    List<Usuario> findAll();
    Usuario findById(Long id);
    Optional<Usuario> findByEmail(String email);
    Usuario save(Usuario usuario);
    void delete(Long id);
    Optional<Usuario> update(UsuarioRequest usuario, Long id);
}
