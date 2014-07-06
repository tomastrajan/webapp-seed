package com.trajan.seed.webapp.rest.config;

import com.trajan.seed.webapp.rest.exception.OperationOnNonexistentResourceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Tomas Trajan
 * @creaded 2014-07-06
 */
@ControllerAdvice
class GlobalControllerExceptionHandler {

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Specified resource not found")
    @ExceptionHandler(OperationOnNonexistentResourceException.class)
    public void operationOnNonexistentResourceException() {}

}