package org.velasquez.springbootbackendpetlovers.informacion.models.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "servicio")
public class Servicio implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio")
    private Long idServicio;
    @Column(name = "nombre_servicio")
    private String nombreServicio;
    @Column(name = "descripcion_servicio")
    private String descripcionServicio;
    @Column(name = "costo_servicio")
    private Double costoServicio;
}
