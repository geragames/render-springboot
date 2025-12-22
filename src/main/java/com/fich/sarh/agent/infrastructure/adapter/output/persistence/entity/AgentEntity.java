package com.fich.sarh.agent.infrastructure.adapter.output.persistence.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fich.sarh.common.DocumentType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Agentes")
public class AgentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 64, nullable = false)
    private String firstname;

    @Column(name = "apellido", length = 64, nullable = false)
    private String lastname;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "tipo_documento", nullable = false)
    private DocumentType documenttype;

    @Column(name = "documento", length = 10, nullable = false)
    private String document;

    @Column(name = "fecha_nac")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;

    @Column(name = "fecha_baja")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate leavingdate;

    private boolean deceased;

    private String file;

    private String address;

    private String phone;

    private String email;

}
