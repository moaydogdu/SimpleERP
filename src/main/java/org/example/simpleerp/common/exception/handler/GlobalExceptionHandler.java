package org.example.simpleerp.common.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.example.simpleerp.common.exception.CustomError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleProcessError(final Exception exception) {

        log.error(exception.getMessage(), exception);


        CustomError enbError = CustomError.builder()
                .httpStatus(HttpStatus.UNPROCESSABLE_ENTITY)
                .header(CustomError.Header.PROCESS_ERROR.getName())
                .build();

        return new ResponseEntity<>(enbError, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
