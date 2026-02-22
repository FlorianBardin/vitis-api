package com.florianbardin.vitisapi.winery;

import com.florianbardin.vitisapi.winery.dto.WineryDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("wineries")
@Tag(name = "Wineries", description = "Wineries management and research operations")
public class WineryController {

    private final WineryService wineryService;

    public WineryController(WineryService wineryService) {
        this.wineryService = wineryService;
    }

    @GetMapping
    @Operation(summary = "Search wineries", description = "Returns a paginated and sorted list of wineries matching the optional search criteria.")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    public Page<WineryDto> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String region,
            @ParameterObject @PageableDefault Pageable pageable
    ) {
        return wineryService.search(name, region, pageable);
    }

    @GetMapping("{id}")
    @Operation(summary = "Get a winery by ID", description = "Returns a single winery based on the provided ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Winery found successfully"),
            @ApiResponse(responseCode = "404", description = "Winery not found")
    })
    public WineryDto findById(@PathVariable Integer id) {
        return wineryService.findById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new winery", description = "Adds a new winery to the database. Validates the input data.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Winery created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data (validation error)")
    })
    public WineryDto create(@Valid @RequestBody WineryDto wineryDto) {
        return wineryService.create(wineryDto);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete a winery", description = "Removes a winery from the database by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Winery deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Winery not found")
    })
    public void delete(@PathVariable Integer id) {
        wineryService.delete(id);
    }

    @PutMapping("{id}")
    @Operation(summary = "Update an existing winery", description = "Updates the information of a winery based on its ID. Validates the input data.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Winery updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data (validation error)"),
            @ApiResponse(responseCode = "404", description = "Winery not found")
    })
    public WineryDto update(@PathVariable Integer id, @Valid @RequestBody WineryDto wineryDto) {
        return wineryService.update(id, wineryDto);
    }
}