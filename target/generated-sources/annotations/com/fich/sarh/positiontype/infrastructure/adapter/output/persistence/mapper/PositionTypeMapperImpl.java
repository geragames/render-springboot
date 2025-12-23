package com.fich.sarh.positiontype.infrastructure.adapter.output.persistence.mapper;

import com.fich.sarh.positiontype.domain.model.PositionType;
import com.fich.sarh.positiontype.infrastructure.adapter.output.persistence.entity.PositionTypeEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-23T12:47:13-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.9 (Microsoft)"
)
public class PositionTypeMapperImpl implements PositionTypeMapper {

    @Override
    public PositionType toPositionType(PositionTypeEntity entity) {
        if ( entity == null ) {
            return null;
        }

        PositionType.PositionTypeBuilder positionType = PositionType.builder();

        positionType.id( entity.getId() );
        positionType.namePosition( entity.getNamePosition() );
        positionType.basicSalary( entity.getBasicSalary() );
        positionType.amountOfPointPerPosition( entity.getAmountOfPointPerPosition() );

        return positionType.build();
    }

    @Override
    public PositionTypeEntity toPositionTypeEntity(PositionType position) {
        if ( position == null ) {
            return null;
        }

        PositionTypeEntity.PositionTypeEntityBuilder positionTypeEntity = PositionTypeEntity.builder();

        positionTypeEntity.id( position.getId() );
        positionTypeEntity.namePosition( position.getNamePosition() );
        positionTypeEntity.basicSalary( position.getBasicSalary() );
        positionTypeEntity.amountOfPointPerPosition( position.getAmountOfPointPerPosition() );

        return positionTypeEntity.build();
    }
}
