package com.fich.sarh.organizationalsubunit.infrastructure.adapter.input.rest.controller;

import com.fich.sarh.common.WebAdapter;
import com.fich.sarh.organizationalsubunit.domain.model.OrganizationalSubUnit;
import com.fich.sarh.organizationalsubunit.domain.model.OrganizationalSubUnitDTO;
import com.fich.sarh.organizationalsubunit.infrastructure.adapter.input.rest.model.request.OrganizationalSubUnitRequest;
import com.fich.sarh.organizationalsubunit.infrastructure.adapter.input.rest.model.response.OrganizationalSubUnitResponse;
import com.fich.sarh.organizationalsubunit.infrastructure.adapter.input.rest.mapper.OrganizationalSubUnitRestMapper;
import com.fich.sarh.organizationalsubunit.application.ports.entrypoint.api.OrganizationalSubUnitRetrieveApiPort;
import com.fich.sarh.organizationalsubunit.application.ports.entrypoint.api.OrganizationalSubUnitSaveApiPort;
import com.fich.sarh.organizationalsubunit.application.ports.entrypoint.api.OrganizationalSubUnitUpdateApiPort;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@WebAdapter
@RestController
@RequestMapping("/suborganizational")
public class OrganizationalSubUnitController {

    private final OrganizationalSubUnitRetrieveApiPort retrieveService;

    private final OrganizationalSubUnitSaveApiPort saveService;

    private final OrganizationalSubUnitUpdateApiPort updateService;

    private final OrganizationalSubUnitRestMapper restMapper;

    //private final OrganizationalSubUnitMapper suborganizationalMapper;

    Logger logger = LoggerFactory.getLogger(OrganizationalSubUnitController.class);

    public OrganizationalSubUnitController(OrganizationalSubUnitRetrieveApiPort retrieveService, OrganizationalSubUnitSaveApiPort saveService,
                                           OrganizationalSubUnitUpdateApiPort updateService,
                                           OrganizationalSubUnitRestMapper restMapper) {
        this.retrieveService = retrieveService;
        this.saveService = saveService;
        this.updateService = updateService;
        this.restMapper = restMapper;
       // this.suborganizationalMapper = suborganizationalMapper;
    }

@GetMapping("{id}")
@PreAuthorize("hasRole('USER')")
 public OrganizationalSubUnit findOrganizationalSubUnitById(@PathVariable Long id){
        Optional<OrganizationalSubUnit> organizationalSubunit = retrieveService.findById(id);

        if(!organizationalSubunit.isPresent()) return null;

        return organizationalSubunit.get();
 }

    @PreAuthorize("hasAnyRole('USER', 'INVITED')")
    @GetMapping("all")
    public List<OrganizationalSubUnit> findAll(){


        return  retrieveService.getAllOrganizationalSubUnits();
                /*.stream().map(suborganizational ->
                suborganizationalMapper.toOrganizationalSubUnit(suborganizational)
        ).collect(Collectors.toList());*/
    }

    @PostMapping("create")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> save(@RequestBody @Valid OrganizationalSubUnitRequest request){
        return  ResponseEntity.status(HttpStatus.CREATED).body(
                        saveService.saveOrganizationSubUnit(request));
    }

    @PutMapping("update/{id}")
    @PreAuthorize("hasRole('USER')")
    public OrganizationalSubUnitResponse update(@PathVariable Long id, @RequestBody OrganizationalSubUnitRequest request){

        return restMapper.toOrganizationalSubUnit(updateService.updateOrganizationSubUnit(id,request));
    }

    @PreAuthorize("hasAnyRole('USER', 'INVITED')")
    @GetMapping("dto/all")
    public List<OrganizationalSubUnitDTO> findAllDto(){

        return retrieveService.getAllOrganizationalSubUnitDTOs();
    }
}
