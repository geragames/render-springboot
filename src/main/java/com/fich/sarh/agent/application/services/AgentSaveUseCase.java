package com.fich.sarh.agent.application.services;

import com.fich.sarh.agent.application.ports.entrypoint.api.AgentSaveServicePort;
import com.fich.sarh.agent.application.ports.persistence.AgentSavePort;
import com.fich.sarh.agent.domain.model.Agent;
import com.fich.sarh.common.UseCase;
import org.springframework.transaction.annotation.Transactional;

@UseCase
public class AgentSaveUseCase implements AgentSaveServicePort {

    private final AgentSavePort agentSavePort;

    public AgentSaveUseCase(AgentSavePort agentSavePort) {
        this.agentSavePort = agentSavePort;
    }

    @Override
    @Transactional
    public Agent saveAgent(Agent command) {


        return agentSavePort.saveAgent(command);
    }
}
