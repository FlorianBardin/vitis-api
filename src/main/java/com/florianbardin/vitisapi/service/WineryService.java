package com.florianbardin.vitisapi.service;

import com.florianbardin.vitisapi.entity.Winery;
import com.florianbardin.vitisapi.repository.WineryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WineryService {

    private final WineryRepository wineryRepository;

    public WineryService(WineryRepository wineryRepository) {
        this.wineryRepository = wineryRepository;
    }

    public List<Winery> findAll() {
        return wineryRepository.findAll();
    }

    public Winery findById(Integer id) {
        return wineryRepository.findById(id).orElse(null);
    }

    public void insertWinery(Winery winery) {
        wineryRepository.save(winery);
    }
}
