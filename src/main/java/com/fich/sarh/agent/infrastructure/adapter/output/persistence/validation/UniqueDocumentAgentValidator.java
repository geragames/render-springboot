package com.fich.sarh.agent.infrastructure.adapter.output.persistence.validation;

import com.fich.sarh.agent.application.ports.persistence.AgentRetrievePort;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueDocumentAgentValidator implements ConstraintValidator<UniqueDocumentAgent, String> {

    private final AgentRetrievePort agentRetrievePort;

    @Override
    public boolean isValid(String document, ConstraintValidatorContext context) {

        if(document == null || document.isBlank()){
            return true;
        }

        return !agentRetrievePort.existsByDocumentAgent(document);

    }
}
