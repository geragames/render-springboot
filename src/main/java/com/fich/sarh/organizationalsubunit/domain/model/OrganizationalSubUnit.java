package com.fich.sarh.organizationalsubunit.domain.model;

import com.fich.sarh.organizationalunit.domain.model.OrganizationalUnit;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class OrganizationalSubUnit {

    Long id;


    String nameSubUnit;


    String guaraniCode;


    OrganizationalUnit organizationalUnit;


}
