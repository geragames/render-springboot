package com.fich.sarh.organizationalunit.infrastructure.adapter.input.rest.model.response;

import com.fich.sarh.agent.domain.model.Agent;
import com.fich.sarh.organizationalsubunit.domain.model.OrganizationalSubUnit;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrganizationalUnitResponse {

    Long id;

    String nameUnit;

    Agent director;

    Agent viceDirector;

    //List<OrganizationalSubUnit> subunitList;

}
