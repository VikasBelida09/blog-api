package com.example.blogapi.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService{
    @Override
    public String uploadImage(String path, MultipartFile image) throws IOException {
        String name = image.getOriginalFilename();
        String randomId = UUID.randomUUID().toString();
        String updatedFileName = randomId.concat(name.substring(name.lastIndexOf('.')));
        String filePath = path + File.separator + updatedFileName;

        // Create parent directory if it doesn't exist
        File parentDirectory = new File(path);
        if (!parentDirectory.exists()) {
            boolean directoryCreated = parentDirectory.mkdirs();
            System.out.println(directoryCreated);
        }

        // Copy the image content to the file
        Files.copy(image.getInputStream(), Paths.get(filePath));

        return updatedFileName;
    }


    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath=path+File.separator+fileName;
        return new FileInputStream( fullPath);
    }
}
/***
 *
 * images/111-232-ed-sass.png
 *
 */
