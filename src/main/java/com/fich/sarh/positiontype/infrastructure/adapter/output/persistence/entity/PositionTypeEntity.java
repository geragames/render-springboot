package com.fich.sarh.positiontype.infrastructure.adapter.output.persistence.entity;




import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "Tipos-cargos")
public class PositionTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "cargo")
    String namePosition;
    @Column(name = "salario_basico")
    Double basicSalary;
    @Column(name = "cantidad_puntos_por_cargo")
    int amountOfPointPerPosition;

}