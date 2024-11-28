package com.example.api_adopciones.DTOs;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data

public class MascotaDTO {
    @NotNull(message = "El nombre de la mascota no puede ser nulo")
    private String nombreMascota;

    @NotNull(message = "El tipo de mascota es obligatorio")
    private Long tipoMascotaId;

    @NotNull(message = "La fecha de ingreso no puede ser nula")
    private LocalDate ingreso;

    @NotNull(message = "La descripción no puede ser nula")
    private String descripcion;

    private boolean disponible;

}
