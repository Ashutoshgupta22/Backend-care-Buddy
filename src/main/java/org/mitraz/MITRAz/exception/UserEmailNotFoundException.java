package org.mitraz.MITRAz.exception;

public class UserEmailNotFoundException extends RuntimeException{

    public UserEmailNotFoundException(String message) {
        super(message);
    }
}
