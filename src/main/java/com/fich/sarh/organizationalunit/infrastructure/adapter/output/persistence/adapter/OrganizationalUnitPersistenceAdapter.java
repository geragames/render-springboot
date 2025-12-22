package com.fich.sarh.organizationalunit.infrastructure.adapter.output.persistence.adapter;

import com.fich.sarh.common.PersistenceAdapter;
import com.fich.sarh.organizationalunit.application.ports.persistence.OrganizationalUnitLoadApiPort;
import com.fich.sarh.organizationalunit.application.ports.persistence.OrganizationalUnitRetrieveSpiPort;
import com.fich.sarh.organizationalunit.application.ports.persistence.OrganizationalUnitSaveSpiPort;
import com.fich.sarh.organizationalunit.domain.model.OrganizationalDTO;
import com.fich.sarh.organizationalunit.domain.model.OrganizationalUnit;
import com.fich.sarh.organizationalunit.infrastructure.adapter.output.persistence.mapper.OrganizationalUnitMapper;
import com.fich.sarh.organizationalunit.infrastructure.adapter.output.persistence.repository.OrganizationalUnitRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@PersistenceAdapter
public class OrganizationalUnitPersistenceAdapter implements OrganizationalUnitLoadApiPort,
        OrganizationalUnitRetrieveSpiPort, OrganizationalUnitSaveSpiPort {

    private final OrganizationalUnitRepository organizationalRepository;
    private final OrganizationalUnitMapper mapper;

    public OrganizationalUnitPersistenceAdapter(OrganizationalUnitRepository organizationalRepository, OrganizationalUnitMapper mapper) {
        this.organizationalRepository = organizationalRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<OrganizationalUnit> loadOrganizationalUnit(Long id) {
        return organizationalRepository.findById(id)
                .map(
                        mapper::toDto

                );
    }

    @Override
    public List<OrganizationalUnit> findAllOrganizationalUnit() {
        return organizationalRepository.findAll().stream()
                .map( mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OrganizationalUnit> findById(Long id) {
        return organizationalRepository.findById(id).map(
                mapper::toDto
        );
    }

    @Override
    public List<OrganizationalDTO> findAllOrganizationalDto() {
        return organizationalRepository.findOrganizationalAll();
    }

    @Override
    public OrganizationalUnit findByNameUnit(String unit) {
        return null;
    }


    @Override
    public OrganizationalUnit saveOrganizationalUnit(OrganizationalUnit unit) {
        return
                mapper.toDto(
                        organizationalRepository.save(mapper.toEntity(unit))
                );

    }
}
