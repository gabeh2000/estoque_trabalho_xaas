package com.xaas.produtosapi.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<MensagemErro> handleResourceNotFound(ResourceNotFoundException exception){
        return new ResponseEntity<>(buildErrorMessage("ResourceNotFound",
                exception.getMessage(),
                NOT_FOUND), NOT_FOUND);
    }

    private MensagemErro buildErrorMessage(String erro, String messagem, HttpStatus httpStatus) {
        return MensagemErro.builder()
                .erro(erro)
                .messagem(messagem)
                .timeStamp(LocalDateTime.now())
                .httpStatus(httpStatus)
                .build();
    }

    @RequiredArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class MensagemErro {
        private final String erro;
        private final String messagem;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private final LocalDateTime timeStamp;
        private final HttpStatus httpStatus;
    }
}
