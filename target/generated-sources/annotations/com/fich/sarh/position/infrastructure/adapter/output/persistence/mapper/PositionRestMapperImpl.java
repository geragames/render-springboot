package com.fich.sarh.position.infrastructure.adapter.output.persistence.mapper;

import com.fich.sarh.position.domain.model.Position;
import com.fich.sarh.position.infrastructure.adapter.input.rest.model.request.PositionRequest;
import com.fich.sarh.position.infrastructure.adapter.input.rest.model.response.PositionResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-24T08:04:41-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.9 (Microsoft)"
)
@Component
public class PositionRestMapperImpl implements PositionRestMapper {

    @Override
    public PositionResponse toPositionResponse(Position position) {
        if ( position == null ) {
            return null;
        }

        PositionResponse.PositionResponseBuilder positionResponse = PositionResponse.builder();

        positionResponse.pointID( position.getPointID() );
        positionResponse.organizationalUnitID( position.getOrganizationalUnitID() );
        positionResponse.positionStatus( position.getPositionStatus() );
        positionResponse.newPosition( position.getNewPosition() );
        positionResponse.pointsAvailable( position.getPointsAvailable() );
        positionResponse.creationResolutionID( position.getCreationResolutionID() );
        positionResponse.resolutionSuppressionID( position.getResolutionSuppressionID() );

        return positionResponse.build();
    }

    @Override
    public Position toPosition(PositionRequest request) {
        if ( request == null ) {
            return null;
        }

        Position.PositionBuilder position = Position.builder();

        position.pointID( request.getPointID() );
        position.organizationalUnitID( request.getOrganizationalUnitID() );
        position.positionStatus( request.getPositionStatus() );
        position.newPosition( request.getNewPosition() );
        position.pointsAvailable( request.getPointsAvailable() );
        position.creationResolutionID( request.getCreationResolutionID() );
        position.resolutionSuppressionID( request.getResolutionSuppressionID() );

        return position.build();
    }

    @Override
    public List<PositionResponse> toPositionResponseList(List<Position> positionList) {
        if ( positionList == null ) {
            return null;
        }

        List<PositionResponse> list = new ArrayList<PositionResponse>( positionList.size() );
        for ( Position position : positionList ) {
            list.add( toPositionResponse( position ) );
        }

        return list;
    }
}
