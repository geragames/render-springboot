package com.fich.sarh.agent.infrastructure.adapter.output.persistence.adapter;

import com.fich.sarh.agent.application.ports.persistence.AgentLoadPort;
import com.fich.sarh.agent.application.ports.persistence.AgentRetrievePort;
import com.fich.sarh.agent.application.ports.persistence.AgentSavePort;
import com.fich.sarh.agent.domain.model.Agent;
import com.fich.sarh.agent.infrastructure.adapter.output.persistence.entity.AgentEntity;
import com.fich.sarh.agent.infrastructure.adapter.output.persistence.mapper.AgentMapper;
import com.fich.sarh.agent.infrastructure.adapter.output.persistence.repository.AgentRepository;
import com.fich.sarh.common.PersistenceAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@PersistenceAdapter
public class AgentPersistenceAdapter implements AgentLoadPort, AgentSavePort, AgentRetrievePort {

    private final AgentRepository agentRepository;
    private final AgentMapper mapper;

    public AgentPersistenceAdapter(AgentRepository agentRepository, AgentMapper mapper) {
        this.agentRepository = agentRepository;
        this.mapper = mapper;
    }

    //private final AgentMapper agentMapper;

    Logger logger = LoggerFactory.getLogger(AgentPersistenceAdapter.class);

    @Override
    public Optional<Agent> loadAgent(Long id) {

       // Optional<AgentEntity> agentEntity = agentRepository.findById(id);

       // Agent agentDto = agentEntity.isPresent()? agentMapper.toDto(agentEntity.get()):null;

        return agentRepository.findById(id)
                .map( mapper::toDto);
    }

    @Override
    public Agent saveAgent(Agent agent) {

        return AgentMapper.INSTANCE.toDto (agentRepository
                .save(mapper.toEntity(agent)));
        // AgentEntity agentEntity = agentMapper.toEntity(agent);
        // return agentMapper.toDto(agentRepository.save(agentEntity));
    }



    @Override
    public Page<Agent> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());

        return  AgentMapper.INSTANCE.toAgentPage(agentRepository.findAll(pageable));

        //return AgentMapper.INSTANCE.toAgenList(agentRepository.findAll());
    }

    @Override
    public Optional<Agent> findById(Long id) {

        return agentRepository.findById(id)
                .map(mapper::toDto);
    }


    @Override
    public Agent findByDocument(String document) {
        Optional<AgentEntity> agentEntity = agentRepository.findAgentByDocument(document);

        Agent agent = mapper.toDto(agentEntity.get());
        //return (ag                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        ent.isPresent())? agentMapper.toDto(agent.get()): null ;
        return agent;
    }

    @Override
    public List<Agent> findByLastname(String lastname) {
        List<AgentEntity> agentEntityList = agentRepository.findAgentByLastname(lastname);
        List<Agent> agentList = AgentMapper.INSTANCE.toAgenList(agentEntityList);

        return agentList;
    }

    @Override
    public boolean existsByDocumentAgent(String document) {

        return agentRepository.existsByDocument(document);
    }
}
