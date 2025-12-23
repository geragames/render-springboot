package com.fich.sarh.organizationalsubunit.infrastructure.adapter.input.rest.mapper;

import com.fich.sarh.organizationalsubunit.domain.model.OrganizationalSubUnit;
import com.fich.sarh.organizationalsubunit.infrastructure.adapter.input.rest.model.response.OrganizationalSubUnitResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-23T11:03:18-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.9 (Microsoft)"
)
@Component
public class OrganizationalSubUnitRestMapperImpl implements OrganizationalSubUnitRestMapper {

    @Override
    public OrganizationalSubUnitResponse toOrganizationalSubUnit(OrganizationalSubUnit subunit) {
        if ( subunit == null ) {
            return null;
        }

        OrganizationalSubUnitResponse.OrganizationalSubUnitResponseBuilder organizationalSubUnitResponse = OrganizationalSubUnitResponse.builder();

        organizationalSubUnitResponse.nameSubUnit( subunit.getNameSubUnit() );
        organizationalSubUnitResponse.guaraniCode( subunit.getGuaraniCode() );
        organizationalSubUnitResponse.organizationalUnit( subunit.getOrganizationalUnit() );

        return organizationalSubUnitResponse.build();
    }
}
