package com.fich.sarh.organizationalunit.infrastructure.adapter.input.rest.controller;

import com.fich.sarh.common.WebAdapter;
import com.fich.sarh.organizationalunit.application.ports.entrypoint.api.OrganizationalUnitRetrieveApiPort;
import com.fich.sarh.organizationalunit.application.ports.entrypoint.api.OrganizationalUnitSaveApiPort;
import com.fich.sarh.organizationalunit.application.ports.entrypoint.api.OrganizationalUnitUpdateApiPort;
import com.fich.sarh.organizationalunit.domain.model.OrganizationalDTO;
import com.fich.sarh.organizationalunit.domain.model.OrganizationalUnit;
import com.fich.sarh.organizationalunit.infrastructure.adapter.input.rest.model.request.OrganizationalUnitRequest;
import com.fich.sarh.organizationalunit.infrastructure.adapter.input.rest.model.response.OrganizationalUnitResponse;
import com.fich.sarh.organizationalunit.infrastructure.adapter.input.rest.mapper.OrganizationalUnitRestMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@WebAdapter
@RestController
@RequestMapping("/organizational")
public class OrganizationalUnitController {

    private final OrganizationalUnitRetrieveApiPort retrieveService;

    private final OrganizationalUnitSaveApiPort saveService;

    private final OrganizationalUnitUpdateApiPort updateService;

    private final OrganizationalUnitRestMapper restMapper;
    public OrganizationalUnitController(OrganizationalUnitRetrieveApiPort retrieveService,
                                        OrganizationalUnitSaveApiPort saveService, OrganizationalUnitUpdateApiPort updateService, OrganizationalUnitRestMapper restMapper) {
        this.retrieveService = retrieveService;
        this.saveService = saveService;
        this.updateService = updateService;
        this.restMapper = restMapper;
    }


    @GetMapping("all")
    @PreAuthorize("hasRole('USER')")
    public List<OrganizationalUnitResponse> findAll(){
        return  OrganizationalUnitRestMapper.INSTANCE.toOrganizationalUnitResponseList(retrieveService.getAllOrganizationalUnits());
    }

    @GetMapping("dto/all")
    @PreAuthorize("hasAnyRole('USER', 'INVITED')")
    public List<OrganizationalDTO> findOrganizationalDTO(){
        return retrieveService.findAllOrganizationDto();
    }

    @PostMapping("create")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> save(@Valid @RequestBody  OrganizationalUnitRequest request){
                      OrganizationalUnit organizationalUnit = saveService.saveOrganizationUnit(
                              OrganizationalUnitRestMapper.INSTANCE.toOrganizationalUnit(request));
                      return ResponseEntity.status(HttpStatus.CREATED).body(organizationalUnit);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('USER')")
    public OrganizationalUnit findOrganizationalUnitById(@PathVariable Long id){
        Optional<OrganizationalUnit> organizational = retrieveService.findById(id);
        if(! organizational.isPresent()){
            return null;
        }

        return organizational.get();
    }

    @PutMapping("update/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody OrganizationalUnitRequest request){
        OrganizationalUnit organizationalUnit = updateService.updateOrganizationUnit(id,
                restMapper.toOrganizationalUnit(request));
        return ResponseEntity.status(HttpStatus.OK).body(organizationalUnit) ;
    }
}
