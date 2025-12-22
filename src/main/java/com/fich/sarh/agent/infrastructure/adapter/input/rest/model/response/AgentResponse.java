package com.fich.sarh.agent.infrastructure.adapter.input.rest.model.response;

import com.fich.sarh.common.DocumentType;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgentResponse {

    private Long id;

    private String firstname;

    private String lastname;

    private DocumentType documenttype;

    private String document;

    private LocalDate birthdate;

    private LocalDate leavingdate;

    private boolean deceased;

    private String file;

    private String email;

    private String phone;

    private String address;

}
