package com.fich.sarh.plantofpositions.application.ports.entrypoint.api;

import com.fich.sarh.plantofpositions.domain.model.PlantOfPosition;
import com.fich.sarh.plantofpositions.domain.model.PlantFilter;
import com.fich.sarh.plantofpositions.domain.model.PlantOfPositionDto;
import com.fich.sarh.plantofpositions.domain.model.PlantProjectionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlantOfPositionSearchApiPort {

    List<PlantProjectionDTO> search(PlantFilter filter);


}
