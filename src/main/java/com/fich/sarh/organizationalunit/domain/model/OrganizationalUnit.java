package com.fich.sarh.organizationalunit.domain.model;

import com.fich.sarh.agent.domain.model.Agent;
import com.fich.sarh.common.exceptions.BusinessRuleViolationException;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter

@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrganizationalUnit {

    Long id;

    String nameUnit;

    Agent director;

    Agent viceDirector;

    public OrganizationalUnit(Long id, String nameUnit, Agent director, Agent viceDirector) {
        this.id = id;
        this.nameUnit = nameUnit;
        this.director = director;
        this.viceDirector = viceDirector;
        validateDirectors();
    }

    // List<OrganizationalSubUnit> subunitList;
    private boolean isDirectorDifferentFromVice() {
        if (director == null || viceDirector == null) {
            return true;
        }

        if (director.getId() == null || viceDirector.getId() == null) {
            return true;
        }

        return !director.getId().equals(viceDirector.getId());
    }

    public void validateDirectors() {
        if (director == null || viceDirector == null) {
            return; // vice es opcional
        }

        if (director.getId() == null || viceDirector.getId() == null) {
            return; // entidad no persistida / caso transitorio
        }

        if (director.getId().equals(viceDirector.getId())) {
            throw new BusinessRuleViolationException(
                    "El director y el vicedirector no pueden ser la misma persona"
            );
        }
    }

    @Override
    public String toString() {
        return "OrganizationalUnit{" +
                "id=" + id +
                ", nameUnit='" + nameUnit + '\'' +
                ", director=" + director +
                ", viceDirector=" + viceDirector +
                '}';
    }
}
