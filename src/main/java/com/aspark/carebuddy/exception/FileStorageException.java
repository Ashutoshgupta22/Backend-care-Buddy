package com.aspark.carebuddy.exception;

import java.io.IOException;

public class FileStorageException extends RuntimeException{

    public FileStorageException(String message, IOException e) {
        super(message, e);
    }
}
