package com.fich.sarh.plantofpositions.infrastructure.adapter.input.rest.mapper;

import com.fich.sarh.plantofpositions.domain.model.PlantOfPosition;
import com.fich.sarh.plantofpositions.infrastructure.adapter.input.rest.model.request.PlantOfPositionRequest;
import com.fich.sarh.plantofpositions.infrastructure.adapter.input.rest.model.response.PlantOfPositionResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-23T12:47:14-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.9 (Microsoft)"
)
@Component
public class PlantOfPositionRestMapperImpl implements PlantOfPositionRestMapper {

    @Override
    public PlantOfPositionResponse toPlantOfPositionResponse(PlantOfPosition plantposition) {
        if ( plantposition == null ) {
            return null;
        }

        PlantOfPositionResponse.PlantOfPositionResponseBuilder plantOfPositionResponse = PlantOfPositionResponse.builder();

        plantOfPositionResponse.id( plantposition.getId() );
        plantOfPositionResponse.position( plantposition.getPosition() );
        plantOfPositionResponse.agent( plantposition.getAgent() );
        plantOfPositionResponse.organizationalSubUnit( plantposition.getOrganizationalSubUnit() );
        plantOfPositionResponse.characterplantID( plantposition.getCharacterplantID() );
        plantOfPositionResponse.currentStatusID( plantposition.getCurrentStatusID() );

        return plantOfPositionResponse.build();
    }

    @Override
    public PlantOfPosition toPlantOfPosition(PlantOfPositionRequest request) {
        if ( request == null ) {
            return null;
        }

        PlantOfPosition.PlantOfPositionBuilder plantOfPosition = PlantOfPosition.builder();

        plantOfPosition.position( request.getPosition() );
        plantOfPosition.agent( request.getAgent() );
        plantOfPosition.characterplantID( request.getCharacterplantID() );
        plantOfPosition.currentStatusID( request.getCurrentStatusID() );

        return plantOfPosition.build();
    }

    @Override
    public List<PlantOfPositionResponse> toPlantOfPositionResponseList(List<PlantOfPosition> plantList) {
        if ( plantList == null ) {
            return null;
        }

        List<PlantOfPositionResponse> list = new ArrayList<PlantOfPositionResponse>( plantList.size() );
        for ( PlantOfPosition plantOfPosition : plantList ) {
            list.add( toPlantOfPositionResponse( plantOfPosition ) );
        }

        return list;
    }
}
