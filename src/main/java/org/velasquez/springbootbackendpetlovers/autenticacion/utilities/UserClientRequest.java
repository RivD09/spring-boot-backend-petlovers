package org.velasquez.springbootbackendpetlovers.autenticacion.utilities;

import lombok.Data;

@Data
public class UserClientRequest {

    private String nombre;
    private String email;
    private String password;
    private String direccion;
    private String telefono;
    private String infoAdicional;

    UserClientRequest(String nombre, String email, String password, String direccion, String telefono, String info) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.direccion = direccion;
        this.telefono = telefono;
        this.infoAdicional = info;
    }

}
