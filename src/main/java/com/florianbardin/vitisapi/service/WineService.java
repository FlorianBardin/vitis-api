package com.florianbardin.vitisapi.service;

import com.florianbardin.vitisapi.dto.WineDto;
import com.florianbardin.vitisapi.entity.Wine;
import com.florianbardin.vitisapi.entity.Winery;
import com.florianbardin.vitisapi.mapper.WineDtoMapper;
import com.florianbardin.vitisapi.repository.WineRepository;
import com.florianbardin.vitisapi.repository.WineryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional(readOnly = true)
public class WineService {

    private final WineRepository wineRepository;
    private final WineDtoMapper wineDtoMapper;
    private final WineryRepository wineryRepository;

    public WineService(WineRepository wineRepository, WineDtoMapper wineDtoMapper, WineryRepository wineryRepository) {
        this.wineRepository = wineRepository;
        this.wineDtoMapper = wineDtoMapper;
        this.wineryRepository = wineryRepository;
    }

    public List<WineDto> findAll() {
        return wineRepository.findAll()
                .stream()
                .map(wineDtoMapper::toWineDto)
                .collect(toList());
    }

    public WineDto findById(Integer id) {
        Wine wine = wineRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Wine with id " + id + " not found"
                ));

        return wineDtoMapper.toWineDto(wine);
    }

    @Transactional
    public WineDto insertWine(WineDto wineDto) {
        Winery winery = wineryRepository.findById(wineDto.wineryId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Winery with id " + wineDto.wineryId() + " not found"
                ));

        Wine newWine = wineDtoMapper.toWine(wineDto);
        newWine.setWinery(winery);

        Wine savedWine = wineRepository.save(newWine);

        return wineDtoMapper.toWineDto(savedWine);
    }

    @Transactional
    public WineDto updateWine(Integer id, WineDto wineDto) {
        Wine updatedWine = wineRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Wine with id " + id + " not found"
                ));

        wineDtoMapper.updateWineFromDto(wineDto, updatedWine);

        if (wineDto.wineryId() != null && !wineDto.wineryId().equals(updatedWine.getWinery().getId())) {
            Winery winery = wineryRepository.findById(wineDto.wineryId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Winery with id " + id + " not found"
                    ));

            updatedWine.setWinery(winery);
        }

        return wineDtoMapper.toWineDto(updatedWine);
    }
}
