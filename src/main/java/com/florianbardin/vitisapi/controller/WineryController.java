package com.florianbardin.vitisapi.controller;

import com.florianbardin.vitisapi.entity.Winery;
import com.florianbardin.vitisapi.service.WineryService;
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
    public List<Winery> getWineries() {
        return wineryService.findAll();
    }

    @GetMapping("{id}")
    public Winery findById(@PathVariable Integer id) {
        return wineryService.findById(id);
    }

    @PostMapping
    public void insertWinery(@RequestBody Winery winery) {
        wineryService.insertWinery(winery);
    }
}
