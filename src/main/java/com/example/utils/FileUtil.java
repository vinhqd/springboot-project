package com.example.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

@Component
public class FileUtil {

    @Value("${uploads.path}")
    private String path;

    public File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    public String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
    }

    public String generateFileName(String fileName) {
        return new Date().getTime() + "-" + fileName.replace(" ", "_");
    }

    public String uploadFile(byte[] bytes, String name) throws IOException {
        String fileName = generateFileName(name);
        String uploadPath = path;
        File uploadRootDir = new File(uploadPath);
        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }
        FileCopyUtils.copy(bytes, new File(uploadPath + "/" + fileName));
        return "/uploads/" + fileName;
    }


}
