package com.florianbardin.vitisapi.winery.dto;

import jakarta.validation.constraints.NotBlank;

public record WineryDto(
        Integer id,
        @NotBlank
        String name,
        @NotBlank
        String region,
        String address
) {
}
