package com.fich.sarh.point.infrastructure.adapter.input.rest.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigInteger;
import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PointResponse {

    Long id;

    Long positionCode;

    String namePosition;

    String dedication;

    Long amountPoint;

    Date date;
}
