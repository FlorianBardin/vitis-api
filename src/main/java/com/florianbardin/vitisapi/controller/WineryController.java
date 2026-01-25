package com.florianbardin.vitisapi.controller;

import com.florianbardin.vitisapi.dto.WineryDto;
import com.florianbardin.vitisapi.entity.Winery;
import com.florianbardin.vitisapi.service.WineryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("wineries")
public class WineryController {

    private final WineryService wineryService;

    public WineryController(WineryService wineryService) {
        this.wineryService = wineryService;
    }

    @GetMapping
    public List<WineryDto> getWineries() {
        return wineryService.findAll();
    }

    @GetMapping("{id}")
    public WineryDto findById(@PathVariable Integer id) {
        return wineryService.findById(id);
    }

    @PostMapping
    public void insertWinery(@Valid @RequestBody Winery winery) {
        wineryService.insertWinery(winery);
    }

    @DeleteMapping("{id}")
    public void deleteWinery(@PathVariable Integer id) {
        wineryService.deleteWinery(id);
    }

    @PutMapping("{id}")
    public void updateWinery(@PathVariable Integer id, @Valid @RequestBody Winery winery) {
        wineryService.updateWinery(id, winery);
    }
}
