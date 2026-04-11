package com.gamifiedlibrary.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.gamifiedlibrary.api.infrastructure.dto.ExceptionResponseDTO;

import io.jsonwebtoken.JwtException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.EntityExistsException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Captura exceções de recurso não encontrado
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> entityNotFound(EntityNotFoundException ex) {
    	ExceptionResponseDTO error = new ExceptionResponseDTO(ex.getMessage(), HttpStatus.NOT_FOUND.toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    
    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ExceptionResponseDTO> invalidJwt(JwtException ex) {
    	ExceptionResponseDTO error = new ExceptionResponseDTO("Invalid token", HttpStatus.UNAUTHORIZED.toString());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }
    
    @ExceptionHandler
    public ResponseEntity<ExceptionResponseDTO> entityExistsException(EntityExistsException ex) {
    	ExceptionResponseDTO error = new ExceptionResponseDTO(ex.getMessage(), HttpStatus.CONFLICT.toString());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    // Captura exceções de recurso já existente
   
}







/*
 * // Captura erros de validação do @Valid / @Validated
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        String msg = ex.getBindingResult()
                       .getFieldErrors()
                       .stream()
                       .map(f -> f.getField() + ": " + f.getDefaultMessage())
                       .collect(Collectors.joining(", "));
        return ResponseEntity.badRequest().body(new ErrorResponse(400, msg));
    }

    // Captura regras de negócio
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusiness(BusinessException ex) {
        return ResponseEntity.badRequest().body(new ErrorResponse(400, ex.getMessage()));
    }

    // Captura qualquer outra exceção não tratada (fallback)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body(new ErrorResponse(500, "Erro interno no servidor"));
    }
 */
 