package com.fich.sarh.planthistory.domain.model;

import com.fich.sarh.common.PlantStatus;
import com.fich.sarh.plantofpositions.domain.model.PlantOfPosition;
import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class PlantHistory {

    Long id;

    //Long historyPrev;

    //Long historyCurrent;

    PlantOfPosition plantOfPosition;

    PlantStatus plantStatus;

    LocalDate dateFrom;

    LocalDate dateTo;
}
