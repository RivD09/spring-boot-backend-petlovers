package org.velasquez.springbootbackendpetlovers.citas.utilities;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CitaRequest {
    private Long idCita;
    private LocalDate fechaCita;
    private String estado;
    private String motivo;
    private String especie;
    private Long idCliente;
    private String nombre;
    private Long idServicio;
    private String nombreServicio;
    private Double costoServicio;
}
