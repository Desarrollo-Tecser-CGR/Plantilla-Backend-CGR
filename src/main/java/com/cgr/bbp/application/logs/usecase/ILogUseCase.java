package com.cgr.bbp.application.logs.usecase;

import java.util.List;

import com.cgr.bbp.application.auth.dto.AuthRequestDto;
import com.cgr.bbp.application.logs.dto.LogDto;
import com.cgr.bbp.infrastructure.persistence.entity.LogEntity;

public interface ILogUseCase {
    public abstract List<LogDto> logFindAll();

    public abstract LogEntity createLog(AuthRequestDto userRequest);
}
