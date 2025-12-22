package com.fich.sarh.auth.Domain.model;

import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.entities.RoleEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleDTO {

    Long id;

    @Enumerated(EnumType.STRING)
    RoleEnum roleEnum;
}
