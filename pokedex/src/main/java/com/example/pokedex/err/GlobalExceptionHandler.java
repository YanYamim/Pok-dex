package com.example.pokedex.err;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    
    // Tratamento para erro 404
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handlerNotFoundException(NotFoundException res) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res.getMessage());
    }

    // Tratamento para erro 400
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handlerBadRequestException(BadRequestException res) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res.getMessage());
    }

    // Tratamento para erro 500
    @ExceptionHandler(InternalErrorException.class)
    public ResponseEntity<String> handlerInternalErrorException(InternalErrorException res) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res.getMessage());
    }
}
