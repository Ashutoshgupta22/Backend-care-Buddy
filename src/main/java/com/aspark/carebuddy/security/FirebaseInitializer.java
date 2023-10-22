package com.aspark.carebuddy.security;

import com.aspark.carebuddy.aws.S3CredentialsDownloader;
import com.aspark.carebuddy.firebase.FirebaseCloudMessaging;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Configuration
@AllArgsConstructor
public class FirebaseInitializer {

    private S3CredentialsDownloader s3CredentialsDownloader;

    @Bean
    public FirebaseApp initializeFirebaseApp() throws IOException {

        s3CredentialsDownloader.credentialDownload();

        System.out.println("Credentials: "+ System.getenv("GOOGLE_APPLICATION_CREDENTIALS"));

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.getApplicationDefault())
                .build();
        return FirebaseApp.initializeApp(options);
    }

    @Bean
    public FirebaseCloudMessaging firebaseCloudMessaging() {
        return new FirebaseCloudMessaging();
    }
}
