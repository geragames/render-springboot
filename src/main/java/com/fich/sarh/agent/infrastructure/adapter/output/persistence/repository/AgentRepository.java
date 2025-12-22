package com.fich.sarh.agent.infrastructure.adapter.output.persistence.repository;

import com.fich.sarh.agent.infrastructure.adapter.output.persistence.entity.AgentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface AgentRepository extends JpaRepository<AgentEntity, Long> {

    @Transactional(readOnly = true)
    Optional<AgentEntity> findAgentByDocument(String document);

    @Transactional(readOnly = true)
    @Query(value = """
            SELECT ag FROM AgentEntity ag WHERE ag.lastname LIKE ?1% OR ag.document LIKE ?1%
            """)
    List<AgentEntity> findAgentByLastname(String query);

    @Override
    Page<AgentEntity> findAll(Pageable pageable);

    boolean existsByDocument(String document);
}
