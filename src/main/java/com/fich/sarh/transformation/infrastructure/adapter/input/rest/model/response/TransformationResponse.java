package com.fich.sarh.transformation.infrastructure.adapter.input.rest.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransformationResponse {

    Long id;

    LocalDate date;

    String resolutionNumber;

    String reason;
}
