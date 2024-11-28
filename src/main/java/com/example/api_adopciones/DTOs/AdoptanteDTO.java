package com.example.api_adopciones.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AdoptanteDTO {
    @NotNull(message = "El nombre no puede ser nulo.")
    @NotBlank(message = "El nombre no puede estar vacío.")
    private String nombre;

    @NotNull(message = "El apellido no puede ser nulo.")
    @NotBlank(message = "El apellido no puede estar vacío.")
    private String apellido;

    @NotNull(message = "El dni no puede ser nulo.")
    @NotBlank(message = "El dni no puede estar vacío.")
    private Long dni;

    @NotNull(message = "El celular no puede ser nulo.")
    @NotBlank(message = "El celular no puede estar vacío.")
    private Long celular;

    @NotNull(message = "El email no puede ser nulo.")
    @NotBlank(message = "El email no puede estar vacío.")
    @Email(message = "El email debe tener un formato válido.")
    private String email;
}

