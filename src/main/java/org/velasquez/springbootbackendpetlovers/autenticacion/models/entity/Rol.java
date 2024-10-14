package org.velasquez.springbootbackendpetlovers.autenticacion.models.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "roles")
public class Roles {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_rol;

    @NotEmpty
    @Column(nullable = false)
    private String nombre_rol;

    @NotEmpty
    @Column(nullable = false)
    private String descripcion;
}
