package org.velasquez.springbootbackendpetlovers.autenticacion.models.services;

import org.velasquez.springbootbackendpetlovers.autenticacion.models.entity.Rol;
import org.velasquez.springbootbackendpetlovers.autenticacion.models.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface IRolService {

    List<Rol> findAll();

    Optional<Rol> findByNombreRol(String name);



}
