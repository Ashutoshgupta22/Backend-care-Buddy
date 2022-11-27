package org.mitraz.MITRAz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NurseExceptionController {

    @ExceptionHandler(value = NurseNotFoundException.class)
    public ResponseEntity<Object> exception(NurseNotFoundException exception) {

        return new ResponseEntity<>("Nurse not found.", HttpStatus.NOT_FOUND);
    }

}
