package com.fich.sarh.plantofpositions.domain.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter @Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class PlantFilter {

    String subject;
    String department;
    String teacher;
    String namePosition;

}
