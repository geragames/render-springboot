package com.fich.sarh.planthistory.infrastructure.adapter.input.rest.controller;

import com.fich.sarh.common.WebAdapter;
import com.fich.sarh.planthistory.application.ports.entrypoint.api.PlantHistoryRetrieveServicePort;
import com.fich.sarh.planthistory.domain.model.PlantHistory;
import com.fich.sarh.planthistory.infrastructure.adapter.input.rest.mapper.PlantHistoryRestMapper;
import com.fich.sarh.planthistory.infrastructure.adapter.input.rest.model.response.PlantHistoryResponse;
import com.fich.sarh.planthistory.infrastructure.adapter.output.persistence.mapper.PlantHistoryMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@WebAdapter
@RestController
@RequestMapping("/planthistory")
public class PlantHistoryController {

    private final PlantHistoryRetrieveServicePort plantHistoryPort;

    public PlantHistoryController(PlantHistoryRetrieveServicePort plantHistoryPort) {
        this.plantHistoryPort = plantHistoryPort;
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('USER')")
    public List<PlantHistoryResponse> findPlantHistoryByPlantId(@PathVariable Long id){
        return PlantHistoryRestMapper.INSTANCE.toPlantHistoryResponseList(plantHistoryPort.getPlantHistoryByPlantId(id));
    }

    @GetMapping("history/{id}")
    @PreAuthorize("hasRole('USER')")
    public PlantHistory findPlantByIdActive(@PathVariable  Long id){
        return plantHistoryPort.findPlantByIdActive(id).get();
    }

    @GetMapping("top/{id}")
    @PreAuthorize("hasRole('USER')")
    public PlantHistory findTopByPlant(@PathVariable Long id){
        PlantHistory plantHistory = plantHistoryPort.fetchTopByPlantIdOrderHistoryIdDesc(id);
        if(plantHistory == null) {
            return null;
        }
        return plantHistoryPort.fetchTopByPlantIdOrderHistoryIdDesc(id);
    }

}
