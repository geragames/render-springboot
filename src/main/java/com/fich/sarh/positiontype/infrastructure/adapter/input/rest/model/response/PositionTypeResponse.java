package com.fich.sarh.positiontype.infrastructure.adapter.input.rest.model.response;

import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PositionTypeResponse {

    String namePosition;
    Double basicSalary;
    int amountOfPointPerPosition;
}
