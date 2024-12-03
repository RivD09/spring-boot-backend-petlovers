package org.velasquez.springbootbackendpetlovers.citas.utilities;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CitaDetailsRequest {
    private LocalDate fechaCita;
    private String estado;
    private String detallesVisita;
    private String motivo;
    private Long idMascota;
    private String nombreMascota;
    private String especie;
    private int edad;
    private Long idCliente;
    private String nombre;
    private String telefono;
    private Long idServicio;
    private String nombreServicio;
    private String descripcionServicio;
    private Double costoServicio;
}
