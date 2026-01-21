package com.florianbardin.vitisapi.mapper;

import com.florianbardin.vitisapi.dto.WineDto;
import com.florianbardin.vitisapi.entity.Wine;
import org.springframework.stereotype.Service;

@Service
public class WineDtoMapper {
    public Wine toWine(WineDto wineDto) {
        Wine wine = new Wine();
        wine.setName(wineDto.name());
        wine.setVintage(wineDto.vintage());
        wine.setColor(wineDto.color());
        wine.setPrice(wineDto.price());
        wine.setStock(wineDto.stock());

        return wine;
    }
}
