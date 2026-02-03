package com.florianbardin.vitisapi.winery;

import org.springframework.data.jpa.domain.Specification;

public class WinerySpecs {
    public static Specification<Winery> hasRegion(String region) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("region"), region);
    }
}
