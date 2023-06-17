package com.aspark.carebuddy.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = FirebaseException.class)
    public ResponseEntity<String> exception(FirebaseException e) {

        return ResponseEntity.internalServerError().body("FCM error: "+e);
    }
}
