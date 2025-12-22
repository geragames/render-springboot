package com.fich.sarh.organizationalsubunit.infrastructure.adapter.output.persistence.mapper;

import com.fich.sarh.organizationalsubunit.infrastructure.adapter.output.persistence.entity.OrganizationalSubUnitEntity;
import com.fich.sarh.organizationalsubunit.domain.model.OrganizationalSubUnit;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrganizationalSubUnitMapper {

    OrganizationalSubUnitMapper INSTANCE = Mappers.getMapper(OrganizationalSubUnitMapper.class);
    OrganizationalSubUnit toOrganizationalSubUnit(OrganizationalSubUnitEntity entity);
    OrganizationalSubUnitEntity toOrganizationalSubUnitEntity(OrganizationalSubUnit suborganizational);

}
