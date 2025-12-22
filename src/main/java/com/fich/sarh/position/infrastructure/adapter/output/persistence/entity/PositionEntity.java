package com.fich.sarh.position.infrastructure.adapter.output.persistence.entity;

import com.fich.sarh.common.StatusOfPositions;
import com.fich.sarh.movement.infrastructure.adapter.output.persistence.entity.MovementEntity;
import com.fich.sarh.organizationalunit.domain.model.OrganizationalUnit;
import com.fich.sarh.organizationalunit.infrastructure.adapter.output.persistence.entity.OrganizationalUnitEntity;
import com.fich.sarh.point.infrastructure.adapter.output.persistence.entity.PointEntity;
import com.fich.sarh.transformation.infrastructure.adapter.output.persistence.entity.TransformationEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@Entity
@Table(name = "cargos")
public class PositionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JoinColumn(name = "punto_id")
    @ManyToOne(fetch = FetchType.LAZY)
    PointEntity pointID;

    @JoinColumn(name = "unidad_organizativa_id")
    @ManyToOne(fetch = FetchType.LAZY)
    OrganizationalUnitEntity organizationalUnitID;

    @Column(name = "estado_cargo")
    @Enumerated(EnumType.ORDINAL)
    StatusOfPositions positionStatus;

    @ManyToOne
    @JoinColumn(name = "cargo_generado", referencedColumnName = "id")
    PositionEntity newPosition;

    @OneToMany(mappedBy = "newPosition", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    List<PositionEntity> originPosition;

    @Column(name = "puntos_disp")
    Long pointsAvailable;

    @JoinColumn(name = "resolucion_creacion_id")
    @ManyToOne(targetEntity = TransformationEntity.class)
    TransformationEntity creationResolutionID;

    @JoinColumn(name = "resolucion_supresion_id")
    @ManyToOne(targetEntity = TransformationEntity.class)
    TransformationEntity resolutionSuppressionID;

    @OneToMany(mappedBy = "position", cascade = CascadeType.ALL)
    List<MovementEntity> movements = new ArrayList<>();
}
