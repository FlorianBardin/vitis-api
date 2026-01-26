package com.florianbardin.vitisapi.wine;

import org.springframework.data.jpa.domain.Specification;

public class WineSpecs {
    public static Specification<Wine> hasType(WineType wineType) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("type"), wineType);
    }
}
