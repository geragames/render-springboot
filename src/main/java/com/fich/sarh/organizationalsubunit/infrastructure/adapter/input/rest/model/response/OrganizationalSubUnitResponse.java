package com.fich.sarh.organizationalsubunit.infrastructure.adapter.input.rest.model.response;

import com.fich.sarh.organizationalunit.domain.model.OrganizationalUnit;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrganizationalSubUnitResponse {

    String nameSubUnit;

    String guaraniCode;

    OrganizationalUnit organizationalUnit;

}
