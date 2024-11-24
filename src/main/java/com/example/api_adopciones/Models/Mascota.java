package com.example.api_adopciones.Models;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "mascotas")
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreMascota;

    @ManyToOne
    @JoinColumn(name = "tipo_mascota_id", nullable = false)
    private TipoMascota tipoMascota;

    private int edad;
    private String descripcion;
    private boolean disponible;
}
