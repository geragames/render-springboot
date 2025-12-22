package com.fich.sarh.plantofpositions.infrastructure.adapter.output.persistence.adapter;

import com.fich.sarh.common.WebAdapter;
import com.fich.sarh.plantofpositions.application.ports.persistence.PlantOfPositionSearchSpiPort;
import com.fich.sarh.plantofpositions.domain.model.PlantFilter;
import com.fich.sarh.plantofpositions.domain.model.PlantOfPositionDto;
import com.fich.sarh.plantofpositions.domain.model.PlantProjectionDTO;
import com.fich.sarh.plantofpositions.infrastructure.adapter.output.persistence.repository.PlantOfPositionCustomRepository;
import com.fich.sarh.plantofpositions.infrastructure.adapter.output.persistence.repository.PlantOfPositionRepository;
import com.fich.sarh.plantofpositions.infrastructure.adapter.output.persistence.repository.PlantSpecifications;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@WebAdapter
public class PlantOfPositionSearchAdapter implements PlantOfPositionSearchSpiPort {

   private final PlantOfPositionRepository repository;

   Logger logger = LoggerFactory.getLogger(this.getClass());

   private final PlantOfPositionCustomRepository customerRepository;
    public PlantOfPositionSearchAdapter(PlantOfPositionRepository repository, PlantOfPositionCustomRepository customerRepository) {
        this.repository = repository;

        this.customerRepository = customerRepository;
    }
    @Override
    public List<PlantProjectionDTO> search(PlantFilter filter) {

        List<PlantProjectionDTO> res = customerRepository.findAllProjection(filter);


       // List<PlantOfPositionDto> results = repository.findAll(PlantSpecifications.createSpecification(filter), PlantOfPositionDto.class);

        return  res;
    }
}
