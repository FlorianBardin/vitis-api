package com.florianbardin.vitisapi.winery.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Data Transfer Object representing a Winery (Estate/Vineyard)")
public record WineryDto(

        @Schema(description = "Unique identifier of the winery", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
        Integer id,

        @Schema(description = "Name of the winery", example = "Château Margaux")
        @NotBlank(message = "Name is required")
        @Size(max = 255, message = "Name must be less than 255 characters")
        String name,

        @Schema(description = "Geographic region", example = "Bordeaux")
        @NotBlank(message = "Region is required")
        @Size(max = 100, message = "Region must be less than 100 characters")
        String region,

        @Schema(description = "Physical address of the estate", example = "1 Route de l'Ile, 33460 Margaux")
        @Size(max = 255, message = "Address must be less than 255 characters")
        String address
) {
}