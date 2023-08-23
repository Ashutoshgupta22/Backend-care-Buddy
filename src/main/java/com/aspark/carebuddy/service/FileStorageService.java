package com.aspark.carebuddy.service;

import com.aspark.carebuddy.exception.FileStorageException;
import com.aspark.carebuddy.exception.FilesNotFoundException;
import com.google.api.client.util.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

   // @Value("${file.upload-dir}")
    private final String uploadDir="files";

    public String storeFile(MultipartFile file, String fileName, int nurseId) {

        Path targetLocation = getNurseFileStorageLocation(nurseId).resolve(fileName);
        try {
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return targetLocation.toAbsolutePath().toString();

        } catch (IOException e) {
            throw new FileStorageException("Could not store file "+ fileName, e);
        }
    }

    public UrlResource loadFileAsResource(String fileName, int nurseId) {

        Path filePath = getNurseFileStorageLocation(nurseId).resolve(fileName).normalize();
        try {
            UrlResource resource = new UrlResource(filePath.toUri());

            if (resource.exists())
                return resource;
            else
                throw new FilesNotFoundException("File not found: "+ fileName);


        } catch (MalformedURLException e) {
            throw new FilesNotFoundException("File not found: "+ fileName, e);
        }

    }

    public Path getNurseFileStorageLocation(int nurseId) {

        Path nurseDir = Path.of(uploadDir,"nurse", "profile-pic", String.valueOf(nurseId));

        if (! Files.exists(nurseDir)) {
            try {
                Files.createDirectories(nurseDir);
            } catch (IOException e) {
                throw new FileStorageException("Could not create directory for nurse "+ nurseId, e);
            }
        }

        return nurseDir;
    }
}
