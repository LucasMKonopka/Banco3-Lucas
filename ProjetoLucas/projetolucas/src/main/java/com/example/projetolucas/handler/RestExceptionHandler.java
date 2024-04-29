package com.example.projetolucas.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.projetolucas.common.ConversorData;
import com.example.projetolucas.domain.exception.BadRequestException;
import com.example.projetolucas.domain.exception.ResourceNotFoundException;
import com.example.projetolucas.domain.model.ErroResposta;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErroResposta> handlerResourceNotFoundException(ResourceNotFoundException ex){
        String dataHora = ConversorData
        .converterDateParaDataHora(new Date());
        ErroResposta erro = new ErroResposta(dataHora, HttpStatus.NOT_FOUND.value(), "NOT FOUND",
        ex.getMessage());
        return new ResponseEntity<>(erro,
        HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErroResposta> handlerBadRequestException(BadRequestException ex){
        String dataHora = ConversorData
        .converterDateParaDataHora(new Date());
        ErroResposta erro = new ErroResposta(dataHora, HttpStatus.BAD_REQUEST.value(), "BAD REQUEST",
        ex.getMessage());
        return new ResponseEntity<>(erro,
        HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResposta> handlerException(Exception ex){
        String dataHora = ConversorData
        .converterDateParaDataHora(new Date());
        ErroResposta erro = new ErroResposta(dataHora, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error",
        ex.getMessage());
        return new ResponseEntity<>(erro,
        HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
