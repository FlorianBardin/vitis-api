package com.florianbardin.vitisapi.mapper;

import com.florianbardin.vitisapi.dto.WineryDto;
import com.florianbardin.vitisapi.entity.Winery;

public class WineryDtoMapper {
    public Winery toWinery(WineryDto wineryDto) {
        Winery winery = new Winery();

        winery.setId(wineryDto.id());
        winery.setName(wineryDto.name());
        winery.setRegion(wineryDto.region());
        winery.setAddress(wineryDto.address());

        return winery;
    }
}
