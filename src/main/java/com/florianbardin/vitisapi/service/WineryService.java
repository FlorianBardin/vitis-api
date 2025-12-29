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

    public void deleteWinery(Integer id) {
        wineryRepository.deleteById(id);
    }

    public void updateWinery(Integer id, Winery winery) {
        Winery updatedWinery = wineryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No such winery found with id: " + id
                ));
        updatedWinery.setName(winery.getName());
        updatedWinery.setRegion(winery.getRegion());
        updatedWinery.setAddress(winery.getAddress());

        wineryRepository.save(updatedWinery);
    }
}
