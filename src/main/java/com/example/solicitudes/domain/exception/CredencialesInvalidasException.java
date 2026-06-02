package com.example.solicitudes.domain.exception;

public class CredencialesInvalidasException extends RuntimeException{
    public CredencialesInvalidasException(String mensaje){
        super(mensaje);
    }
}
