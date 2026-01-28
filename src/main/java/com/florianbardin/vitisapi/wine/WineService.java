package com.florianbardin.vitisapi.wine;

import com.florianbardin.vitisapi.exception.WineNotFoundException;
import com.florianbardin.vitisapi.exception.WineryNotFoundException;
import com.florianbardin.vitisapi.wine.dto.WineDto;
import com.florianbardin.vitisapi.winery.Winery;
import com.florianbardin.vitisapi.wine.dto.WineDtoMapper;
import com.florianbardin.vitisapi.winery.WineryRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<WineDto> search(
            WineType type,
            String color,
            Integer vintage,
            Double minimumPrice,
            Double maximumPrice
    ) {
        Specification<Wine> spec = (root, query, criteriaBuilder)
                -> criteriaBuilder.conjunction();

        if (type != null) {
            spec = spec.and(WineSpecs.hasType(type));
        } else if (color != null) {
            spec = spec.and(WineSpecs.hasColor(color));
        } else if (vintage != null) {
            spec = spec.and(WineSpecs.hasVintage(vintage));
        } else if (minimumPrice != null) {
            spec = spec.and(WineSpecs.hasMinimumPrice(minimumPrice));
        } else if (maximumPrice != null) {
            spec = spec.and(WineSpecs.hasMaximumPrice(maximumPrice));
        }

        return wineRepository.findAll(spec)
                .stream()
                .map(wineDtoMapper::toWineDto)
                .collect(toList());
    }

    public WineDto findById(Integer id) {
        Wine wine = wineRepository.findById(id)
                .orElseThrow(() -> new WineNotFoundException(id));

        return wineDtoMapper.toWineDto(wine);
    }

    @Transactional
    public WineDto create(WineDto wineDto) {
        Winery winery = wineryRepository.findById(wineDto.wineryId())
                .orElseThrow(() -> new WineryNotFoundException(wineDto.wineryId()));

        Wine newWine = wineDtoMapper.toWine(wineDto);
        newWine.setWinery(winery);

        Wine savedWine = wineRepository.save(newWine);

        return wineDtoMapper.toWineDto(savedWine);
    }

    @Transactional
    public WineDto update(Integer id, WineDto wineDto) {
        Wine updatedWine = wineRepository.findById(id)
                .orElseThrow(() -> new WineNotFoundException(id));

        wineDtoMapper.updateWineFromDto(wineDto, updatedWine);

        if (wineDto.wineryId() != null && !wineDto.wineryId().equals(updatedWine.getWinery().getId())) {
            Winery winery = wineryRepository.findById(wineDto.wineryId())
                    .orElseThrow(() -> new WineryNotFoundException(wineDto.wineryId()));

            updatedWine.setWinery(winery);
        }

        Wine savedWine = wineRepository.save(updatedWine);

        return wineDtoMapper.toWineDto(savedWine);
    }

    @Transactional
    public void delete(Integer id) {
        if (!wineRepository.existsById(id)) {
            throw new WineNotFoundException(id);
        }

        wineRepository.deleteById(id);
    }
}
