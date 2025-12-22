package com.fich.sarh.plantofpositions.application.ports.entrypoint.api;

import com.fich.sarh.plantofpositions.domain.model.PlantProjectionDTO;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface ExcelPlantReportApiPort {

    ByteArrayInputStream createExcel(List<PlantProjectionDTO> plants);

}
