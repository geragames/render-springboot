package com.fich.sarh.point.domain.model;

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
public class Point {
    Long id;

    Long positionCode;

    String namePosition;

    String dedication;

    Long amountPoint;

    Date date;

    @Override
    public String toString() {
        return "Point{" +
                "id=" + id +
                ", positionCode=" + positionCode +
                ", namePosition='" + namePosition + '\'' +
                ", dedication='" + dedication + '\'' +
                ", amountPoint=" + amountPoint +
                '}';
    }
}
