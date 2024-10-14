package org.velasquez.springbootbackendpetlovers.autenticacion.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.velasquez.springbootbackendpetlovers.autenticacion.models.entity.Rol;
import org.velasquez.springbootbackendpetlovers.autenticacion.models.entity.Usuario;
import org.velasquez.springbootbackendpetlovers.autenticacion.models.repositories.RolRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RolServiceImpl implements IRolService{

    private RolRepository rolRepository;

    public RolServiceImpl(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    public List<Rol> findAll() {
        return (List<Rol>) rolRepository.findAll();
    }

    @Override
    public Optional<Rol> findByNombreRol(String name) {

        return rolRepository.findByNombreRol(name);
    }


}
