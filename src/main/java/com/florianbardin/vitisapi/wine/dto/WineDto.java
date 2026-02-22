package com.florianbardin.vitisapi.wine.dto;

import com.florianbardin.vitisapi.wine.WineType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "Data Transfer Object representing a Wine")
public record WineDto(

        @Schema(description = "Unique identifier of the wine", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
        Integer id,

        @Schema(description = "Name of the cuvée or wine", example = "Pavillon Rouge")
        @NotBlank(message = "Name is required")
        @Size(max = 255, message = "Name must be less than 255 characters")
        String name,

        @Schema(description = "Year of harvest (vintage)", example = "2018")
        @NotNull(message = "Vintage year is required")
        @Min(value = 1900, message = "Vintage must be later than 1900")
        @Max(value = 2100, message = "Vintage must be a valid year (up to 2100)")
        Integer vintage,

        @Schema(description = "Type of the wine (e.g., DRY, SWEET, SPARKLING)", example = "DRY")
        @NotNull(message = "Wine type is required")
        WineType type,

        @Schema(description = "Color of the wine", example = "Red")
        @NotBlank(message = "Color is required")
        String color,

        @Schema(description = "Unit price in euros", example = "150.50")
        @PositiveOrZero(message = "Price must be greater than or equal to 0")
        Double price,

        @Schema(description = "Quantity available in stock", example = "12")
        @PositiveOrZero(message = "Stock must be greater than or equal to 0")
        Integer stock,

        @Schema(description = "ID of the winery producing this wine", example = "1")
        @NotNull(message = "Winery ID is required")
        Integer wineryId
) {
}