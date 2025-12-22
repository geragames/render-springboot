package com.fich.sarh.transformation.infrastructure.adapter.input.rest.mapper;

import com.fich.sarh.transformation.domain.model.Transformation;
import com.fich.sarh.transformation.infrastructure.adapter.input.rest.model.request.TransformationRequest;
import com.fich.sarh.transformation.infrastructure.adapter.input.rest.model.response.TransformationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TransformationRestMapper {

    TransformationRestMapper INSTANCE = Mappers.getMapper(TransformationRestMapper.class);
    Transformation toTransformation(TransformationRequest request);

    TransformationResponse toTransformationResponse(Transformation transformation);


}
