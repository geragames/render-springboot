package com.fich.sarh.point.infrastructure.adapter.output.persistence.mapper;

import com.fich.sarh.point.domain.model.Point;
import com.fich.sarh.point.infrastructure.adapter.input.rest.model.request.PointRequest;
import com.fich.sarh.point.infrastructure.adapter.input.rest.model.response.PointResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PointRestMapper {

    PointRestMapper INSTANCE = Mappers.getMapper(PointRestMapper.class);

    Point PointRequestToPoint(PointRequest request);

    PointResponse PointToPointResponse(Point point);

    List<PointResponse> toPointResponseList(List<Point> pointList);

}
