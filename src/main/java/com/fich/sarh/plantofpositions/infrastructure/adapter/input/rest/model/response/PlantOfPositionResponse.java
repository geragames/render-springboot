package com.fich.sarh.plantofpositions.infrastructure.adapter.input.rest.model.response;

import com.fich.sarh.agent.domain.model.Agent;
import com.fich.sarh.common.CharacterPlant;
import com.fich.sarh.common.PlantStatus;
import com.fich.sarh.organizationalsubunit.domain.model.OrganizationalSubUnit;
import com.fich.sarh.point.domain.model.Point;
import com.fich.sarh.position.domain.model.Position;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlantOfPositionResponse {

    Long id;

    Position position;

    Agent agent;

    OrganizationalSubUnit organizationalSubUnit;

    CharacterPlant characterplantID;

    PlantStatus currentStatusID;
}
