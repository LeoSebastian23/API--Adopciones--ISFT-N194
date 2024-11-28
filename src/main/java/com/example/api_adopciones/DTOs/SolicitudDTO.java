package com.example.api_adopciones.DTOs;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SolicitudDTO {

    private Long id;

    @NotNull(message = "El ID de la mascota es obligatorio")
    private Long idMascota;

    @NotNull(message = "El DNI del adoptante es obligatorio")
    private Long dniAdoptante;

    private LocalDate fechaSolicitud;
}


