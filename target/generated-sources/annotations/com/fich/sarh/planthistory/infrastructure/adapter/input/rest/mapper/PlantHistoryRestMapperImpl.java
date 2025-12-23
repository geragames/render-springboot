package com.fich.sarh.planthistory.infrastructure.adapter.input.rest.mapper;

import com.fich.sarh.planthistory.domain.model.PlantHistory;
import com.fich.sarh.planthistory.infrastructure.adapter.input.rest.model.request.PlantHistoryRequest;
import com.fich.sarh.planthistory.infrastructure.adapter.input.rest.model.response.PlantHistoryResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-23T18:53:31-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.9 (Microsoft)"
)
@Component
public class PlantHistoryRestMapperImpl implements PlantHistoryRestMapper {

    @Override
    public PlantHistoryResponse toPlantHistoryResponse(PlantHistory plantHistory) {
        if ( plantHistory == null ) {
            return null;
        }

        PlantHistoryResponse.PlantHistoryResponseBuilder plantHistoryResponse = PlantHistoryResponse.builder();

        plantHistoryResponse.id( plantHistory.getId() );
        plantHistoryResponse.plantOfPosition( plantHistory.getPlantOfPosition() );
        plantHistoryResponse.plantStatus( plantHistory.getPlantStatus() );
        plantHistoryResponse.dateFrom( plantHistory.getDateFrom() );
        plantHistoryResponse.dateTo( plantHistory.getDateTo() );

        return plantHistoryResponse.build();
    }

    @Override
    public PlantHistory toPlantHistory(PlantHistoryRequest plantHistoryRequest) {
        if ( plantHistoryRequest == null ) {
            return null;
        }

        PlantHistory.PlantHistoryBuilder plantHistory = PlantHistory.builder();

        plantHistory.id( plantHistoryRequest.getId() );
        plantHistory.plantOfPosition( plantHistoryRequest.getPlantOfPosition() );
        plantHistory.plantStatus( plantHistoryRequest.getPlantStatus() );
        plantHistory.dateFrom( plantHistoryRequest.getDateFrom() );
        plantHistory.dateTo( plantHistoryRequest.getDateTo() );

        return plantHistory.build();
    }

    @Override
    public List<PlantHistoryResponse> toPlantHistoryResponseList(List<PlantHistory> plantHistories) {
        if ( plantHistories == null ) {
            return null;
        }

        List<PlantHistoryResponse> list = new ArrayList<PlantHistoryResponse>( plantHistories.size() );
        for ( PlantHistory plantHistory : plantHistories ) {
            list.add( toPlantHistoryResponse( plantHistory ) );
        }

        return list;
    }
}
