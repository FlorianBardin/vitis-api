package com.florianbardin.vitisapi.winery;

import org.springframework.data.jpa.domain.Specification;

public class WinerySpecs {
    public static Specification<Winery> hasRegion(String region) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        criteriaBuilder.lower(root.get("region")),
                        region.toLowerCase()
                );
    }

    public static Specification<Winery> nameContains(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("name")),
                        "%" +  name.toLowerCase() + "%"
                );
    }
}
