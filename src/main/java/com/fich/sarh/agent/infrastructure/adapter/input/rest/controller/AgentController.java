package com.fich.sarh.agent.infrastructure.adapter.input.rest.controller;


import com.fich.sarh.agent.application.ports.entrypoint.api.AgentSaveServicePort;
import com.fich.sarh.agent.application.ports.entrypoint.api.AgentUpdateServicePort;
import com.fich.sarh.agent.application.ports.entrypoint.api.AgentRetrieveServicePort;
import com.fich.sarh.agent.application.ports.persistence.AgentSavePort;
import com.fich.sarh.agent.domain.model.Agent;
import com.fich.sarh.agent.infrastructure.adapter.input.rest.model.request.AgentRequest;
import com.fich.sarh.agent.infrastructure.adapter.input.rest.model.response.AgentResponse;
import com.fich.sarh.agent.infrastructure.adapter.output.persistence.mapper.AgentRestMapper;

import com.fich.sarh.common.WebAdapter;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@WebAdapter
@RestController
@RequestMapping("/agent")
public class AgentController {

    private final AgentSaveServicePort agentSavePort;
    private final AgentRetrieveServicePort agentRetrievePort;
    private final AgentUpdateServicePort agentUpdatePort;
    private final AgentRestMapper restMapper;

    Logger logger = LoggerFactory.getLogger(AgentSavePort.class);
    public AgentController(AgentSaveServicePort agentSavePort, AgentRetrieveServicePort agentRetrievePort, AgentUpdateServicePort agentUpdatePort, AgentRestMapper restMapper) {
        this.agentSavePort = agentSavePort;
        this.agentRetrievePort = agentRetrievePort;
        this.agentUpdatePort = agentUpdatePort;

        this.restMapper = restMapper;
    }

  //  @CrossOrigin(origins = "http://localhost:5173")
    @PreAuthorize("hasRole('USER')")
    @GetMapping("all")
    public Page<AgentResponse> findAll(@RequestParam(defaultValue = "0") int page,
          @RequestParam(defaultValue = "5") int size){

        return AgentRestMapper.INSTANCE.toAgentResponsePage(agentRetrievePort.getAllAgent(page, size));

    }

    @GetMapping("document/{document}")
    @PreAuthorize("hasRole('USER')")
    public AgentResponse findAgentByDocument(@PathVariable String document ){
        return  AgentRestMapper.INSTANCE.AgentToAgentResponse(agentRetrievePort.fetchByDocument(document));
    }

    @GetMapping("search/{query}")
    @PreAuthorize("hasRole('USER')")
    public List<AgentResponse> findAgentByLastnameOrFirstname(@PathVariable String query){
        return  AgentRestMapper.INSTANCE.AgentListToAgentResponseList(agentRetrievePort.fetchByLastname(query));
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('USER')")
    public AgentResponse findByIdAgent(@PathVariable Long id) {
        return AgentRestMapper.INSTANCE.AgentToAgentResponse(agentRetrievePort.findById(id).get());
    }



    @PostMapping("create")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> save(@RequestBody @Valid Agent request){
       return ResponseEntity.status(HttpStatus.CREATED)
               .body(
                       agentSavePort.saveAgent(request)
               );

    }

    @PutMapping("update/{id}")
    @PreAuthorize("hasRole('USER')")
    public AgentResponse update(@PathVariable Long id, @RequestBody AgentRequest request){
        logger.info("AGENTE ACTUALIZADO " + request + " ID " + id);
        return restMapper.AgentToAgentResponse(agentUpdatePort.updateAgent(id,
                restMapper.AgentRequestToAgent(request) ));
    }
}
