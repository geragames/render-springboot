package com.fich.sarh.planthistory.infrastructure.adapter.input.rest.model.response;

import com.fich.sarh.common.PlantStatus;
import com.fich.sarh.plantofpositions.domain.model.PlantOfPosition;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlantHistoryResponse {

    Long id;

    PlantOfPosition plantOfPosition;

    PlantStatus plantStatus;

    LocalDate dateFrom;

    LocalDate dateTo;
}
