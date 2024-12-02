package org.velasquez.springbootbackendpetlovers.clientes.models.services;

import org.velasquez.springbootbackendpetlovers.administracion.utilities.MascotaRequest;
import org.velasquez.springbootbackendpetlovers.clientes.models.entity.Cliente;
import org.velasquez.springbootbackendpetlovers.clientes.models.entity.Mascota;

import java.util.List;
import java.util.Optional;

public interface IMascotaService {

    List<Mascota> findAll();
    List<Mascota> findAllByCliente(Long id);
    Mascota findById(Long id);
    Mascota save(Mascota mascota, Long id);
    void delete(Long id);
    Optional<MascotaRequest> update(MascotaRequest mascota, Long id);

}
