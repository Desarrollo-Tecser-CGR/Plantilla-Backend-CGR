package com.cgr.bbp.application.traceability.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cgr.bbp.application.traceability.usecase.ITraceabilityUseCase;
import com.cgr.bbp.infrastructure.persistence.entity.Traceability.TraceabilityEntity;
import com.cgr.bbp.infrastructure.persistence.repository.Traceability.ITraceabilityRepository;

import lombok.AllArgsConstructor;



@Service
@AllArgsConstructor
public class TraceabilityService implements ITraceabilityUseCase  {

    private ITraceabilityRepository traceabilityRepository;


    @Override
    @Transactional
    public TraceabilityEntity createTraceability(TraceabilityEntity traceabilityEntity) {
     traceabilityEntity.setId(null);   
     return this.traceabilityRepository.save(traceabilityEntity);
    }
    




}
