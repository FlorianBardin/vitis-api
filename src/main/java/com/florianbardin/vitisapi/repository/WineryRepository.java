package com.florianbardin.vitisapi.repository;

import com.florianbardin.vitisapi.entity.Winery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WineryRepository extends JpaRepository<Winery, Integer> {
}
