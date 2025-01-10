package com.cgr.bbp.infrastructure.persistence.repository.file;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cgr.bbp.infrastructure.persistence.entity.entityCgr.EntityCgr;

public interface IFileRepository extends JpaRepository<EntityCgr, Integer> {
    
}
