package com.example.api_adopciones.Models;

import jakarta.persistence.*;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "solicitud")
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_mascota", nullable = false)
    private Mascota mascota;

    @ManyToOne
    @JoinColumn(name = "dni_adoptante", referencedColumnName = "dni", nullable = false)
    private Adoptante adoptante;

    @Column(name = "fecha_solicitud", nullable = false)
    private LocalDate fechaSolicitud;
}


