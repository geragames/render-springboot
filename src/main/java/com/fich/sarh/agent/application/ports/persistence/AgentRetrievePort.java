package com.fich.sarh.agent.application.ports.persistence;



import com.fich.sarh.agent.domain.model.Agent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AgentRetrievePort {

    Page<Agent> findAll(int page, int size);

    Optional<Agent> findById(Long id);
    Agent findByDocument(String document);

    List<Agent> findByLastname(String lastname);

    boolean existsByDocumentAgent(String document);
}
