package com.fich.sarh.organizationalsubunit.infrastructure.adapter.input.rest.mapper;

import com.fich.sarh.organizationalsubunit.infrastructure.adapter.input.rest.model.request.OrganizationalSubUnitRequest;
import com.fich.sarh.organizationalsubunit.infrastructure.adapter.input.rest.model.response.OrganizationalSubUnitResponse;
import com.fich.sarh.organizationalsubunit.domain.model.OrganizationalSubUnit;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrganizationalSubUnitRestMapper {

      OrganizationalSubUnitRestMapper INSTANCE = Mappers.getMapper(OrganizationalSubUnitRestMapper.class);

      //OrganizationalSubUnit toOrganizationalSubUnit(OrganizationalSubUnitRequest request);

      OrganizationalSubUnitResponse toOrganizationalSubUnit(OrganizationalSubUnit subunit);

      }
