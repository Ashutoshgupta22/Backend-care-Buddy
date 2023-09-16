package com.aspark.carebuddy.aws;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.nio.ByteBuffer;

@Service
public class ImageUploadService {

    private final String bucketName = "care-buddy";

    public String imageUpload(MultipartFile file) {

        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key("nurse/"+file.getOriginalFilename())
                .build();

        try (S3Client s3 = S3Client.builder().build()) {
            s3.putObject(objectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
        }
        catch (Exception e ) {
            e.printStackTrace();
        }

        return null;
    }
}
