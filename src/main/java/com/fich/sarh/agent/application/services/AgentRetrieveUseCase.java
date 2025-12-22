package com.fich.sarh.agent.application.services;



import com.fich.sarh.agent.application.ports.entrypoint.api.AgentRetrieveServicePort;
import com.fich.sarh.agent.application.ports.persistence.AgentRetrievePort;
import com.fich.sarh.agent.domain.model.Agent;
import com.fich.sarh.common.UseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.util.List;
import java.util.Optional;

@UseCase
public class AgentRetrieveUseCase implements AgentRetrieveServicePort {

    private final AgentRetrievePort agentRetrievePort;

    public AgentRetrieveUseCase(AgentRetrievePort agentRetrievePort) {
        this.agentRetrievePort = agentRetrievePort;
    }

    @Override
    public Page<Agent> getAllAgent(int page, int size) {

        return agentRetrievePort.findAll(page, size);
    }

    @Override
    public Optional<Agent> findById(Long id) {

        return agentRetrievePort.findById(id);
    }

    @Override
    public Agent fetchByDocument(String document)
    {
        return agentRetrievePort.findByDocument(document);
    }

    @Override
    public List<Agent> fetchByLastname(String lastname) {

        return agentRetrievePort.findByLastname(lastname);
    }

    @Override
    public boolean existByDocumentAgent(String document) {

        return agentRetrievePort.existsByDocumentAgent(document);
    }
}
