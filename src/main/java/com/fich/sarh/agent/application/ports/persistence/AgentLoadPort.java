package com.fich.sarh.agent.application.ports.persistence;


import com.fich.sarh.agent.domain.model.Agent;

import java.util.Optional;

public interface AgentLoadPort {

    Optional<Agent> loadAgent(Long id);
}
