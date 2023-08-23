package com.aspark.carebuddy.exception;

import java.io.IOException;

public class FilesNotFoundException extends RuntimeException{

    public FilesNotFoundException(String message) {
        super(message);
    }

    public FilesNotFoundException(String message, IOException e) {

        super(message, e);
    }

}
