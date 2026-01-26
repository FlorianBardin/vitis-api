package com.florianbardin.vitisapi.winery;

import com.florianbardin.vitisapi.winery.dto.WineryDto;
import com.florianbardin.vitisapi.winery.dto.WineryDtoMapper;
import com.florianbardin.vitisapi.wine.WineRepository;
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
    private final WineRepository wineRepository;

    public WineryService(WineryRepository wineryRepository, WineryDtoMapper wineryDtoMapper, WineRepository wineRepository) {
        this.wineryRepository = wineryRepository;
        this.wineryDtoMapper = wineryDtoMapper;
        this.wineRepository = wineRepository;
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
    public WineryDto create(WineryDto wineryDto) {
        Winery newWinery = wineryRepository.save(wineryDtoMapper.toWinery(wineryDto));

        return wineryDtoMapper.toWineryDto(newWinery);
    }

    @Transactional
    public void delete(Integer id) {
        if (!wineryRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Winery with id " + id + " not found");
        }

        if (wineRepository.existsByWineryId(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Winery with id " + id + " still contains wines");
        }

        wineryRepository.deleteById(id);
    }

    @Transactional
    public WineryDto update(Integer id, WineryDto wineryDto) {
        Winery wineryToUpdate = wineryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Winery with id " + id + " not found"
                ));
        wineryDtoMapper.updateWineryFromDto(wineryDto, wineryToUpdate);

        Winery savedWinery = wineryRepository.save(wineryToUpdate);

        return wineryDtoMapper.toWineryDto(savedWinery);
    }
}
