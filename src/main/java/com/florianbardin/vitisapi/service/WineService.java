package com.florianbardin.vitisapi.service;

import com.florianbardin.vitisapi.entity.Wine;
import com.florianbardin.vitisapi.repository.WineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
                .orElseThrow(() -> new IllegalArgumentException(
                        "Wine with id " + id + " not found"
                ));
    }
}
