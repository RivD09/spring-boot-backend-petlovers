package org.velasquez.springbootbackendpetlovers.autenticacion.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "usuarios")
public class Usuarios implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuario;

    @NotEmpty
    @Column(nullable = false)
    private String nombre;

    @NotEmpty
    @Email
    @Column(nullable = false)
    private String email;

    private String password;

    private String telefono;

    @Column(nullable = false)
    private Date fecha_creacion;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol")
    private Roles rol;

}
