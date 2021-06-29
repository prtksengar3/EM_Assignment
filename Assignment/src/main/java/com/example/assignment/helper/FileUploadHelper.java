package com.example.assignment.helper;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUploadHelper {
    public final String Upload_Dir = "/home/extramarks/Desktop/EM_Assignment/Assignment/src/main/resources";

    public void uploadFile(String uploadDir, String fileName,MultipartFile multipartFile){
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
//            throw new IOException("Could not save image file: " + fileName, ioe);
            ioe.printStackTrace();
        }
    }

}
