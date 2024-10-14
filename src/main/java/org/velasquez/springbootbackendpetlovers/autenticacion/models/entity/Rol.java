package org.velasquez.springbootbackendpetlovers.autenticacion.models.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "rol")
public class Rol {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long idRol;

    @NotEmpty
    @Column(name = "nombre_rol",nullable = false)
    private String nombreRol;

    @NotEmpty
    @Column(nullable = false)
    private String descripcion;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rol", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Usuario> usuario;
}
