package com.florianbardin.vitisapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class WineNotFoundException extends ResponseStatusException {
    public WineNotFoundException(Integer id) {
        super(HttpStatus.NOT_FOUND, "Wine with id " + id + " not found");
    }
}
