package com.fich.sarh.agent.application.ports.entrypoint.api;


import com.fich.sarh.agent.domain.model.Agent;

public interface AgentSaveServicePort {

    Agent saveAgent(Agent command);
}
