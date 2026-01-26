package com.florianbardin.vitisapi.wine;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WineRepository extends JpaRepository<Wine, Integer> {
    boolean existsByWineryId(Integer wineryId);
}
