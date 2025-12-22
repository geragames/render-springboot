package com.fich.sarh.plantofpositions.infrastructure.adapter.output.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fich.sarh.organizationalsubunit.infrastructure.adapter.output.persistence.entity.OrganizationalSubUnitEntity;
import com.fich.sarh.planthistory.infrastructure.adapter.output.persistence.entity.PlantHistoryEntity;
import com.fich.sarh.agent.infrastructure.adapter.output.persistence.entity.AgentEntity;
import com.fich.sarh.common.CharacterPlant;
import com.fich.sarh.common.PlantStatus;
import com.fich.sarh.movement.infrastructure.adapter.output.persistence.entity.MovementEntity;
import com.fich.sarh.position.infrastructure.adapter.output.persistence.entity.PositionEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
@Table(name = "planta_cargos")
public class PlantOfPositionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cargo_id", nullable = false)
    @JsonIgnore
    PositionEntity position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agente_id")
    @JsonIgnore
    AgentEntity agent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subunidad_organizativa_id")
    @JsonIgnore
    OrganizationalSubUnitEntity organizationalSubUnit;

    @Enumerated(EnumType.ORDINAL)
    @Column(name ="caracter")
    CharacterPlant characterplantID;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "estado_vigente_id")
    PlantStatus currentStatusID;

    @OneToMany(mappedBy = "plant", cascade = CascadeType.ALL)
    @JsonIgnore
    List<MovementEntity> movements = new ArrayList<>();

    @OneToMany(mappedBy = "plantOfPosition",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<PlantHistoryEntity> plantHistoryEntities;

}
