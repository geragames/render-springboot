package com.fich.sarh.plantofpositions.infrastructure.adapter.input.rest.controller;

import com.fich.sarh.common.WebAdapter;
import com.fich.sarh.movement.application.ports.entrypoint.api.MovementRetrieveServicePort;
import com.fich.sarh.plantofpositions.application.ports.entrypoint.api.*;
import com.fich.sarh.plantofpositions.domain.model.*;
import com.fich.sarh.plantofpositions.infrastructure.adapter.input.rest.mapper.PlantOfPositionRestMapper;
import com.fich.sarh.plantofpositions.infrastructure.adapter.input.rest.model.request.PlantOfPositionRequest;
import com.fich.sarh.plantofpositions.infrastructure.adapter.input.rest.model.response.PlantOfPositionResponse;
import com.fich.sarh.plantofpositions.infrastructure.adapter.output.persistence.entity.PlantOfPositionEntity;
import com.fich.sarh.plantofpositions.infrastructure.adapter.output.persistence.repository.PlantSpecifications;
import com.fich.sarh.position.application.ports.entrypoint.api.PositionRetrieveServicePort;
import com.fich.sarh.position.application.ports.entrypoint.api.PositionUpdateServicePort;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebAdapter
@RestController
@RequestMapping("/plant")
public class PlantOfPositionController {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private final PlantOfPositionRetrieveApiPort retrievePlantService;
    private final PlantOfPositionSaveApiPort savePlantService;
    private final MovementRetrieveServicePort movementRetrieve;
    private final PlantOfPositionUpdateApiPort updatePlantService;
    private final PlantOfPositionSearchApiPort plantSearch;
    private final PositionUpdateServicePort positionUpdate;
    private final PositionRetrieveServicePort positionRetrieve;
    private final ExcelPlantReportApiPort excelReport;


    public PlantOfPositionController(PlantOfPositionRetrieveApiPort retrievePlantService,
                                     PlantOfPositionSaveApiPort savePlantService,
                                     MovementRetrieveServicePort movementRetrieve, PlantOfPositionUpdateApiPort updatePlantService,
                                     PlantOfPositionSearchApiPort plantSearch, PositionUpdateServicePort positionUpdate, PositionRetrieveServicePort positionRetrieve, ExcelPlantReportApiPort excelReport) {
        this.retrievePlantService = retrievePlantService;
        this.savePlantService = savePlantService;
        this.movementRetrieve = movementRetrieve;
        this.updatePlantService = updatePlantService;
        this.plantSearch = plantSearch;
        this.positionUpdate = positionUpdate;
        this.positionRetrieve = positionRetrieve;
        this.excelReport = excelReport;
    }

    @GetMapping("all")
    @PreAuthorize("hasRole('USER')")
    public List<PlantOfPositionDto> findAll() {
        // logger.info("Planta de Cargo " );
        // retrievePlantService.getAllPlantOfPositions().stream().forEach(p -> {System.out.println(p.getId()); } );

        return retrievePlantService.getAllPlantOfPositions();

    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('USER')")
    public PlantOfPositionResponse findPlantPositionById(@PathVariable Long id) {
        Optional<PlantOfPosition> plantOfPositionFound = retrievePlantService.findById(id);
        if (!plantOfPositionFound.isPresent()) {
            return null;
        }
        return PlantOfPositionRestMapper.INSTANCE.toPlantOfPositionResponse(plantOfPositionFound.get());
    }

    @PostMapping("create")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> save(@RequestBody PlantOfPositionCommand request) {

        return new ResponseEntity<>(savePlantService.savePlantOfPosition(request), HttpStatus.CREATED);

    }

    @GetMapping("search/")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> search(PlantFilter filter) {

        // Specification<PlantOfPositionEntity> specification = PlantSpecifications.createSpecification(filter);
        return new ResponseEntity<>(plantSearch.search(filter), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('USER')")
    @PostMapping(
            value = "/report/excel",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
    )
    public ResponseEntity<byte[]> generateExcelReport(@RequestBody PlantFilter filter) {
        logger.info("Generando reporte Excel con filtro: {}", filter);

        List<PlantProjectionDTO> plants = plantSearch.search(filter);

        if (plants == null || plants.isEmpty()) {
            logger.warn("No se encontraron resultados para el filtro");
            return ResponseEntity.noContent().build();
        }

        try (ByteArrayInputStream in = excelReport.createExcel(plants)) {
            if (in == null) {
                logger.error("Error al generar el archivo Excel");
                return ResponseEntity.internalServerError().build();
            }

            byte[] bytes = in.readAllBytes();

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"plantas.xlsx\"");
            headers.add(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.parseMediaType(
                            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
                    ))
                    .contentLength(bytes.length)
                    .body(bytes);

        } catch (IOException e) {
            logger.error("Error al generar el archivo Excel", e);
            return ResponseEntity.internalServerError().build();
        }
    }
    @PutMapping("update/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody PlantOfPositionRequest request) {

        return new ResponseEntity<>(updatePlantService.updatePlantOfPosition(id, request), HttpStatus.OK);

        /*if(request.getCurrentStatusID()== PlantStatus.FINALIZADO){

            logger.info(request.getCurrentStatusID().toString()  );


            PlantOfPosition plant = retrievePlantService.findById(id).get();

            Movement movement = movementRetrieve.findByPlant(plant);

            if(movement != null){
               Optional<Position> position = positionRetrieve.findById(movement.getPositionId());

                logger.info(position.get().getPositionStatus() + " ");
               if(!position.isEmpty()){
                   Position positionUpdated = position.get();
                   positionUpdated.setPositionStatus(StatusOfPositions.SUPRIMIDO);
                   positionUpdate.updatePosition(position.get().getId(), positionUpdated);
               }
           }
        }
         return null;
*/

         /*PlantOfPositionRestMapper.INSTANCE.toPlantOfPositionResponse(updatePlantService.updatePlantOfPosition(id,
                PlantOfPositionRestMapper.INSTANCE.toPlantOfPosition(request)));*/
    }
}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                