package com.example.api_adopciones.Models;

import jakarta.persistence.*;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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

    private LocalDate ingreso;
    private String descripcion;
    private boolean disponible;
}
