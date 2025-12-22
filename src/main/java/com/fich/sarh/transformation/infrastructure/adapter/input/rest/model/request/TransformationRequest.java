package com.fich.sarh.transformation.infrastructure.adapter.input.rest.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransformationRequest {

    LocalDate date;

    String resolutionNumber;

    String reason;

}
