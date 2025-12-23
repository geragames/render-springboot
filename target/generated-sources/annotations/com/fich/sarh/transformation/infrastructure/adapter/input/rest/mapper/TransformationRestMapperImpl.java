package com.fich.sarh.transformation.infrastructure.adapter.input.rest.mapper;

import com.fich.sarh.transformation.domain.model.Transformation;
import com.fich.sarh.transformation.infrastructure.adapter.input.rest.model.request.TransformationRequest;
import com.fich.sarh.transformation.infrastructure.adapter.input.rest.model.response.TransformationResponse;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-23T12:47:14-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.9 (Microsoft)"
)
public class TransformationRestMapperImpl implements TransformationRestMapper {

    @Override
    public Transformation toTransformation(TransformationRequest request) {
        if ( request == null ) {
            return null;
        }

        Transformation.TransformationBuilder transformation = Transformation.builder();

        transformation.date( request.getDate() );
        transformation.resolutionNumber( request.getResolutionNumber() );
        transformation.reason( request.getReason() );

        return transformation.build();
    }

    @Override
    public TransformationResponse toTransformationResponse(Transformation transformation) {
        if ( transformation == null ) {
            return null;
        }

        TransformationResponse.TransformationResponseBuilder transformationResponse = TransformationResponse.builder();

        transformationResponse.id( transformation.getId() );
        transformationResponse.date( transformation.getDate() );
        transformationResponse.resolutionNumber( transformation.getResolutionNumber() );
        transformationResponse.reason( transformation.getReason() );

        return transformationResponse.build();
    }
}
