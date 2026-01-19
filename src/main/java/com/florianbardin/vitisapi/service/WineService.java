package com.florianbardin.vitisapi.service;

import com.florianbardin.vitisapi.entity.Wine;
import com.florianbardin.vitisapi.repository.WineRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class WineService {

    private final WineRepository wineRepository;

    public WineService(WineRepository wineRepository) {
        this.wineRepository = wineRepository;
    }

    public List<Wine> findAll() {
        return wineRepository.findAll();
    }

    public Wine findById(Integer id) {
        return wineRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Wine with id " + id + " not found"
                ));
    }
}
