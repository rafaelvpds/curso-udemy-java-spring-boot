package com.cursoudemy.persons.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FileStorageExceptions extends RuntimeException {

    public FileStorageExceptions(String msg) {
        super(msg);
    }

    public FileStorageExceptions(String msg, Throwable cause) {
        super(msg, cause);
    }

}
