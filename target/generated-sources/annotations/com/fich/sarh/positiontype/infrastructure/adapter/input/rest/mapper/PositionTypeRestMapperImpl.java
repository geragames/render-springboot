package com.fich.sarh.positiontype.infrastructure.adapter.input.rest.mapper;

import com.fich.sarh.positiontype.domain.model.PositionType;
import com.fich.sarh.positiontype.infrastructure.adapter.input.rest.model.request.PositionTypeRequest;
import com.fich.sarh.positiontype.infrastructure.adapter.input.rest.model.response.PositionTypeResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-24T16:36:43-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.9 (Microsoft)"
)
@Component
public class PositionTypeRestMapperImpl implements PositionTypeRestMapper {

    @Override
    public PositionType toPositionType(PositionTypeRequest request) {
        if ( request == null ) {
            return null;
        }

        PositionType.PositionTypeBuilder positionType = PositionType.builder();

        positionType.namePosition( request.getNamePosition() );
        positionType.basicSalary( request.getBasicSalary() );
        positionType.amountOfPointPerPosition( request.getAmountOfPointPerPosition() );

        return positionType.build();
    }

    @Override
    public PositionTypeResponse toPositionTypeResponse(PositionType position) {
        if ( position == null ) {
            return null;
        }

        PositionTypeResponse.PositionTypeResponseBuilder positionTypeResponse = PositionTypeResponse.builder();

        positionTypeResponse.namePosition( position.getNamePosition() );
        positionTypeResponse.basicSalary( position.getBasicSalary() );
        positionTypeResponse.amountOfPointPerPosition( position.getAmountOfPointPerPosition() );

        return positionTypeResponse.build();
    }
}
