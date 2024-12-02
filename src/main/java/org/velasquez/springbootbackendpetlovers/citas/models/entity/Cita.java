package org.velasquez.springbootbackendpetlovers.citas.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.velasquez.springbootbackendpetlovers.clientes.models.entity.Mascota;
import org.velasquez.springbootbackendpetlovers.informacion.models.entity.Servicio;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "cita")
public class Cita implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita")
    private Long idCita;

    @Column(name = "fecha_cita")
    private LocalDate fechaCita;

    @Column(name = "estado")
    private String estado;//Pagado o NoPagado

    @Column(name = "detalles_visita")
    private String detallesVisita;

    @Column(name = "motivo")
    private String motivo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mascota")
    //@JsonIgnore
    private Mascota mascota;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_servicio")
    //@JsonIgnore
    private Servicio servicio;

}