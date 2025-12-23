package com.fich.sarh.agent.infrastructure.adapter.output.persistence.mapper;

import com.fich.sarh.agent.domain.model.Agent;
import com.fich.sarh.agent.infrastructure.adapter.input.rest.model.request.AgentRequest;
import com.fich.sarh.agent.infrastructure.adapter.input.rest.model.response.AgentResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-23T12:47:14-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.9 (Microsoft)"
)
@Component
public class AgentRestMapperImpl implements AgentRestMapper {

    @Override
    public Agent AgentRequestToAgent(AgentRequest request) {
        if ( request == null ) {
            return null;
        }

        Agent.AgentBuilder agent = Agent.builder();

        agent.documenttype( request.getDocumenttype() );
        agent.id( request.getId() );
        agent.firstname( request.getFirstname() );
        agent.lastname( request.getLastname() );
        agent.document( request.getDocument() );
        agent.birthdate( request.getBirthdate() );
        agent.leavingdate( request.getLeavingdate() );
        agent.deceased( request.isDeceased() );
        agent.file( request.getFile() );
        agent.address( request.getAddress() );
        agent.phone( request.getPhone() );
        agent.email( request.getEmail() );

        return agent.build();
    }

    @Override
    public AgentResponse AgentToAgentResponse(Agent agent) {
        if ( agent == null ) {
            return null;
        }

        AgentResponse.AgentResponseBuilder agentResponse = AgentResponse.builder();

        agentResponse.documenttype( agent.getDocumenttype() );
        agentResponse.id( agent.getId() );
        agentResponse.firstname( agent.getFirstname() );
        agentResponse.lastname( agent.getLastname() );
        agentResponse.document( agent.getDocument() );
        agentResponse.birthdate( agent.getBirthdate() );
        agentResponse.leavingdate( agent.getLeavingdate() );
        agentResponse.deceased( agent.isDeceased() );
        agentResponse.file( agent.getFile() );
        agentResponse.email( agent.getEmail() );
        agentResponse.phone( agent.getPhone() );
        agentResponse.address( agent.getAddress() );

        return agentResponse.build();
    }

    @Override
    public List<AgentResponse> AgentListToAgentResponseList(List<Agent> AgentList) {
        if ( AgentList == null ) {
            return null;
        }

        List<AgentResponse> list = new ArrayList<AgentResponse>( AgentList.size() );
        for ( Agent agent : AgentList ) {
            list.add( AgentToAgentResponse( agent ) );
        }

        return list;
    }
}
