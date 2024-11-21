package com.example.api_adopciones.Models;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "solicitud")
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_mascota", nullable = false)
    @NotNull // Validación
    private Mascota mascota;

    @ManyToOne
    @JoinColumn(name = "dni_adoptante", referencedColumnName = "dni", nullable = false)
    @NotNull
    private Adoptante adoptante;

    @Column(name = "fecha_solicitud", nullable = false)
    @NotNull
    private LocalDate fechaSolicitud;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_solicitud", nullable = false)
    @NotNull
    private EstadoSolicitud estadoSolicitud;

    // Constructores, getters y setters


    // Constructor vacío
    public Solicitud() {
    }

    // Constructor con parámetros
    public Solicitud(Mascota mascota, Adoptante adoptante, LocalDate fechaSolicitud, EstadoSolicitud estadoSolicitud) {
        this.mascota = mascota;
        this.adoptante = adoptante;
        this.fechaSolicitud = fechaSolicitud;
        this.estadoSolicitud = estadoSolicitud;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public Adoptante getAdoptante() {
        return adoptante;
    }

    public void setAdoptante(Adoptante adoptante) {
        this.adoptante = adoptante;
    }

    public LocalDate getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDate fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public EstadoSolicitud getEstadoSolicitud() {
        return estadoSolicitud;
    }

    public void setEstadoSolicitud(EstadoSolicitud estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }

    public EstadoSolicitud isEstadoSolicitud() {
        return estadoSolicitud;
    }

    // Enumerado para los estados de solicitud
    public enum EstadoSolicitud {
        PENDIENTE,
        APROBADO,
        RECHAZADO
    }
}


