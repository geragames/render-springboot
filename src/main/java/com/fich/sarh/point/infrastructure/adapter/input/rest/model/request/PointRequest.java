package com.fich.sarh.point.infrastructure.adapter.input.rest.model.request;

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
public class PointRequest {

    Long positionCode;

    String namePosition;

    String dedication;

    Long amountPoint;

    Date date;
}
