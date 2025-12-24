package com.fich.sarh.agent.infrastructure.adapter.output.persistence.mapper;

import com.fich.sarh.agent.domain.model.Agent;
import com.fich.sarh.agent.infrastructure.adapter.output.persistence.entity.AgentEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-24T16:36:47-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.9 (Microsoft)"
)
@Component
public class AgentMapperImpl implements AgentMapper {

    @Override
    public Agent toDto(AgentEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Agent.AgentBuilder agent = Agent.builder();

        agent.documenttype( entity.getDocumenttype() );
        agent.id( entity.getId() );
        agent.firstname( entity.getFirstname() );
        agent.lastname( entity.getLastname() );
        agent.document( entity.getDocument() );
        agent.birthdate( entity.getBirthdate() );
        agent.leavingdate( entity.getLeavingdate() );
        agent.deceased( entity.isDeceased() );
        agent.file( entity.getFile() );
        agent.address( entity.getAddress() );
        agent.phone( entity.getPhone() );
        agent.email( entity.getEmail() );

        return agent.build();
    }

    @Override
    public AgentEntity toEntity(Agent agent) {
        if ( agent == null ) {
            return null;
        }

        AgentEntity.AgentEntityBuilder agentEntity = AgentEntity.builder();

        agentEntity.documenttype( agent.getDocumenttype() );
        agentEntity.id( agent.getId() );
        agentEntity.firstname( agent.getFirstname() );
        agentEntity.lastname( agent.getLastname() );
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

    @Override
    public List<Agent> toAgenList(List<AgentEntity> AgentList) {
        if ( AgentList == null ) {
            return null;
        }

        List<Agent> list = new ArrayList<Agent>( AgentList.size() );
        for ( AgentEntity agentEntity : AgentList ) {
            list.add( toDto( agentEntity ) );
        }

        return list;
    }
}
