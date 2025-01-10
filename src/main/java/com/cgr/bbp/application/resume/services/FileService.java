package com.cgr.bbp.application.resume.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cgr.bbp.infrastructure.persistence.entity.file.FileEntity;
import com.cgr.bbp.infrastructure.persistence.entity.resumen.Identity;
import com.cgr.bbp.infrastructure.persistence.repository.file.IFileRepository;

@Service
public class FileService {

    private static final String DIRECTORY_PATH = "C:/mi-carpeta-de-archivos/";
    
    @Autowired
    private IFileRepository fileRepository;
   
      public List<String> saveFiles(List<MultipartFile> files, Identity identity) throws IOException {
        // Crear carpeta si no existe
        createDirectoryIfNotExists(DIRECTORY_PATH);

        List<String> fileNames = new ArrayList<>();
        for (MultipartFile file : files) {
            if (file != null && !file.isEmpty()) {
                // Guardar archivo físicamente
                String fileName = saveFileToDisk(file);

                // Guardar registro en la base de datos
                saveFileEntity(fileName, DIRECTORY_PATH + fileName, identity);

                fileNames.add(fileName);
            }
        }
        return 
        fileNames;
    }

    private void createDirectoryIfNotExists(String path) {
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

       private String saveFileToDisk(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String filePath = DIRECTORY_PATH + fileName;
        Path path = Paths.get(filePath);
        Files.write(path, file.getBytes());
        return fileName;
    }
    
    private void saveFileEntity(String fileName, String filePath, Identity identity) {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setName(fileName);
        fileEntity.setPath(filePath);
        fileEntity.setIdentity(identity);
        fileRepository.save(fileEntity);
    }

}
