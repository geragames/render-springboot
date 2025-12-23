package com.fich.sarh.organizationalsubunit.infrastructure.adapter.output.persistence.mapper;

import com.fich.sarh.agent.domain.model.Agent;
import com.fich.sarh.agent.infrastructure.adapter.output.persistence.entity.AgentEntity;
import com.fich.sarh.organizationalsubunit.domain.model.OrganizationalSubUnit;
import com.fich.sarh.organizationalsubunit.infrastructure.adapter.output.persistence.entity.OrganizationalSubUnitEntity;
import com.fich.sarh.organizationalunit.domain.model.OrganizationalUnit;
import com.fich.sarh.organizationalunit.infrastructure.adapter.output.persistence.entity.OrganizationalUnitEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-23T11:03:20-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.9 (Microsoft)"
)
public class OrganizationalSubUnitMapperImpl implements OrganizationalSubUnitMapper {

    @Override
    public OrganizationalSubUnit toOrganizationalSubUnit(OrganizationalSubUnitEntity entity) {
        if ( entity == null ) {
            return null;
        }

        OrganizationalSubUnit.OrganizationalSubUnitBuilder organizationalSubUnit = OrganizationalSubUnit.builder();

        organizationalSubUnit.id( entity.getId() );
        organizationalSubUnit.nameSubUnit( entity.getNameSubUnit() );
        organizationalSubUnit.guaraniCode( entity.getGuaraniCode() );
        organizationalSubUnit.organizationalUnit( organizationalUnitEntityToOrganizationalUnit( entity.getOrganizationalUnit() ) );

        return organizationalSubUnit.build();
    }

    @Override
    public OrganizationalSubUnitEntity toOrganizationalSubUnitEntity(OrganizationalSubUnit suborganizational) {
        if ( suborganizational == null ) {
            return null;
        }

        OrganizationalSubUnitEntity.OrganizationalSubUnitEntityBuilder organizationalSubUnitEntity = OrganizationalSubUnitEntity.builder();

        organizationalSubUnitEntity.id( suborganizational.getId() );
        organizationalSubUnitEntity.nameSubUnit( suborganizational.getNameSubUnit() );
        organizationalSubUnitEntity.guaraniCode( suborganizational.getGuaraniCode() );
        organizationalSubUnitEntity.organizationalUnit( organizationalUnitToOrganizationalUnitEntity( suborganizational.getOrganizationalUnit() ) );

        return organizationalSubUnitEntity.build();
    }

    protected Agent agentEntityToAgent(AgentEntity agentEntity) {
        if ( agentEntity == null ) {
            return null;
        }

        Agent.AgentBuilder agent = Agent.builder();

        agent.id( agentEntity.getId() );
        agent.firstname( agentEntity.getFirstname() );
        agent.lastname( agentEntity.getLastname() );
        agent.documenttype( agentEntity.getDocumenttype() );
        agent.document( agentEntity.getDocument() );
        agent.birthdate( agentEntity.getBirthdate() );
        agent.leavingdate( agentEntity.getLeavingdate() );
        agent.deceased( agentEntity.isDeceased() );
        agent.file( agentEntity.getFile() );
        agent.address( agentEntity.getAddress() );
        agent.phone( agentEntity.getPhone() );
        agent.email( agentEntity.getEmail() );

        return agent.build();
    }

    protected OrganizationalUnit organizationalUnitEntityToOrganizationalUnit(OrganizationalUnitEntity organizationalUnitEntity) {
        if ( organizationalUnitEntity == null ) {
            return null;
        }

        OrganizationalUnit.OrganizationalUnitBuilder organizationalUnit = OrganizationalUnit.builder();

        organizationalUnit.id( organizationalUnitEntity.getId() );
        organizationalUnit.nameUnit( organizationalUnitEntity.getNameUnit() );
        organizationalUnit.director( agentEntityToAgent( organizationalUnitEntity.getDirector() ) );
        organizationalUnit.viceDirector( agentEntityToAgent( organizationalUnitEntity.getViceDirector() ) );

        return organizationalUnit.build();
    }

    protected AgentEntity agentToAgentEntity(Agent agent) {
        if ( agent == null ) {
            return null;
        }

        AgentEntity.AgentEntityBuilder agentEntity = AgentEntity.builder();

        agentEntity.id( agent.getId() );
        agentEntity.firstname( agent.getFirstname() );
        agentEntity.lastname( agent.getLastname() );
        agentEntity.documenttype( agent.getDocumenttype() );
        agentEntity.document( agent.getDocument() );
        agentEntity.birthdate( agent.getBirthdate() );
        agentEntity.leavingdate( agent.getLeavingdate() );
        agentEntity.deceased( agent.isDeceased() );
        agentEntity.file( agent.getFile() );
        agentEntity.address( agent.getAddress() );
        agentEntity.phone( agent.getPhone() );
        agentEntity.email( agent.getEmail() );

        return agentEntity.build();
    }

    protected OrganizationalUnitEntity organizationalUnitToOrganizationalUnitEntity(OrganizationalUnit organizationalUnit) {
        if ( organizationalUnit == null ) {
            return null;
        }

        OrganizationalUnitEntity.OrganizationalUnitEntityBuilder organizationalUnitEntity = OrganizationalUnitEntity.builder();

        organizationalUnitEntity.id( organizationalUnit.getId() );
        organizationalUnitEntity.nameUnit( organizationalUnit.getNameUnit() );
        organizationalUnitEntity.director( agentToAgentEntity( organizationalUnit.getDirector() ) );
        organizationalUnitEntity.viceDirector( agentToAgentEntity( organizationalUnit.getViceDirector() ) );

        return organizationalUnitEntity.build();
    }
}
