package com.fich.sarh.organizationalsubunit.infrastructure.adapter.input.rest.model.request;

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
public class OrganizationalSubUnitRequest {

    @NotBlank(message = "El nombre de la materia es un dato requerido")
    @NotNull(message = "El nombre de la materia es un dato requerido")
    @Size(min = 8, max=50, message = "La materia debe tener al menos 8 caracteres y maximo 50 caracteres")
    String nameSubUnit;
    @NotBlank(message = "El codigo Guarani es un dato obligatorio")
    @NotNull(message = "El codigo Guarani es un dato obligatorio")
    @Size(min = 4, max=6, message = "El codigo Guarani tiene que tener un minimo de 4 caracteres y un maximo de 6")
    String guaraniCode;
    @NotNull(message = "El Departamento es un dato obligatorio")
    Long organizationalUnit;
}
