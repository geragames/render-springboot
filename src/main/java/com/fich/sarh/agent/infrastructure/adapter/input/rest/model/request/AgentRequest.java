package com.fich.sarh.agent.infrastructure.adapter.input.rest.model.request;


import com.fich.sarh.common.DocumentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AgentRequest {

    Long id;
    
    @NotBlank(message = "Nombre Invalido: Nombre en blanco")
    @NotNull(message = "Nombre Invalido: El nombre es NULL")
    @Size(min = 3, max = 30, message = "Nombre Invalido: Debe tener de 3 a 30 caracteres ")
    private String firstname;

    @NotBlank(message = "Apellido Invalido: Apellido en blanco")
    @NotNull(message = "Apellido Invalido: El Apellido es NULL")
    @Size(min = 3, max = 30, message = "Apellido Invalido: Debe tener de 3 a 30 caracteres ")
    private String lastname;

    @NotNull(message = "Tipo de documento Invalido: El tipo no debe ser NULL")

    private DocumentType documenttype;
    @NotBlank(message = "Documento Invalido: El documento esta en blanco ")
    @NotNull(message = "Documento Invalido: El documento es NULL")
    @Size(min = 6, max = 8, message = "Documento Invalido: Debe tener más de 6 digitos y menos de 8 digito")
    private String document;
    private LocalDate birthdate;
    private LocalDate leavingdate;
    private boolean deceased;
    private String file;
    private String email;
    private String phone;
    private String address;


}
