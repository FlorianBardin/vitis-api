package com.florianbardin.vitisapi.wine;

import com.florianbardin.vitisapi.wine.dto.WineDto;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("wines")
public class WineController {

    private final WineService wineService;

    public WineController(WineService wineService) {
        this.wineService = wineService;
    }

    @GetMapping
    public Page<WineDto> search(
            @RequestParam(required = false) WineType type,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) Integer vintage,
            @RequestParam(required = false) Double minimumPrice,
            @RequestParam(required = false) Double maximumPrice,
            @ParameterObject @PageableDefault(sort = "id") Pageable pageable
    ) {
        return wineService.search(
                type,
                color,
                vintage,
                minimumPrice,
                maximumPrice,
                pageable);
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
