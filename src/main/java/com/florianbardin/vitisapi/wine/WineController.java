package com.florianbardin.vitisapi.wine;

import com.florianbardin.vitisapi.wine.dto.WineDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("wines")
@Tag(name = "Wines", description = "Wines management and research operations")
public class WineController {

    private final WineService wineService;

    public WineController(WineService wineService) {
        this.wineService = wineService;
    }

    @GetMapping
    @Operation(summary = "Search wines", description = "Returns a paginated and sorted list of wines matching the optional search criteria (type, color, vintage, price range).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid query parameters (e.g. unknown type or invalid sort property)")
    })
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
    @Operation(summary = "Get a wine by ID", description = "Returns detailed information about a single wine based on its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Wine found successfully"),
            @ApiResponse(responseCode = "404", description = "Wine not found")
    })
    public WineDto findById(@PathVariable Integer id) {
        return wineService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new wine", description = "Adds a new wine to the database. Validates the input data and ensures the associated winery exists.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Wine created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data (validation error)"),
            @ApiResponse(responseCode = "404", description = "Associated winery not found")
    })
    public WineDto create(@Valid @RequestBody WineDto wineDto) {
        return wineService.create(wineDto);
    }

    @PutMapping("{id}")
    @Operation(summary = "Update an existing wine", description = "Updates the information of a wine based on its ID. Validates the input data.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Wine updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data (validation error)"),
            @ApiResponse(responseCode = "404", description = "Wine or associated winery not found")
    })
    public WineDto update(@PathVariable Integer id, @Valid @RequestBody WineDto wineDto) {
        return wineService.update(id, wineDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a wine", description = "Removes a wine from the database by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Wine deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Wine not found")
    })
    public void delete(@PathVariable Integer id) {
        wineService.delete(id);
    }
}