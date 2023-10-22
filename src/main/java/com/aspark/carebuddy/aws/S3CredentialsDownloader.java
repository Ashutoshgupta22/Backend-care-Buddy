package com.aspark.carebuddy.aws;

import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
public class S3CredentialsDownloader {

    private final String bucketName = "care-buddy";
    private final String objectKey = "credentials/care-buddy-2-de09115d9cd4.json";
    private final String localFilePath = "firebaseCredentials.json";

    public void credentialDownload() {

        try {
            ResponseBytes<GetObjectResponse> responseBytes;

            //create s3 client
            try (S3Client s3Client = S3Client.builder()
                    .region(Region.AP_SOUTH_1)
                    .build()) {

                //prepare object request
                GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                        .bucket(bucketName)
                        .key(objectKey)
                        .build();

                //get object from s3
                responseBytes = s3Client.getObjectAsBytes(getObjectRequest);
            }
            byte[] data = responseBytes.asByteArray();

            // Get the path to save the file in the resources directory
            ClassLoader classLoader = getClass().getClassLoader();
            Path filePath = Paths.get(classLoader.getResource(localFilePath).toURI());

            //write data to file
            try (OutputStream os = new FileOutputStream(filePath.toFile())) {
                os.write(data);
                System.out.println("Successfully downloaded credential file");
                System.out.println("file path- "+ filePath);
                System.out.println("file absolute path- "+ filePath.toAbsolutePath());
                System.out.println("file size- "+filePath.toFile().length());
                System.out.println("file write- "+filePath.toFile().canWrite());

//                try {
//                    // Read all lines from the file into a list
//                    List<String> lines = Files.readAllLines(filePath);
//
//                    // Print each line of the file
//                    for (String line : lines) {
//                        System.out.println(line);
//                    }
//                } catch (IOException e) {
//                    System.err.println("Error: " + e.getMessage());
//                }

            }
        }
        catch (S3Exception | IOException | URISyntaxException e) {
            System.err.println("Error credentialDownload - "+e.getMessage());
        }
    }

}
