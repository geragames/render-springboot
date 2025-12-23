package com.fich.sarh.organizationalunit.infrastructure.adapter.output.persistence.mapper;

import com.fich.sarh.agent.infrastructure.adapter.output.persistence.mapper.AgentMapper;
import com.fich.sarh.organizationalunit.domain.model.OrganizationalUnit;
import com.fich.sarh.organizationalunit.infrastructure.adapter.output.persistence.entity.OrganizationalUnitEntity;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-23T18:53:31-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.9 (Microsoft)"
)
@Component
public class OrganizationalUnitMapperImpl implements OrganizationalUnitMapper {

    @Autowired
    private AgentMapper agentMapper;

    @Override
    public OrganizationalUnit toDto(OrganizationalUnitEntity entity) {
        if ( entity == null ) {
            return null;
        }

        OrganizationalUnit.OrganizationalUnitBuilder organizationalUnit = OrganizationalUnit.builder();

        organizationalUnit.id( entity.getId() );
        organizationalUnit.nameUnit( entity.getNameUnit() );
        organizationalUnit.director( agentMapper.toDto( entity.getDirector() ) );
        organizationalUnit.viceDirector( agentMapper.toDto( entity.getViceDirector() ) );

        return organizationalUnit.build();
    }

    @Override
    public OrganizationalUnitEntity toEntity(OrganizationalUnit dto) {
        if ( dto == null ) {
            return null;
        }

        OrganizationalUnitEntity.OrganizationalUnitEntityBuilder organizationalUnitEntity = OrganizationalUnitEntity.builder();

        organizationalUnitEntity.id( dto.getId() );
        organizationalUnitEntity.nameUnit( dto.getNameUnit() );
        organizationalUnitEntity.director( agentMapper.toEntity( dto.getDirector() ) );
        organizationalUnitEntity.viceDirector( agentMapper.toEntity( dto.getViceDirector() ) );

        return organizationalUnitEntity.build();
    }
}
