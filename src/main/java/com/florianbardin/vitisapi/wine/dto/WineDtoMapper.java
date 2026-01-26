package com.florianbardin.vitisapi.wine.dto;

import com.florianbardin.vitisapi.wine.Wine;
import org.springframework.stereotype.Service;

@Service
public class WineDtoMapper {
    public Wine toWine(WineDto wineDto) {
        Wine wine = new Wine();
        wine.setId(wineDto.id());
        wine.setName(wineDto.name());
        wine.setVintage(wineDto.vintage());
        wine.setType(wineDto.type());
        wine.setColor(wineDto.color());
        wine.setPrice(wineDto.price());
        wine.setStock(wineDto.stock());

        return wine;
    }

    public WineDto toWineDto(Wine wine) {
        return new WineDto(
                wine.getId(),
                wine.getName(),
                wine.getVintage(),
                wine.getType(),
                wine.getColor(),
                wine.getPrice(),
                wine.getStock(),
                wine.getWinery().getId()
        );
    }

    public void updateWineFromDto(WineDto wineDto, Wine wine) {
        wine.setName(wineDto.name());
        wine.setVintage(wineDto.vintage());
        wine.setType(wineDto.type());
        wine.setColor(wineDto.color());
        wine.setPrice(wineDto.price());
        wine.setStock(wineDto.stock());
    }
}
