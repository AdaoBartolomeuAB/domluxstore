package com.domluxstore.exception;

import com.domluxstore.exception.domain.ExceptionRecourse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class HandlerException {

    @ExceptionHandler(value = ConflictException.class)
    public ResponseEntity<Object> conflit(ConflictException conflitException){

        ExceptionRecourse exceptionRecourse = ExceptionRecourse.builder()
                .timestamp(LocalDateTime.now().toString())
                .message(conflitException.getMessage())
                .status(HttpStatus.CONFLICT.value())
                .detail(conflitException.getDetails())
                .build();


        System.out.println(""+exceptionRecourse.toString());

        return new ResponseEntity<>(exceptionRecourse,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = RecourceNotFoundException.class)
    public ResponseEntity<Object> recursoNaoEncontradoExececao(RecourceNotFoundException exception){
        ExceptionRecourse exceptionRecourse = ExceptionRecourse.builder()
                .timestamp(LocalDateTime.now().toString())
                .status(HttpStatus.NOT_FOUND.value())
                .message(exception.getMessage())
                .detail(exception.getDetail())
                .build();
        return new ResponseEntity<>(exceptionRecourse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<Object> credenciasRuinExcecao(BadRequestException excecao){
        ExceptionRecourse exceptionRecourse = ExceptionRecourse.builder()
                .timestamp(LocalDateTime.now().toString())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(excecao.getMessage())
                .detail(excecao.getDetail())
                .build();

        System.out.println(""+exceptionRecourse.toString());

        return new ResponseEntity<>(exceptionRecourse,HttpStatus.BAD_REQUEST);
    }


}
