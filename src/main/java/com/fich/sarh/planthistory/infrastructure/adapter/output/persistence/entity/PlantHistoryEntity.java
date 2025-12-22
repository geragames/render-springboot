package com.fich.sarh.planthistory.infrastructure.adapter.output.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fich.sarh.common.PlantStatus;
import com.fich.sarh.planthistory.infrastructure.adapter.output.persistence.repository.PlantHistoryListener;
import com.fich.sarh.plantofpositions.infrastructure.adapter.output.persistence.entity.PlantOfPositionEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
//@EntityListeners(PlantHistoryListener.class)
@Table(name = "Historia_planta")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PlantHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    //@Column(name = "historia_previa")
   // Long historyPrev;

    //@Column(name = "historia_actual", unique = true)
    //Long historyCurrent;

    @ManyToOne //(targetEntity = PlantOfPositionEntity.class)
    @JoinColumn(name = "planta_cargo_id")
    PlantOfPositionEntity   plantOfPosition;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "estado_planta")
    PlantStatus plantStatus;

    @Column(name = "fecha_desde")
    LocalDate dateFrom;

    @Column(name = "fecha_hasta")
    LocalDate dateTo;
}
