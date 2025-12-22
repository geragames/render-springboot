package com.fich.sarh.auth.Infrastructure.adapter.input.rest.model.response;

import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.entities.RoleEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleResponse {

    Long id;
    RoleEnum name;
}
