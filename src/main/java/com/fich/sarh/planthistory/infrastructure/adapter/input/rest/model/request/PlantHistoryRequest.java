package com.fich.sarh.planthistory.infrastructure.adapter.input.rest.model.request;

import com.fich.sarh.common.PlantStatus;
import com.fich.sarh.plantofpositions.domain.model.PlantOfPosition;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlantHistoryRequest {

    Long id;

    PlantOfPosition plantOfPosition;

    PlantStatus plantStatus;

    LocalDate dateFrom;

    LocalDate dateTo;
}
