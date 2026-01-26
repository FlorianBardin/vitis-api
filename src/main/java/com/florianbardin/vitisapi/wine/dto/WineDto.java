package com.florianbardin.vitisapi.wine.dto;

import com.florianbardin.vitisapi.wine.WineType;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record WineDto(
        Integer id,
        @NotBlank
        String name,
        @NotNull
        @Digits(fraction = 0, integer = 4)
        @PositiveOrZero
        Integer vintage,
        @NotNull
        WineType type,
        @NotBlank
        String color,
        @PositiveOrZero
        Double price,
        @PositiveOrZero
        Integer stock,
        @NotNull
        Integer wineryId
) {
}
