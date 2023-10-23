package com.cursoudemy.persons.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.cursoudemy.persons.config.FileStorageConfig;
import com.cursoudemy.persons.exceptions.FileStorageExceptions;
import com.cursoudemy.persons.exceptions.MyFileNotFoundException;

@Service

public class FileStorageService {
    // Caminho completo ate a pasta onde os arquivos serao salvos
    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageConfig fileStorageConfig) {
        Path path = Paths.get(fileStorageConfig.getUploadDir())
                .toAbsolutePath()
                .normalize();
        this.fileStorageLocation = path;
        try {
            Files.createDirectories(this.fileStorageLocation);

        } catch (Exception e) {
            // TODO: handle exception
            throw new FileStorageExceptions("Could not create the directory where the uploaded files will be stoage! ",
                    e);
        }
    }

    public String storeFile(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // filename..txt
            if (filename.contains("..")) {
                throw new FileStorageExceptions("Sorry! Filename contains invalid path sequence " + filename);
            }
            // Esta criando um arquivo vazio no caiminho especifico
            Path targetLocation = this.fileStorageLocation.resolve(filename);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return filename;
        } catch (Exception e) {
            // TODO: handle exception
            throw new FileStorageExceptions("Could not store the file! " + filename + " . Please try again!", e);
        }

    }

    public Resource loadFileAsResource(String filename) {
        try {
            // obtem o caminho ate o arquivo
            Path filePath = this.fileStorageLocation.resolve(filename).normalize();
            // Ele obtem o ressource atraves do arquivo
            Resource resource = new UrlResource(filePath.toUri());
            // Veficar se o ressource existe
            if (resource.exists())
                return resource;
            else
                throw new MyFileNotFoundException("File not Found");
        } catch (Exception e) {

            throw new MyFileNotFoundException("File not Found " + filename, e);
        }
    }
}