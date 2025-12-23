package com.fich.sarh.movement.infrastructure.adapter.input.rest.mapper;

import com.fich.sarh.movement.domain.model.Movement;
import com.fich.sarh.movement.infrastructure.adapter.input.rest.model.request.MovementRequest;
import com.fich.sarh.movement.infrastructure.adapter.input.rest.model.response.MovementResponse;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-23T18:53:35-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.9 (Microsoft)"
)
@Component
public class MovementRestMapperImpl implements MovementRestMapper {

    @Override
    public MovementResponse toMovementResponse(Movement movement) {
        if ( movement == null ) {
            return null;
        }

        MovementResponse.MovementResponseBuilder movementResponse = MovementResponse.builder();

        if ( movement.getMovementDate() != null ) {
            movementResponse.movementDate( Date.from( movement.getMovementDate().atStartOfDay( ZoneOffset.UTC ).toInstant() ) );
        }
        movementResponse.reasonForMovement( movement.getReasonForMovement() );

        return movementResponse.build();
    }

    @Override
    public Movement toMovement(MovementRequest request) {
        if ( request == null ) {
            return null;
        }

        Movement.MovementBuilder movement = Movement.builder();

        if ( request.getMovementDate() != null ) {
            movement.movementDate( LocalDateTime.ofInstant( request.getMovementDate().toInstant(), ZoneOffset.UTC ).toLocalDate() );
        }
        movement.reasonForMovement( request.getReasonForMovement() );

        return movement.build();
    }

    @Override
    public List<MovementResponse> toMovementList(List<Movement> movementList) {
        if ( movementList == null ) {
            return null;
        }

        List<MovementResponse> list = new ArrayList<MovementResponse>( movementList.size() );
        for ( Movement movement : movementList ) {
            list.add( toMovementResponse( movement ) );
        }

        return list;
    }
}
