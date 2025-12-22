package com.fich.sarh.auth.Infrastructure.adapter.input.rest.model.request;

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
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleRequest {

    Long id;
    @Enumerated(EnumType.STRING)
    RoleEnum roleEnum;
}
