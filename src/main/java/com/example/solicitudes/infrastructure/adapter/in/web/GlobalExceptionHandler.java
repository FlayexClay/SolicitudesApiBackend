package com.example.solicitudes.infrastructure.adapter.in.web;

import com.example.solicitudes.domain.exception.CredencialesInvalidasException;
import com.example.solicitudes.domain.exception.EstadoInvalidoException;
import com.example.solicitudes.domain.exception.SolicitudNoEncontradaException;
import com.example.solicitudes.domain.exception.UsuarioYaExisteException;
import com.example.solicitudes.infrastructure.adapter.in.web.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebInputException;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SolicitudNoEncontradaException.class)
    public ResponseEntity<ErrorResponse> manejarNoEncontrada(SolicitudNoEncontradaException ex){
        return construir(HttpStatus.NOT_FOUND, "Solicitud no encontrada", ex.getMessage());
    }

    @ExceptionHandler(EstadoInvalidoException.class)
    public ResponseEntity<ErrorResponse> manejarEstadoInvalido(EstadoInvalidoException ex){
        return construir(HttpStatus.CONFLICT, "Estado inválido", ex.getMessage());
    }

    @ExceptionHandler(UsuarioYaExisteException.class)
    public ResponseEntity<ErrorResponse> manejarUsuarioYaExiste(UsuarioYaExisteException ex){
        return construir(HttpStatus.CONFLICT, "Conflicto de usuario", ex.getMessage());
    }

    @ExceptionHandler(CredencialesInvalidasException.class)
    public ResponseEntity<ErrorResponse> manejarCredenciales(CredencialesInvalidasException ex){
        return construir(HttpStatus.UNAUTHORIZED, "No autorizado", ex.getMessage());
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<ErrorResponse> manejarValidacion(WebExchangeBindException ex){
        List<String> detalles = ex.getFieldErrors().stream()
                .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
                .toList();

        ErrorResponse body = ErrorResponse.of(
                HttpStatus.BAD_REQUEST.value(),
                "Datos invalidos",
                "Uno o mas campos no son validos",
                detalles
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(ServerWebInputException.class)
    public ResponseEntity<ErrorResponse> manejarInputInvalido(ServerWebInputException ex){
        return construir(HttpStatus.BAD_REQUEST, "Entrada invalida",
                ex.getReason() != null ? ex.getReason() : "Payload mal formado");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> manejarAccesoDenegado(AccessDeniedException ex){
        return construir(HttpStatus.FORBIDDEN, "Acceso denegado", "No tienes permisos para acceder a este recurso");
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> manejarResponseStatus(ResponseStatusException ex){
        HttpStatus status = HttpStatus.valueOf(ex.getStatusCode().value());
        return construir(status, status.getReasonPhrase(),
                ex.getReason() != null ? ex.getReason() : status.getReasonPhrase());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> manejarGenerico(Exception ex){
        return construir(HttpStatus.INTERNAL_SERVER_ERROR,
                "Error intetno", "Ocurrio un error inesperado");
    }

    private ResponseEntity<ErrorResponse> construir(HttpStatus status, String error, String mensaje){
        return ResponseEntity.status(status)
                .body(ErrorResponse.of(status.value(), error, mensaje));
    }
}
