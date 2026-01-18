package com.florianbardin.vitisapi.repository;

import com.florianbardin.vitisapi.entity.Wine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WineRepository extends JpaRepository<Wine, Integer> {
}
