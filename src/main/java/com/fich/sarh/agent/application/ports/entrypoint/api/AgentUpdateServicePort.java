package com.fich.sarh.agent.application.ports.entrypoint.api;


import com.fich.sarh.agent.domain.model.Agent;

public interface AgentUpdateServicePort {
    Agent updateAgent(Long id, Agent command);
}
