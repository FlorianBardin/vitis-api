package com.florianbardin.vitisapi.controller;

import com.florianbardin.vitisapi.entity.Wine;
import com.florianbardin.vitisapi.service.WineService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("wines")
public class WineController {

    private final WineService wineService;

    public WineController(WineService wineService) {
        this.wineService = wineService;
    }

    @GetMapping
    public List<Wine> findAll() {
        return wineService.findAll();
    }

}
