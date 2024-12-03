package org.velasquez.springbootbackendpetlovers.clientes.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.velasquez.springbootbackendpetlovers.citas.models.entity.Cita;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "mascota")
public class Mascota implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mascota")
    private Long idMascota;

    @Column(name = "nombre_mascota", length = 100, nullable = false)
    private String nombreMascota;

    @Column(name = "especie", length = 50, nullable = false)
    private String especie;

    @Column(name = "raza", length = 50, nullable = false)
    private String raza;

    @Column(name = "edad")
    private int edad;

    @Column(name = "peso")
    private Double peso;

    // Relación con Cliente
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    @JsonIgnore
    private Cliente cliente;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "mascota", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Cita> cita;

}
