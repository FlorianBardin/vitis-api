package com.florianbardin.vitisapi.winery;

import com.florianbardin.vitisapi.exception.WineryNotFoundException;
import com.florianbardin.vitisapi.winery.dto.WineryDto;
import com.florianbardin.vitisapi.winery.dto.WineryDtoMapper;
import com.florianbardin.vitisapi.wine.WineRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

    public Page<WineryDto> search(String name, String region, Pageable pageable) {
        Specification<Winery> spec = (root, query, criteriaBuilder)
                -> criteriaBuilder.conjunction();

        if (name != null) {
            spec = spec.and(WinerySpecs.nameContains(name));
        }
        if (region != null) {
            spec = spec.and(WinerySpecs.hasRegion(region));
        }

        return wineryRepository.findAll(spec, pageable).map(wineryDtoMapper::toWineryDto);
    }

    public WineryDto findById(Integer id) {
        Winery winery = wineryRepository.findById(id)
                .orElseThrow(() -> new WineryNotFoundException(id));

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
            throw new WineryNotFoundException(id);
        }

        if (wineRepository.existsByWineryId(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Winery with id " + id + " still contains wines");
        }

        wineryRepository.deleteById(id);
    }

    @Transactional
    public WineryDto update(Integer id, WineryDto wineryDto) {
        Winery wineryToUpdate = wineryRepository.findById(id)
                .orElseThrow(() -> new WineryNotFoundException(id));
        wineryDtoMapper.updateWineryFromDto(wineryDto, wineryToUpdate);

        Winery savedWinery = wineryRepository.save(wineryToUpdate);

        return wineryDtoMapper.toWineryDto(savedWinery);
    }
}
