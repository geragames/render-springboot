package com.fich.sarh.point.infrastructure.adapter.output.persistence.adapter;

import com.fich.sarh.common.PersistenceAdapter;
import com.fich.sarh.point.application.ports.persistence.PointLoadPort;
import com.fich.sarh.point.application.ports.persistence.PointRetrievePort;
import com.fich.sarh.point.application.ports.persistence.PointSavePort;
import com.fich.sarh.point.domain.model.Point;
import com.fich.sarh.point.infrastructure.adapter.output.persistence.entity.PointEntity;
import com.fich.sarh.point.infrastructure.adapter.output.persistence.mapper.PointMapper;
import com.fich.sarh.point.infrastructure.adapter.output.persistence.repository.PointRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@PersistenceAdapter
public class PointPersistenceAdapter implements PointRetrievePort, PointSavePort, PointLoadPort {

    private final PointRepository pointRepository;

    public PointPersistenceAdapter(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    @Override
    public Optional<Point> loadPoint(Long id) {
        return pointRepository.findById(id).map(
                PointMapper.INSTANCE::toDto
        );
    }

    @Override
    public List<Point> findAllPoints() {
        return pointRepository.findAll().stream().map(
                PointMapper.INSTANCE::toDto
        ).collect(Collectors.toList());
    }

    @Override
    public Optional<Point> findById(Long id) {
        return pointRepository.findById(id).map(
                PointMapper.INSTANCE::toDto
        );
    }

    @Override
    public Point savePoint(Point point) {

        return PointMapper.INSTANCE.toDto(
                pointRepository.save(PointMapper.INSTANCE.toEntity(point))
        );

    }
}
