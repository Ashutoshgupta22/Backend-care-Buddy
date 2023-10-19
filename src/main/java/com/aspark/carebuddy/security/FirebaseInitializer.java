package com.aspark.carebuddy.security;

import com.aspark.carebuddy.firebase.FirebaseCloudMessaging;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseInitializer {

    @Bean
    public FirebaseApp initializeFirebaseApp() throws IOException {

//        System.out.println("Credentials: "+ System.getenv("GOOGLE_APPLICATION_CREDENTIALS"));
//
//        FirebaseOptions options = FirebaseOptions.builder()
//                .setCredentials(GoogleCredentials.getApplicationDefault())
//                .build();
//        return FirebaseApp.initializeApp(options);
        return null;
    }

    @Bean
    public FirebaseCloudMessaging firebaseCloudMessaging() {
        return new FirebaseCloudMessaging();
    }
}
