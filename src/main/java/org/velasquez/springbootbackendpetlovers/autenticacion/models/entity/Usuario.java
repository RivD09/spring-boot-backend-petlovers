package org.velasquez.springbootbackendpetlovers.autenticacion.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.velasquez.springbootbackendpetlovers.clientes.models.entity.Cliente;

import java.io.Serializable;

@Data
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @NotEmpty
    @Column(nullable = false)
    private String nombre;

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean admin;

    @NotEmpty
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    private String telefono;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol")
    @JsonIgnoreProperties(value={"rol","usuario","hibernateLazyInitializer","handler"}, allowSetters = true)
    private Rol rol;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "usuario")
    private Cliente cliente;

}
