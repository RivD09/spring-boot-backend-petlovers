package org.velasquez.springbootbackendpetlovers.citas.models.services;

import org.springframework.stereotype.Service;
import org.velasquez.springbootbackendpetlovers.citas.models.entity.Cita;
import org.velasquez.springbootbackendpetlovers.citas.models.repositories.CitaRepository;
import org.velasquez.springbootbackendpetlovers.citas.utilities.CitaRequest;
import org.velasquez.springbootbackendpetlovers.clientes.models.entity.Cliente;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CitaServiceImpl implements ICitaService {

    private CitaRepository citaRepository;

    public CitaServiceImpl(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    @Override
    public List<CitaRequest> findAll() {
        List<Cita> citas = (List<Cita>) citaRepository.findAll();
        return citas.stream().map(this::convertToCitaRequest).collect(Collectors.toList());
    }

    @Override
    public Cita findById(Long id) {
        return citaRepository.findById(id).orElse(null);
    }

    @Override
    public List<CitaRequest> findByCliente(Cliente cliente) {
        List<Cita> citas = (List<Cita>) citaRepository.findByCliente(cliente);
        return citas.stream().map(this::convertToCitaRequest).collect(Collectors.toList());
    }

    @Override
    public Cita save(Cita cita) {
        return citaRepository.save(cita);
    }

    @Override
    public void delete(Cita cita) {
        citaRepository.delete(cita);
    }

    @Override
    public Optional<Cita> update(Cita cita) {
        return Optional.empty();
    }

    private CitaRequest convertToCitaRequest(Cita cita) {
        CitaRequest citaRequest = new CitaRequest();
        citaRequest.setIdCita(cita.getIdCita());
        citaRequest.setFechaCita(cita.getFechaCita());
        citaRequest.setEstado(cita.getEstado());
        citaRequest.setMotivo(cita.getMotivo());
        citaRequest.setEspecie(cita.getMascota().getEspecie());
        citaRequest.setIdCliente(cita.getCliente().getIdCliente());
        citaRequest.setNombre(cita.getCliente().getUsuario().getNombre());
        citaRequest.setIdServicio(cita.getServicio().getIdServicio());
        citaRequest.setNombreServicio(cita.getServicio().getNombreServicio());
        citaRequest.setCostoServicio(cita.getServicio().getCostoServicio());
        return citaRequest;
    }

}
