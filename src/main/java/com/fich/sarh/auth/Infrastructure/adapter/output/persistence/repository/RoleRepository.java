package com.fich.sarh.auth.Infrastructure.adapter.output.persistence.repository;

import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.entities.RoleEntity;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.entities.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Set<RoleEntity> findRoleEntitiesByRoleEnumIn(Set<String> roleNames);

    RoleEntity findByRoleEnum(RoleEnum name);
}
