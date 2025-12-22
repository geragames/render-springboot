package com.fich.sarh.plantofpositions.application.services;

import com.fich.sarh.common.UseCase;
import com.fich.sarh.plantofpositions.application.ports.entrypoint.api.ExcelPlantReportApiPort;
import com.fich.sarh.plantofpositions.application.ports.persistence.ExcelPlantsReportSpiPort;
import com.fich.sarh.plantofpositions.domain.model.PlantProjectionDTO;

import java.io.ByteArrayInputStream;
import java.util.List;

@UseCase
public class ExcelPlantsReportUseCase implements ExcelPlantReportApiPort {

    private final ExcelPlantsReportSpiPort excelReport;

    public ExcelPlantsReportUseCase(ExcelPlantsReportSpiPort excelReport) {
        this.excelReport = excelReport;
    }

    @Override
    public ByteArrayInputStream createExcel(List<PlantProjectionDTO> plants) {

        return  excelReport.createExcel(plants);
    }
}
