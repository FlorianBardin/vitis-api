package com.florianbardin.vitisapi.controller;

import com.florianbardin.vitisapi.dto.WineDto;
import com.florianbardin.vitisapi.service.WineService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("wines")
public class WineController {

    private final WineService wineService;

    public WineController(WineService wineService) {
        this.wineService = wineService;
    }

    @GetMapping
    public List<WineDto> findAll() {
        return wineService.findAll();
    }

    @GetMapping("{id}")
    public WineDto findById(@PathVariable Integer id) {
        return wineService.findById(id);
    }

    @PostMapping
    public WineDto create(@Valid @RequestBody WineDto wineDto) {
        return wineService.insertWine(wineDto);
    }
}
