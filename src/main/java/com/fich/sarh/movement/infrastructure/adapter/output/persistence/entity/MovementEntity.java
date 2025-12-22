package com.fich.sarh.movement.infrastructure.adapter.output.persistence.entity;

import com.fich.sarh.plantofpositions.infrastructure.adapter.output.persistence.entity.PlantOfPositionEntity;
import com.fich.sarh.position.infrastructure.adapter.output.persistence.entity.PositionEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;



@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@Entity
@Table(name = "movimiento")
@Getter
@Setter
public class MovementEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

   @ManyToOne
    @JoinColumn(name="planta_id")
    PlantOfPositionEntity plant;

    @ManyToOne
    @JoinColumn(name = "cargo_id")
    PositionEntity position;

    @Column(name = "fecha_movimiento")
    LocalDate movementDate;

    @Column(name = "motivo_movimiento")
    String reasonForMovement;
}
