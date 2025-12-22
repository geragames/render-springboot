package com.fich.sarh.positiontype.infrastructure.adapter.input.rest.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PositionTypeRequest {

    String namePosition;
    Double basicSalary;
    int amountOfPointPerPosition;

}
