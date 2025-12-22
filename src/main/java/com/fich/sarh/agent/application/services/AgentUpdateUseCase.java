package com.fich.sarh.agent.application.services;

import com.fich.sarh.agent.application.ports.entrypoint.api.AgentUpdateServicePort;
import com.fich.sarh.agent.application.ports.persistence.AgentRetrievePort;
import com.fich.sarh.agent.application.ports.persistence.AgentSavePort;
import com.fich.sarh.agent.domain.model.Agent;
import com.fich.sarh.common.UseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@UseCase
public class AgentUpdateUseCase implements AgentUpdateServicePort {

    Logger logger = LoggerFactory.getLogger(AgentUpdateUseCase.class);

    private final AgentRetrievePort agentRetrievePort;

    private final AgentSavePort agentSavePort;
    public AgentUpdateUseCase(AgentRetrievePort agentRetrievePort, AgentSavePort agentSavePort) {
        this.agentRetrievePort = agentRetrievePort;
        this.agentSavePort = agentSavePort;
    }

    @Override
    public Agent updateAgent(Long id, Agent command) {

        logger.error("UPPPPDATE AGENT " +  command);
        return Optional.ofNullable(agentRetrievePort.findById(id))
                .map(savedAgent -> savedAgent.get())
                .map(savedAgent -> {
                           savedAgent.setFirstname(command.getFirstname());
                           savedAgent.setLastname(command.getLastname());
                           savedAgent.setDocumenttype(command.getDocumenttype());
                           savedAgent.setDocument(command.getDocument());
                           savedAgent.setBirthdate(command.getBirthdate());
                           savedAgent.setAddress(command.getAddress());
                           savedAgent.setFile(command.getFile());
                           savedAgent.setLeavingdate(command.getLeavingdate());
                           savedAgent.setDeceased(command.isDeceased());
                           savedAgent.setPhone(command.getPhone());
                           savedAgent.setEmail(command.getEmail());
                        return agentSavePort.saveAgent(savedAgent);
                }).get();


    }
}
