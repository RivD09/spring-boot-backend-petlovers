package org.velasquez.springbootbackendpetlovers.clientes.models.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.velasquez.springbootbackendpetlovers.administracion.utilities.MascotaRequest;
import org.velasquez.springbootbackendpetlovers.clientes.models.entity.Cliente;
import org.velasquez.springbootbackendpetlovers.clientes.models.entity.Mascota;
import org.velasquez.springbootbackendpetlovers.clientes.models.repositories.ClienteRepository;
import org.velasquez.springbootbackendpetlovers.clientes.models.repositories.MascotaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MascotaServiceImpl implements IMascotaService{

    private MascotaRepository mascotaRepository;
    private ClienteRepository clienteRepository;

    public MascotaServiceImpl(MascotaRepository mascotaRepository, ClienteRepository clienteRepository) {
        this.mascotaRepository = mascotaRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Mascota> findAll() {
        return (List<Mascota>) mascotaRepository.findAll();
    }

    @Override
    public List<Mascota> findAllByCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        return mascotaRepository.findAllByCliente(cliente);
    }

    @Override
    public Mascota findById(Long id) {
        return mascotaRepository.findById(id).orElse(null);
    }

    @Override
    public Mascota save(Mascota mascota, Long id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        Cliente cliente = clienteOptional.get();
        mascota.setCliente(cliente);
        return mascotaRepository.save(mascota);
    }

    @Override
    public void delete(Long id) {
        mascotaRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<MascotaRequest> update(MascotaRequest mascota, Long id) {
        Optional<Mascota> mascotaOptional = mascotaRepository.findById(id);
        if (mascotaOptional.isPresent()) {
            Mascota mascotaUpdate = mascotaOptional.get();

            mascotaUpdate.setNombreMascota(mascota.getNombreMascota());
            mascotaUpdate.setEspecie(mascota.getEspecie());
            mascotaUpdate.setRaza(mascota.getRaza());
            mascotaUpdate.setEdad(mascota.getEdad());
            mascotaUpdate.setPeso(mascota.getPeso());
            mascotaRepository.save(mascotaUpdate);

            MascotaRequest mascotaRequestUpdated = new MascotaRequest(mascotaUpdate.getNombreMascota(), mascotaUpdate.getEspecie(), mascotaUpdate.getRaza(), mascotaUpdate.getEdad(), mascotaUpdate.getPeso());
            return Optional.of(mascotaRequestUpdated);
        }
        return Optional.empty();
    }
}
