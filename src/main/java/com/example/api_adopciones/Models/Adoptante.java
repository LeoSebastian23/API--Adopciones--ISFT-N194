package com.example.api_adopciones.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "adoptantes")
@Getter
@Setter
public class Adoptante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "apellido", length = 50, nullable = false)
    private String apellido;

    @Column(name = "dni", length = 50, nullable = false)
    private int dni;

    @Column(name = "celular", length = 50, nullable = false)
    private int celular;

    @Column(name = "email", length = 50, nullable = false)
    private String email;
}


