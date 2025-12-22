package com.fich.sarh.organizationalunit.infrastructure.adapter.input.rest.model.request;

import com.fich.sarh.agent.domain.model.Agent;
import com.fich.sarh.organizationalsubunit.domain.model.OrganizationalSubUnit;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrganizationalUnitRequest {
    @NotBlank(message = "El nombre del departamento academico es un dato requerido")
    @NotNull(message = "El nombre del departamento academico es un dato requerido")
    @Size(min = 8, max=50, message = "El nombre del departamento academico debe tener al menos 8 caracteres y maximo 50 caracteres")
    String nameUnit;

    @NotNull(message = "El dato del director es un dato obligatorio")
    Agent director;

    Agent viceDirector;

    List<OrganizationalSubUnit> subunitList;
}
