package com.example.api_adopciones.Exceptions;

public class AdoptanteCreationException extends RuntimeException {
    public AdoptanteCreationException(String message) {
        super(message);
    }

    public AdoptanteCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}