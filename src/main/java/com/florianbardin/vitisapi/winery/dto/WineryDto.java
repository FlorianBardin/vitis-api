package com.florianbardin.vitisapi.winery.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record WineryDto(
        Integer id,

        @NotBlank(message = "Name is required")
        @Size(max = 255, message = "Name must be less than 255 characters")
        String name,

        @NotBlank(message = "Region is required")
        @Size(max = 100, message = "Region must be less than 100 characters")
        String region,

        @Size(max = 255, message = "Address must be less than 255 characters")
        String address
) {
}
