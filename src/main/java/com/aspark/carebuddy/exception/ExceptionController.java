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

    @ExceptionHandler(value = FileStorageException.class)
    public ResponseEntity<String> exception(FileStorageException e) {
        return ResponseEntity.internalServerError().body("File storage error: "+e);
    }

    @ExceptionHandler(value = FilesNotFoundException.class)
    public ResponseEntity<String> exception(FilesNotFoundException e) {
        return ResponseEntity.internalServerError().body("File not found: "+e);
    }
}
