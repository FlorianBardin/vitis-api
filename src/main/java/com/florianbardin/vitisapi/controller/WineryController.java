package com.florianbardin.vitisapi.controller;

import com.florianbardin.vitisapi.entity.Winery;
import com.florianbardin.vitisapi.repository.WineryRepository;
import com.florianbardin.vitisapi.service.WineryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("wineries")
public class WineryController {

    private final WineryService wineryService;

    public WineryController(WineryService wineryService) {
        this.wineryService = wineryService;
    }

    @GetMapping
    public List<Winery> getWineries() {
        return wineryService.findAll();
    }

}
