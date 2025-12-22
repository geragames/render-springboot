package com.fich.sarh.agent.domain.model;

import com.fich.sarh.agent.infrastructure.adapter.output.persistence.validation.UniqueDocumentAgent;
import com.fich.sarh.common.DocumentType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
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
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Agent {

    private Long id;

    @NotBlank(message = "El nombre es un dato obligatorio")
    @NotNull(message = "El nombre1 es obligatorio")
    @Size(min = 2, max = 64, message = "El nombre debe tener al menos 2 caracteres")
    private String firstname;

    @NotBlank(message = "El apellido es un dato obligatorio")
    @NotNull(message = "El apellido es obligatorio")
    @Size(min = 2, max = 64, message = "El nombre debe tener al menos 2 caracteres")
    private String lastname;

    @Enumerated(EnumType.ORDINAL)
    private DocumentType documenttype;
    @NotBlank(message = "El documento es obligatorio")
    @UniqueDocumentAgent
    private String document;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    private LocalDate birthdate;

    private LocalDate leavingdate;

    private boolean deceased;

    @NotBlank(message = "El nro de legajo es un dato obligatorio")
    private String file;

    @NotBlank(message = "El domicilio es un dato obligatorio")
    private String address;

    private String phone;
    @Email(message = "El correo electronico es invalido")
    @NotBlank(message = "El correo electronico es obligatorio")
    private String email;
    
}
