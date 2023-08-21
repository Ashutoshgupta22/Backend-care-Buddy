package com.aspark.carebuddy.firebase;

import com.aspark.carebuddy.exception.FirebaseException;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

public class FirebaseCloudMessaging {

   // @SneakyThrows
    public void sendNotification(String firebaseToken) {

        System.out.println("FCM sendNotification called ");
        System.out.println("registration token: " + firebaseToken);

        Message notification = Message.builder()
                .setNotification(Notification.builder()
                        .setTitle("Booking Request")
                        .setBody("Someone requires your service")
                        .build())
                .setToken(firebaseToken)
                .build();

        try {
            String response = FirebaseMessaging.getInstance().send(notification);
            System.out.println("Successfully sent message " + response );
        }
        catch (FirebaseMessagingException e) {
            System.out.println("FCM Error: " + e);
            throw new FirebaseException(e);
        }
    }
}
