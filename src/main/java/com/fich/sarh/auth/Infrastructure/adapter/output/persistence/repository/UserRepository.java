package com.fich.sarh.auth.Infrastructure.adapter.output.persistence.repository;

import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    @Query("SELECT u FROM UserEntity u WHERE u.username = ?1")
    Optional<UserEntity>findUserEntityByUsername(String username);

    @Transactional(readOnly = true)
    @Query("""
            SELECT u FROM UserEntity u WHERE u.username LIKE ?1% OR u.email LIKE ?1%
            """)
    List<UserEntity>findUserByUsernameAndEmail(String query);

    boolean existsByUsername(String username);

    boolean existsByEmailAndIdNot(String email, Long id);

    boolean existsByUsernameIgnoreCaseAndIdNot(String username, Long id);
}
