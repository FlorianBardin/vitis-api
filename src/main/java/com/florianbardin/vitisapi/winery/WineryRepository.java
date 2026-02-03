package com.florianbardin.vitisapi.winery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WineryRepository extends JpaRepository<Winery, Integer>, JpaSpecificationExecutor<Winery> {
}
