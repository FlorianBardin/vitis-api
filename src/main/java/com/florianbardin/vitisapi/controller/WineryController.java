package com.florianbardin.vitisapi.controller;

import com.florianbardin.vitisapi.entity.Winery;
import com.florianbardin.vitisapi.repository.WineryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("wineries")
public class WineryController {

    private final WineryRepository wineryRepository;

    public WineryController(WineryRepository wineryRepository) {
        this.wineryRepository = wineryRepository;
    }

    @GetMapping
    public List<Winery> getWineries() {
        return wineryRepository.findAll();
    }

}
