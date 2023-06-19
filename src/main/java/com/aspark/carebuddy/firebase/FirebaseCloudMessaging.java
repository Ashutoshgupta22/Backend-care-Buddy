package com.aspark.carebuddy.firebase;

import com.aspark.carebuddy.exception.FirebaseException;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

public class FirebaseCloudMessaging {

   // @SneakyThrows
    public void sendNotification(String registrationToken) {

        System.out.println("FCM sendNotification called ");
        System.out.println("registration token: " + registrationToken);

        Message notification = Message.builder()
                .setNotification(Notification.builder()
                        .setTitle("Service Request")
                        .setBody("Someone needs your service")
                        .build())
                .setToken(registrationToken)
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
