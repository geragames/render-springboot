package com.fich.sarh.transformation.domain.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Transformation {

    Long id;

    LocalDate date;

    String resolutionNumber;

    String reason;

    Set<String> attachedDocumentation;

    @Override
    public String toString() {
        return "Transformation{" +
                "id=" + id +
                ", date=" + date +
                ", resolutionNumber='" + resolutionNumber + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
