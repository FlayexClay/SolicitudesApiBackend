package com.example.solicitudes.domain.exception;

public class EstadoInvalidoException extends RuntimeException{
    public EstadoInvalidoException(String mensaje){
        super(mensaje);
    }
}
