package com.example.api_adopciones.DTOs;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MascotaDTO {
    private String nombreMascota;
    private long tipoMascota; // ID de TipoMascota
    private LocalDate ingreso;
    private String descripcion;
    private boolean disponible;
}
