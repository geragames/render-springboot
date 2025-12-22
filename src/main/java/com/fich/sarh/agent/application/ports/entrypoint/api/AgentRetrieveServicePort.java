package com.fich.sarh.agent.application.ports.entrypoint.api;



import com.fich.sarh.agent.domain.model.Agent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AgentRetrieveServicePort {

    Page<Agent> getAllAgent(int page, int size);

    Optional<Agent> findById(Long id);
    Agent fetchByDocument(String document);

    List<Agent> fetchByLastname(String lastname);

    boolean existByDocumentAgent(String document);
}
