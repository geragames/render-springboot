package com.fich.sarh.point.infrastructure.adapter.output.persistence.mapper;

import com.fich.sarh.point.domain.model.Point;
import com.fich.sarh.point.infrastructure.adapter.output.persistence.entity.PointEntity;
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
public class PointMapperImpl implements PointMapper {

    @Override
    public Point toDto(PointEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Point.PointBuilder point = Point.builder();

        point.id( entity.getId() );
        point.positionCode( entity.getPositionCode() );
        point.namePosition( entity.getNamePosition() );
        point.dedication( entity.getDedication() );
        point.amountPoint( entity.getAmountPoint() );
        point.date( entity.getDate() );

        return point.build();
    }

    @Override
    public PointEntity toEntity(Point dto) {
        if ( dto == null ) {
            return null;
        }

        PointEntity.PointEntityBuilder pointEntity = PointEntity.builder();

        pointEntity.id( dto.getId() );
        pointEntity.positionCode( dto.getPositionCode() );
        pointEntity.namePosition( dto.getNamePosition() );
        pointEntity.dedication( dto.getDedication() );
        pointEntity.amountPoint( dto.getAmountPoint() );
        pointEntity.date( dto.getDate() );

        return pointEntity.build();
    }

    @Override
    public List<Point> toDtoList(List<PointEntity> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<Point> list = new ArrayList<Point>( entityList.size() );
        for ( PointEntity pointEntity : entityList ) {
            list.add( toDto( pointEntity ) );
        }

        return list;
    }
}
