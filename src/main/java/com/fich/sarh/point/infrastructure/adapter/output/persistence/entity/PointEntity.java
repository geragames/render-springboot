package com.fich.sarh.point.infrastructure.adapter.output.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "Puntos")
public class PointEntity {


  //  private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "codigo_cargo")
    Long positionCode;

    @Column(name = "nombre_cargo")
    String namePosition;

    @Column(name = "dedicacion")
    String dedication;

    @Column(name = "cantidad_puntos")
    Long amountPoint;

    @Column(name = "fecha")
    Date date;
}
