package com.fich.sarh.organizationalsubunit.infrastructure.adapter.output.persistence.entity;

import com.fich.sarh.organizationalunit.infrastructure.adapter.output.persistence.entity.OrganizationalUnitEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "SUBUNIDADES_ORGANIZATIVAS")
@ToString
public class OrganizationalSubUnitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "nombre_subunidad")
    String nameSubUnit;

    @Column(name = "codigo_guarani")
    String guaraniCode;

    @ManyToOne
    @JoinColumn(name = "unidad_organizativa_id")
    OrganizationalUnitEntity organizationalUnit;

}
