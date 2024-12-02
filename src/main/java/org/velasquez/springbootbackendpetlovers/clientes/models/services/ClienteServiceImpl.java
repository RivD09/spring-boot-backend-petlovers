package org.velasquez.springbootbackendpetlovers.clientes.models.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.velasquez.springbootbackendpetlovers.administracion.utilities.ClienteRequest;
import org.velasquez.springbootbackendpetlovers.autenticacion.models.entity.Usuario;
import org.velasquez.springbootbackendpetlovers.autenticacion.models.repositories.UsuarioRepository;
import org.velasquez.springbootbackendpetlovers.clientes.models.entity.Cliente;
import org.velasquez.springbootbackendpetlovers.clientes.models.repositories.ClienteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements IClienteService{

    private ClienteRepository clienteRepository;
    private UsuarioRepository usuarioRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository, UsuarioRepository usuarioRepository) {
        this.clienteRepository = clienteRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Cliente> findAll() {
        return (List<Cliente>) clienteRepository.findAll();
    }

    @Override
    public Cliente findById(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public Optional<ClienteRequest> findByEmail(String email) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            Optional<Cliente> clienteOptional = clienteRepository.findByUsuario(usuario);
            if (clienteOptional.isPresent()) {
                Cliente cliente = clienteOptional.get();
                ClienteRequest clienteRequest = new ClienteRequest(cliente.getIdCliente(),cliente.getUsuario().getNombre(), cliente.getDireccion(), cliente.getUsuario().getTelefono(), cliente.getInformacionAdicional(), cliente.getMascotas().size());
                return Optional.of(clienteRequest);
            }
        }
        return Optional.empty();

    }


    @Override
    @Transactional
    public Optional<ClienteRequest> update(ClienteRequest cliente, Long id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);

        if (clienteOptional.isPresent()) {
            Cliente clienteUpdate = clienteOptional.get();

            Optional<Usuario> usuarioOptional = usuarioRepository.findById(clienteUpdate.getUsuario().getIdUsuario());

            if (usuarioOptional.isPresent()) {
                Usuario usuarioUpdate = usuarioOptional.get();

                //Actualizar campos de usuario
                usuarioUpdate.setNombre(cliente.getNombre());
                usuarioUpdate.setTelefono(cliente.getTelefono());
                //Actualizar campos de cliente
                clienteUpdate.setDireccion(cliente.getDireccion());
                clienteUpdate.setInformacionAdicional(cliente.getInfoAdicional());
                //Guardar las actualizaciones
                usuarioRepository.save(usuarioUpdate);
                clienteRepository.save(clienteUpdate);

                // Transforma el Cliente actualizado a ClienteRequest y retorna
                ClienteRequest clienteRequestUpdated = new ClienteRequest(clienteUpdate.getIdCliente(), usuarioUpdate.getNombre(), clienteUpdate.getDireccion(), usuarioUpdate.getTelefono(), cliente.getInfoAdicional(), clienteUpdate.getMascotas().size());
                return Optional.of(clienteRequestUpdated);
            }
        }
        return Optional.empty();
    }


}
