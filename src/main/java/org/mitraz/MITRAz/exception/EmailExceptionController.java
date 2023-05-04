package org.mitraz.MITRAz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class EmailExceptionController {

    @ExceptionHandler(value = EmailExistsException.class)
    public ResponseEntity<Map<String,String>> exception(EmailExistsException e) {

        Map<String,String> errorResponse = Map.of(
                "message","Email already registered",    //TODO why is e null
                "status",HttpStatus.FORBIDDEN.toString()
        );

        return new  ResponseEntity<>(errorResponse,HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = EmailNotFoundException.class)
    public ResponseEntity<Map<String,String>> notFoundException(EmailNotFoundException e) {

        Map<String,String> errorResponse = Map.of(
                "message","User not found",
                "status",HttpStatus.NOT_FOUND.toString()
        );

        return new  ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }
}
