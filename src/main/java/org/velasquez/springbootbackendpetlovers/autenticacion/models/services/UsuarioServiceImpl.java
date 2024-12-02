package org.velasquez.springbootbackendpetlovers.autenticacion.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.velasquez.springbootbackendpetlovers.autenticacion.models.entity.Rol;
import org.velasquez.springbootbackendpetlovers.autenticacion.models.entity.Usuario;
import org.velasquez.springbootbackendpetlovers.autenticacion.models.repositories.RolRepository;
import org.velasquez.springbootbackendpetlovers.autenticacion.models.repositories.UsuarioRepository;
import org.velasquez.springbootbackendpetlovers.autenticacion.models.utilities.UsuarioRequest;
import org.velasquez.springbootbackendpetlovers.clientes.models.entity.Cliente;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private UsuarioRepository usuarioRepository;
    private PasswordEncoder passwordEncoder;
    private IRolService rolService;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, IRolService rolService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.rolService = rolService;
    }

    @Override
    public List<Usuario> findAll() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    @Override
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        Optional<Usuario> userOptional = usuarioRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            Usuario userDb = userOptional.get();
            return Optional.of(userDb);
        } else {
            return Optional.empty();
        }
    }

    @Transactional
    @Override
    public Usuario save(Usuario usuario) {

        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("El email ya está registrado.");
        }
        if (usuario.isAdmin()){
            Optional<Rol> optionalRolAdmin = rolService.findByNombreRol("ROLE_ADMIN");
            optionalRolAdmin.ifPresent(usuario::setRol);
        } else {
            Optional<Rol> optionalRolCliente = rolService.findByNombreRol("ROLE_CLIENTE");
            optionalRolCliente.ifPresent(usuario::setRol);
        }

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    @Override
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<Usuario> update(UsuarioRequest user, Long id) {
        Optional<Usuario> userOptional = usuarioRepository.findById(id);

        if (userOptional.isPresent()) {
            Usuario userDb = userOptional.get();
            userDb.setNombre(user.getNombre());
            userDb.setEmail(user.getEmail());
            userDb.setTelefono(user.getTelefono());

            if (user.isAdmin()){
                Optional<Rol> optionalRolAdmin = rolService.findByNombreRol("ROLE_ADMIN");
                optionalRolAdmin.ifPresent(userDb::setRol);
            } else {
                Optional<Rol> optionalRolCliente = rolService.findByNombreRol("ROLE_CLIENTE");
                optionalRolCliente.ifPresent(userDb::setRol);
            }

            return Optional.of(usuarioRepository.save(userDb));
        }
        return Optional.empty();
    }

    public void setRol(){

    }


}
