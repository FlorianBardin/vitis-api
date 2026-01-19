package com.florianbardin.vitisapi.service;

import com.florianbardin.vitisapi.entity.Winery;
import com.florianbardin.vitisapi.repository.WineryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class WineryService {

    private final WineryRepository wineryRepository;

    public WineryService(WineryRepository wineryRepository) {
        this.wineryRepository = wineryRepository;
    }

    public List<Winery> findAll() {
        return wineryRepository.findAll();
    }

    public Winery findById(Integer id) {
        return wineryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Winery with id " + id + " not found"
                ));
    }

    @Transactional
    public void insertWinery(Winery winery) {
        wineryRepository.save(winery);
    }

    @Transactional
    public void deleteWinery(Integer id) {
        wineryRepository.deleteById(id);
    }

    @Transactional
    public void updateWinery(Integer id, Winery winery) {
        Winery updatedWinery = wineryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Winery with id " + id + " not found"
                ));
        updatedWinery.setName(winery.getName());
        updatedWinery.setRegion(winery.getRegion());
        updatedWinery.setAddress(winery.getAddress());

        wineryRepository.save(updatedWinery);
    }
}
