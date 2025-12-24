package com.fich.sarh.organizationalunit.infrastructure.adapter.input.rest.mapper;

import com.fich.sarh.organizationalunit.domain.model.OrganizationalUnit;
import com.fich.sarh.organizationalunit.infrastructure.adapter.input.rest.model.request.OrganizationalUnitRequest;
import com.fich.sarh.organizationalunit.infrastructure.adapter.input.rest.model.response.OrganizationalUnitResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-24T08:04:41-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.9 (Microsoft)"
)
@Component
public class OrganizationalUnitRestMapperImpl implements OrganizationalUnitRestMapper {

    @Override
    public OrganizationalUnit toOrganizationalUnit(OrganizationalUnitRequest request) {
        if ( request == null ) {
            return null;
        }

        OrganizationalUnit.OrganizationalUnitBuilder organizationalUnit = OrganizationalUnit.builder();

        organizationalUnit.nameUnit( request.getNameUnit() );
        organizationalUnit.director( request.getDirector() );
        organizationalUnit.viceDirector( request.getViceDirector() );

        return organizationalUnit.build();
    }

    @Override
    public OrganizationalUnitResponse toOrganizationalUnitResponse(OrganizationalUnit organizational) {
        if ( organizational == null ) {
            return null;
        }

        OrganizationalUnitResponse.OrganizationalUnitResponseBuilder organizationalUnitResponse = OrganizationalUnitResponse.builder();

        organizationalUnitResponse.id( organizational.getId() );
        organizationalUnitResponse.nameUnit( organizational.getNameUnit() );
        organizationalUnitResponse.director( organizational.getDirector() );
        organizationalUnitResponse.viceDirector( organizational.getViceDirector() );

        return organizationalUnitResponse.build();
    }

    @Override
    public List<OrganizationalUnitResponse> toOrganizationalUnitResponseList(List<OrganizationalUnit> organizationalUnitList) {
        if ( organizationalUnitList == null ) {
            return null;
        }

        List<OrganizationalUnitResponse> list = new ArrayList<OrganizationalUnitResponse>( organizationalUnitList.size() );
        for ( OrganizationalUnit organizationalUnit : organizationalUnitList ) {
            list.add( toOrganizationalUnitResponse( organizationalUnit ) );
        }

        return list;
    }
}
