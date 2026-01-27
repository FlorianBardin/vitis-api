package com.florianbardin.vitisapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class WineryNotFoundException extends ResponseStatusException {
    public WineryNotFoundException(Integer id) {
        super(HttpStatus.NOT_FOUND, "Winery with id " + id + " not found");
    }
}
