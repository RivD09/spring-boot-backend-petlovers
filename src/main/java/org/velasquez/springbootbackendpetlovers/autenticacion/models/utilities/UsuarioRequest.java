package org.velasquez.springbootbackendpetlovers.autenticacion.models.utilities;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UsuarioRequest {

    @NotEmpty
    @Column(nullable = false)
    private String nombre;

    @NotEmpty
    @Email
    @Column(nullable = false)
    private String email;

    private String telefono;

    private boolean admin;
}
