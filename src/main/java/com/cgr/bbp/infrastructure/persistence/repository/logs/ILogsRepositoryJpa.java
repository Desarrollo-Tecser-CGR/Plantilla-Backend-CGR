package com.cgr.bbp.infrastructure.persistence.repository.logs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cgr.bbp.infrastructure.persistence.entity.LogEntity;

@Repository
public interface ILogsRepositoryJpa extends JpaRepository<LogEntity, Long> {
    
}
