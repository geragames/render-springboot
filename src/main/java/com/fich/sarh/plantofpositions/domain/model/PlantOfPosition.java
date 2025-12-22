package com.fich.sarh.plantofpositions.domain.model;

import com.fich.sarh.agent.domain.model.Agent;
import com.fich.sarh.common.CharacterPlant;
import com.fich.sarh.common.PlantStatus;
import com.fich.sarh.organizationalsubunit.domain.model.OrganizationalSubUnit;
import com.fich.sarh.organizationalsubunit.infrastructure.adapter.output.persistence.entity.OrganizationalSubUnitEntity;
import com.fich.sarh.position.domain.model.Position;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class PlantOfPosition {

    Long id;

    Position position;

    Agent agent;

    OrganizationalSubUnit organizationalSubUnit;

    CharacterPlant characterplantID;

    PlantStatus currentStatusID;
}
