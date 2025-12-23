package com.fich.sarh.transformation.infrastructure.adapter.output.persistence.mapper;

import com.fich.sarh.transformation.domain.model.Transformation;
import com.fich.sarh.transformation.infrastructure.adapter.output.persistence.entity.TransformationEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-23T12:47:13-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.9 (Microsoft)"
)
@Component
public class TransformationMapperImpl implements TransformationMapper {

    @Override
    public Transformation toDto(TransformationEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Transformation.TransformationBuilder transformation = Transformation.builder();

        transformation.id( entity.getId() );
        transformation.date( entity.getDate() );
        transformation.resolutionNumber( entity.getResolutionNumber() );
        transformation.reason( entity.getReason() );

        return transformation.build();
    }

    @Override
    public TransformationEntity toEntity(Transformation dto) {
        if ( dto == null ) {
            return null;
        }

        TransformationEntity.TransformationEntityBuilder transformationEntity = TransformationEntity.builder();

        transformationEntity.id( dto.getId() );
        transformationEntity.date( dto.getDate() );
        transformationEntity.resolutionNumber( dto.getResolutionNumber() );
        transformationEntity.reason( dto.getReason() );

        return transformationEntity.build();
    }
}
