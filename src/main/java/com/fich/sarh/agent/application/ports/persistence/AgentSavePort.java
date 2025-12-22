package com.fich.sarh.agent.application.ports.persistence;


import com.fich.sarh.agent.domain.model.Agent;

public interface AgentSavePort {

    Agent saveAgent(Agent agent);
}
