package com.cgr.bbp.infrastructure.persistence.repository.entityCgr;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cgr.bbp.infrastructure.persistence.entity.entityCgr.EntityCgr;

public interface IEntityCgrRepository extends JpaRepository<EntityCgr, Integer>{
    
 }
