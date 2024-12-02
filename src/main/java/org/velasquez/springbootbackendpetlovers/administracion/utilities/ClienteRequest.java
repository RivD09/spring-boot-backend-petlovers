package org.velasquez.springbootbackendpetlovers.administracion.utilities;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ClienteRequest {

    private Long idCliente;
    private String nombre;
    private String direccion;
    private String telefono;
    private String infoAdicional;
    private int cantMascotas;

    public ClienteRequest(Long idCliente, String nombre, String direccion, String telefono, String infoAdicional, int cantMascotas) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.infoAdicional = infoAdicional;
        this.cantMascotas = cantMascotas;
    }
}
