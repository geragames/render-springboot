package com.fich.sarh.plantofpositions.application.ports.persistence;

import com.fich.sarh.plantofpositions.domain.model.PlantOfPosition;
import com.fich.sarh.plantofpositions.domain.model.PlantProjectionDTO;
import jakarta.servlet.http.HttpServletResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public interface ExcelPlantsReportSpiPort {

    ByteArrayInputStream createExcel(List<PlantProjectionDTO> plants);
}
