package com.asseco.demo.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Log4j2
public class ApiExceptionHandler {

    @ExceptionHandler(MyException.class)
    public ResponseEntity<ErrorResponse> handleMyException(MyException myException) {
        log.error(myException);
        return ResponseEntity
                .status(myException.getHttpStatus())
                .body(new ErrorResponse(myException.getHttpStatus().value(), myException.getErrorCode(), myException.getMessage()));
    }
}
