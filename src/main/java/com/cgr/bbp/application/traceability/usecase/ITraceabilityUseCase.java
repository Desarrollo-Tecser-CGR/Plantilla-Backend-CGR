package com.cgr.bbp.application.traceability.usecase;

import com.cgr.bbp.infrastructure.persistence.entity.Traceability.TraceabilityEntity;

public interface ITraceabilityUseCase {
    
    public abstract TraceabilityEntity createTraceability (TraceabilityEntity traceabilityEntity );


}
