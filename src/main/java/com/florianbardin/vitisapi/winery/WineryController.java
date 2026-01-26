package com.florianbardin.vitisapi.winery;

import com.florianbardin.vitisapi.winery.dto.WineryDto;
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
    public WineryDto create(@Valid @RequestBody WineryDto wineryDto) {
        return wineryService.create(wineryDto);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        wineryService.delete(id);
    }

    @PutMapping("{id}")
    public WineryDto update(@PathVariable Integer id, @Valid @RequestBody WineryDto wineryDto) {
        return wineryService.update(id, wineryDto);
    }
}
