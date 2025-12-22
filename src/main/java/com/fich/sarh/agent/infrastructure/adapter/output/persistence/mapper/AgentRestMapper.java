package com.fich.sarh.agent.infrastructure.adapter.output.persistence.mapper;


import com.fich.sarh.agent.domain.model.Agent;
import com.fich.sarh.agent.infrastructure.adapter.input.rest.model.request.AgentRequest;
import com.fich.sarh.agent.infrastructure.adapter.input.rest.model.response.AgentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AgentRestMapper {

    AgentRestMapper INSTANCE = Mappers.getMapper(AgentRestMapper.class);
    @Mapping(target = "documenttype", source = "documenttype")
    Agent AgentRequestToAgent(AgentRequest request);
    @Mapping(target = "documenttype", source = "documenttype")
    AgentResponse AgentToAgentResponse(Agent agent);
    List<AgentResponse> AgentListToAgentResponseList(List<Agent> AgentList);

    default Page<AgentResponse> toAgentResponsePage(Page<Agent> entities){
        return entities.map(this::AgentToAgentResponse);
    }
}
