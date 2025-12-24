package com.fich.sarh.point.infrastructure.adapter.output.persistence.mapper;

import com.fich.sarh.point.domain.model.Point;
import com.fich.sarh.point.infrastructure.adapter.input.rest.model.request.PointRequest;
import com.fich.sarh.point.infrastructure.adapter.input.rest.model.response.PointResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-24T16:36:45-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.9 (Microsoft)"
)
@Component
public class PointRestMapperImpl implements PointRestMapper {

    @Override
    public Point PointRequestToPoint(PointRequest request) {
        if ( request == null ) {
            return null;
        }

        Point.PointBuilder point = Point.builder();

        point.positionCode( request.getPositionCode() );
        point.namePosition( request.getNamePosition() );
        point.dedication( request.getDedication() );
        point.amountPoint( request.getAmountPoint() );
        point.date( request.getDate() );

        return point.build();
    }

    @Override
    public PointResponse PointToPointResponse(Point point) {
        if ( point == null ) {
            return null;
        }

        PointResponse.PointResponseBuilder pointResponse = PointResponse.builder();

        pointResponse.id( point.getId() );
        pointResponse.positionCode( point.getPositionCode() );
        pointResponse.namePosition( point.getNamePosition() );
        pointResponse.dedication( point.getDedication() );
        pointResponse.amountPoint( point.getAmountPoint() );
        pointResponse.date( point.getDate() );

        return pointResponse.build();
    }

    @Override
    public List<PointResponse> toPointResponseList(List<Point> pointList) {
        if ( pointList == null ) {
            return null;
        }

        List<PointResponse> list = new ArrayList<PointResponse>( pointList.size() );
        for ( Point point : pointList ) {
            list.add( PointToPointResponse( point ) );
        }

        return list;
    }
}
