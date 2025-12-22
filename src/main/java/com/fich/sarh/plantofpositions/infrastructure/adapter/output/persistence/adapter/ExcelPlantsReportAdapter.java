package com.fich.sarh.plantofpositions.infrastructure.adapter.output.persistence.adapter;

import com.fich.sarh.common.WebAdapter;
import com.fich.sarh.plantofpositions.application.ports.persistence.ExcelPlantsReportSpiPort;
import com.fich.sarh.plantofpositions.domain.model.PlantOfPosition;
import com.fich.sarh.plantofpositions.domain.model.PlantProjectionDTO;
import com.fich.sarh.plantofpositions.infrastructure.adapter.output.persistence.repository.PlantOfPositionRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

import java.io.*;
import java.util.Iterator;
import java.util.List;

@WebAdapter
public class ExcelPlantsReportAdapter implements ExcelPlantsReportSpiPort {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public ByteArrayInputStream createExcel(List<PlantProjectionDTO> plants) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (Workbook workbook = new XSSFWorkbook() ) {
            Sheet sheet = workbook.createSheet("Plantas");

            // ✅ Estilo de encabezado
            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);

            // ✅ Encabezados
            Row headerRow = sheet.createRow(0);
            String[] headers = {"ID", "Apellido", "Nombre", "Materia", "Cargo", "Puntos", "Estado Planta"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            // ✅ Cuerpo
            int rowIdx = 1;
            for (PlantProjectionDTO plant : plants) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(plant.getId());
                row.createCell(1).setCellValue(plant.getFirstname());
                row.createCell(2).setCellValue(plant.getLastname());
                row.createCell(3).setCellValue(plant.getNameSubUnit());
                row.createCell(4).setCellValue(plant.getNamePosition());
                row.createCell(5).setCellValue(plant.getCharacterplantID());
                row.createCell(6).setCellValue(plant.getCurrentStatusID());
            }

            // ✅ Autoajustar columnas
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // ✅ Escribir el workbook al stream
            workbook.write(out);


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return new ByteArrayInputStream(out.toByteArray());
    }
}
/* Explicación de puntos clave
        Elemen
/*
    @Override
    public ByteArrayInputStream createExcel(List<PlantProjectionDTO> plants) {



        try (SXSSFWorkbook wb = new SXSSFWorkbook(1); ByteArrayOutputStream out = new ByteArrayOutputStream();){

            SXSSFSheet sheet = wb.createSheet("Planta");

            final var style = wb.createCellStyle();
            final var font = wb.createFont();

            font.setBold(true);
            style.setFont(font);

            Row nRow = null;
            Cell nCell = null;

            // Generar la cabecera
            Object[] columns = {"ID","Cod Cargo", "Nombre", "Apellido", "Departamento", "Materia"};

            nRow = sheet.createRow(0);

            for (int i = 0; i < columns.length; i++) {
                nCell = nRow.createCell(i);
                nCell.setCellValue(columns[i].toString());
            }

            // Generamos el cuerpo del excel
            Iterator<PlantProjectionDTO> it = plants.iterator();
            int pageRowNo = 1;

            while (it.hasNext()) {
                PlantProjectionDTO objExcelPlant = it.next();
                nRow = sheet.createRow(pageRowNo++);
                nRow.createCell(0).setCellValue(objExcelPlant.getId().toString());

                nRow.createCell(1).setCellValue(objExcelPlant.getNamePosition());
                nRow.createCell(2).setCellValue(objExcelPlant.getFirstname());
                nRow.createCell(3).setCellValue(objExcelPlant.getLastname());
                nRow.createCell(4).setCellValue(objExcelPlant.getNameSubUnit());
                nRow.createCell(5).setCellValue(objExcelPlant.getNamePosition());
            }

         //   FileOutputStream fileOutputStream = new FileOutputStream("report-excel.xlsx");
            wb.write(out);
         //   fileOutputStream.flush();
         //   fileOutputStream.close();
            wb.dispose();
            logger.info("LA PLANTA CARGADA " + out.size());
            return  new ByteArrayInputStream(out.toByteArray());

        } catch (Exception e) {

            throw new RuntimeException("Error al generar el Excel", e);
        }


    }*/

