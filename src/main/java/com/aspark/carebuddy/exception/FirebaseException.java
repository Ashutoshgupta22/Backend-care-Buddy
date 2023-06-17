package com.aspark.carebuddy.exception;

import com.google.firebase.messaging.FirebaseMessagingException;

public class FirebaseException extends RuntimeException{

    public FirebaseException(FirebaseMessagingException e) {
        super(e);
    }
}
