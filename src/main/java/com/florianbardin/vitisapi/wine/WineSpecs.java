package com.florianbardin.vitisapi.wine;

import org.springframework.data.jpa.domain.Specification;

public class WineSpecs {
    public static Specification<Wine> hasType(WineType wineType) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("type"), wineType);
    }

    public static Specification<Wine> hasColor(String color) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        criteriaBuilder.lower(root.get("color")),
                        color.toLowerCase()
                );
    }

    public static Specification<Wine> hasVintage(Integer vintage) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("vintage"), vintage);
    }

    public static Specification<Wine> hasMaximumPrice(Double maximumPrice) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("price"), maximumPrice);
    }

    public static Specification<Wine> hasMinimumPrice(Double minimumPrice) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minimumPrice);
    }
}
