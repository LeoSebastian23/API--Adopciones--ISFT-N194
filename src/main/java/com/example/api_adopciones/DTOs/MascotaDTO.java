package com.example.api_adopciones.DTOs;

import java.time.LocalDate;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data

public class MascotaDTO {
    private String nombreMascota;
    private long tipoMascotaId; // ID de TipoMascota
    private LocalDate ingreso;
    private String descripcion;
    private boolean disponible;


}
