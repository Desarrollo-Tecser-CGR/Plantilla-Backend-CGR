package com.cgr.bbp.infrastructure.persistence.repository.Traceability;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cgr.bbp.infrastructure.persistence.entity.Traceability.TraceabilityEntity;

public interface ITraceabilityRepository extends JpaRepository<TraceabilityEntity, Integer>{

    

} 
