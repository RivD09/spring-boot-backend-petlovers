package org.velasquez.springbootbackendpetlovers.informacion.models.services;

import org.velasquez.springbootbackendpetlovers.informacion.models.entity.Servicio;

import java.util.List;

public interface IServicioService {

    List<Servicio> findAll();
    Servicio findById(Long id);
    Servicio save(Servicio servicio);
    void delete(Servicio servicio);
}
