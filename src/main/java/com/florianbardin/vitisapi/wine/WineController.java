package com.florianbardin.vitisapi.wine;

import com.florianbardin.vitisapi.wine.dto.WineDto;
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
    public List<WineDto> search(
            @RequestParam
    ) {
        return wineService.findAll();
    }

    @GetMapping("{id}")
    public WineDto findById(@PathVariable Integer id) {
        return wineService.findById(id);
    }

    @PostMapping
    public WineDto create(@Valid @RequestBody WineDto wineDto) {
        return wineService.create(wineDto);
    }

    @PutMapping("{id}")
    public WineDto update(@PathVariable Integer id, @Valid @RequestBody WineDto wineDto) {
        return wineService.update(id, wineDto);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id) {
        wineService.delete(id);
    }
}
