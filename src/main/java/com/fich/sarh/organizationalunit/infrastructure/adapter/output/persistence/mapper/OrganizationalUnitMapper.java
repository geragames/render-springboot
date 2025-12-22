package com.fich.sarh.organizationalunit.infrastructure.adapter.output.persistence.mapper;

import com.fich.sarh.agent.infrastructure.adapter.output.persistence.mapper.AgentMapper;
import com.fich.sarh.organizationalunit.domain.model.OrganizationalUnit;
import com.fich.sarh.organizationalunit.infrastructure.adapter.output.persistence.entity.OrganizationalUnitEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = { AgentMapper.class },
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL, // si el objeto completo es null
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)// si una propiedad es null, setear null)
public interface OrganizationalUnitMapper {

    OrganizationalUnitMapper INSTANCE = Mappers.getMapper(OrganizationalUnitMapper.class);
    OrganizationalUnit toDto(OrganizationalUnitEntity entity);

    OrganizationalUnitEntity toEntity(OrganizationalUnit dto);
}