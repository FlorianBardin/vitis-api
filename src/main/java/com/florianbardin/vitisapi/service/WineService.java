package com.florianbardin.vitisapi.service;

import com.florianbardin.vitisapi.dto.WineDto;
import com.florianbardin.vitisapi.entity.Wine;
import com.florianbardin.vitisapi.mapper.WineDtoMapper;
import com.florianbardin.vitisapi.repository.WineRepository;
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

    public WineService(WineRepository wineRepository, WineDtoMapper wineDtoMapper) {
        this.wineRepository = wineRepository;
        this.wineDtoMapper = wineDtoMapper;
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
}
