package com.florianbardin.vitisapi.winery;

import com.florianbardin.vitisapi.winery.dto.WineryDto;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public Page<WineryDto> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String region,
            @ParameterObject @PageableDefault Pageable pageable
    ) {
        return wineryService.search(name, region, pageable);
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
