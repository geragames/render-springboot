package  com.fich.sarh.agent.infrastructure.adapter.output.persistence.mapper;


import com.fich.sarh.agent.domain.model.Agent;
import com.fich.sarh.agent.infrastructure.adapter.output.persistence.entity.AgentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AgentMapper {

  AgentMapper INSTANCE = Mappers.getMapper(AgentMapper.class);

  @Mapping(target = "documenttype", source = "documenttype")
  Agent  toDto(AgentEntity entity);

  @Mapping(target = "documenttype", source = "documenttype")
  AgentEntity toEntity(Agent agent);
  List<Agent> toAgenList(List<AgentEntity> AgentList);

  default Page<Agent> toAgentPage(Page<AgentEntity> entities){
    return entities.map(this::toDto);
  }
}
