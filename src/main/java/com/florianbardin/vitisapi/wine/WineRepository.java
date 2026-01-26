package com.florianbardin.vitisapi.wine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WineRepository extends JpaRepository<Wine, Integer>, JpaSpecificationExecutor<Wine> {
    boolean existsByWineryId(Integer wineryId);
}
