package org.velasquez.springbootbackendpetlovers.informacion.models.services;

import org.springframework.stereotype.Service;
import org.velasquez.springbootbackendpetlovers.informacion.models.entity.Servicio;
import org.velasquez.springbootbackendpetlovers.informacion.models.repositories.ServicioRepository;

import java.util.List;

@Service
public class ServicioServiceImpl implements IServicioService {

    private ServicioRepository servicioRepository;

    public ServicioServiceImpl(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    @Override
    public List<Servicio> findAll() {
        return (List<Servicio>) servicioRepository.findAll();
    }

    @Override
    public Servicio findById(Long id) {
        return servicioRepository.findById(id).orElse(null);
    }

    @Override
    public Servicio save(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    @Override
    public void delete(Servicio servicio) {
        servicioRepository.delete(servicio);
    }
}
