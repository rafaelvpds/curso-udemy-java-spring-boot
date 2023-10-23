package com.cursoudemy.persons.controller;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cursoudemy.persons.dto.dtoV01.UploadFileResponseDTO;
import com.cursoudemy.persons.service.FileStorageService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/persons/files")
@Tag(name = "File Endpoints")
public class FileController {
    private Logger log = Logger.getLogger(FileController.class.getName());

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/uploadMultiFile")
    public List<UploadFileResponseDTO> uploadMultipleFile(@RequestParam("files") MultipartFile[] files) {
        log.info("uploadFiles ");

        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());

    }

    @PostMapping("/uploadFile")
    public UploadFileResponseDTO uploadFile(@RequestParam("file") MultipartFile file) {
        log.info("uploadFile ");
        var filename = fileStorageService.storeFile(file);
        String fileDowloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/persons/files/downloadFile")
                .path(filename)
                .toUriString();
        return new UploadFileResponseDTO(filename, fileDowloadUri, file.getContentType(), file.getSize());
    }
}
