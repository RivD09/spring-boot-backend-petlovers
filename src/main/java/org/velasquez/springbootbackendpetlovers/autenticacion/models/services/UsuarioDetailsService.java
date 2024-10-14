package org.velasquez.springbootbackendpetlovers.autenticacion.models.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.velasquez.springbootbackendpetlovers.autenticacion.models.entity.Usuario;
import org.velasquez.springbootbackendpetlovers.autenticacion.models.repositories.UsuarioRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Usuario> optionalUsuario = usuarioRepository.findByNombre(username);

        if (optionalUsuario.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Usuario %s no encontrado", username));
        }

        Usuario usuario = optionalUsuario.orElseThrow();

        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(usuario.getRol().getNombreRol()));
        return new User(username, usuario.getPassword(), true, true, true, true, authorities);
    }
}
