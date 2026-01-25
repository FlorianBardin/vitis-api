package com.florianbardin.vitisapi.service;

import com.florianbardin.vitisapi.dto.WineryDto;
import com.florianbardin.vitisapi.entity.Winery;
import com.florianbardin.vitisapi.mapper.WineryDtoMapper;
import com.florianbardin.vitisapi.repository.WineryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional(readOnly = true)
public class WineryService {

    private final WineryRepository wineryRepository;
    private final WineryDtoMapper wineryDtoMapper;

    public WineryService(WineryRepository wineryRepository, WineryDtoMapper wineryDtoMapper) {
        this.wineryRepository = wineryRepository;
        this.wineryDtoMapper = wineryDtoMapper;
    }

    public List<WineryDto> findAll() {
        return wineryRepository.findAll()
                .stream()
                .map(wineryDtoMapper::toWineryDto)
                .collect(toList());
    }

    public WineryDto findById(Integer id) {
        Winery winery = wineryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Winery with id " + id + " not found"
                ));

        return wineryDtoMapper.toWineryDto(winery);
    }

    @Transactional
    public WineryDto insertWinery(Winery winery) {
        Winery newWinery = wineryRepository.save(winery);

        return wineryDtoMapper.toWineryDto(newWinery);
    }

    @Transactional
    public void deleteWinery(Integer id) {
        if (!wineryRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Winery with id " + id + " not found");
        }

        wineryRepository.deleteById(id);
    }

    @Transactional
    public WineryDto updateWinery(Integer id, WineryDto wineryDto) {
        Winery wineryToUpdate = wineryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Winery with id " + id + " not found"
                ));
        wineryDtoMapper.updateWineryFromDto(wineryDto, wineryToUpdate);

        Winery savedWinery = wineryRepository.save(wineryToUpdate);

        return wineryDtoMapper.toWineryDto(savedWinery);
    }
}
