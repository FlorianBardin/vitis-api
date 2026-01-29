package com.florianbardin.vitisapi.wine.dto;

import com.florianbardin.vitisapi.wine.WineType;
import jakarta.validation.constraints.*;

public record WineDto(
        Integer id,

        @NotBlank(message = "Name is required")
        @Size(max = 255, message = "Name must be less than 255 characters")
        String name,

        @NotNull(message = "Vintage year is required")
        @Min(value = 1900, message = "Vintage must be later than 1900")
        @Max(value = 2100, message = "Vintage must be a valid year (up to 2100)")
        Integer vintage,

        @NotNull(message = "Wine type is required")
        WineType type,

        @NotBlank(message = "Color is required")
        String color,

        @PositiveOrZero(message = "Price must be greater than or equal to 0")
        Double price,

        @PositiveOrZero(message = "Stock must be greater than or equal to 0")
        Integer stock,

        @NotNull(message = "Winery ID is required")
        Integer wineryId
) {
}
