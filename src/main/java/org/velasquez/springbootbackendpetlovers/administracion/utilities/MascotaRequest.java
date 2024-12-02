package org.velasquez.springbootbackendpetlovers.administracion.utilities;

import lombok.Data;

@Data
public class MascotaRequest {

    private String nombreMascota;
    private String especie;
    private String raza;
    private int edad;
    private Double peso;

    public MascotaRequest(String nombreMascota, String especie, String raza, int edad, Double peso) {
        this.nombreMascota = nombreMascota;
        this.especie = especie;
        this.raza = raza;
        this.edad = edad;
        this.peso = peso;
    }

}

