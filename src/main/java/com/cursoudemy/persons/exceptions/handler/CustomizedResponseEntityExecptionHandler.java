package com.cursoudemy.persons.exceptions.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cursoudemy.persons.exceptions.ExecptionResponse;
import com.cursoudemy.persons.exceptions.RessourcePersonNotFaundExceptions;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExecptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExecptionResponse> handleAllExceptions(Exception ex, WebRequest req) {
        ExecptionResponse execptionResponse = new ExecptionResponse(new Date(), ex.getMessage(),
                req.getDescription(false));

        return new ResponseEntity<>(execptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RessourcePersonNotFaundExceptions.class)
    public final ResponseEntity<ExecptionResponse> handlePersonExceptions(Exception ex, WebRequest req) {
        ExecptionResponse execptionResponse = new ExecptionResponse(new Date(), ex.getMessage(),
                req.getDescription(false));

        return new ResponseEntity<>(execptionResponse, HttpStatus.NOT_FOUND);
    }

    // @ExceptionHandler(InvalidJwtAuthenticationException.class)
    // public final ResponseEntity<ExecptionResponse>
    // handleInvalidJwtAuthenticationException(Exception ex,
    // WebRequest req) {
    // ExecptionResponse execptionResponse = new ExecptionResponse(new Date(),
    // ex.getMessage(),
    // req.getDescription(false));

    // return new ResponseEntity<>(execptionResponse, HttpStatus.FORBIDDEN);
    // }
}
