package com.cgr.bbp.application.logs.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cgr.bbp.application.auth.dto.AuthRequestDto;
import com.cgr.bbp.application.logs.dto.LogDto;
import com.cgr.bbp.application.logs.usecase.ILogUseCase;
import com.cgr.bbp.domain.repository.ILogRepository;
import com.cgr.bbp.infrastructure.persistence.entity.LogEntity;
import com.cgr.bbp.infrastructure.utilities.helpers.DtoMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LogService implements ILogUseCase {

    private final ILogRepository adapterLogRepository;

    private final DtoMapper dtoMapper;

    @Override
    public List<LogDto> logFindAll() {
        return this.dtoMapper.convertToListDto(this.adapterLogRepository.logFindAll(), LogDto.class);
    }

    @Override
    public LogEntity createLog(AuthRequestDto userRequest) {
        LogEntity logEntity = new LogEntity(userRequest.getEmail(), new Date(), true, userRequest.getSAMAccountName());
        return this.adapterLogRepository.createLog(logEntity, userRequest.getSAMAccountName());
    }

}
