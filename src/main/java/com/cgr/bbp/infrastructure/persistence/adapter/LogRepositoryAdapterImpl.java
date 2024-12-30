package com.cgr.bbp.infrastructure.persistence.adapter;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cgr.bbp.domain.repository.ILogRepository;
import com.cgr.bbp.infrastructure.exception.customException.ResourceNotFoundException;
import com.cgr.bbp.infrastructure.persistence.entity.LogEntity;
import com.cgr.bbp.infrastructure.persistence.entity.UserEntity;
import com.cgr.bbp.infrastructure.persistence.repository.logs.ILogsRepositoryJpa;
import com.cgr.bbp.infrastructure.persistence.repository.user.IUserRepositoryJpa;

@Component
public class LogRepositoryAdapterImpl implements ILogRepository {

    private final ILogsRepositoryJpa logRepositoryJpa;

    private final IUserRepositoryJpa userRepositoryJpa;

    public LogRepositoryAdapterImpl(ILogsRepositoryJpa logRepositoryJpa, IUserRepositoryJpa userRepositoryJpa) {
        this.logRepositoryJpa = logRepositoryJpa;
        this.userRepositoryJpa = userRepositoryJpa;
    }

    @Transactional(readOnly = true)
    @Override
    public List<LogEntity> logFindAll() {
        return this.logRepositoryJpa.findAll();
    }

    @Transactional
    @Override
    public LogEntity createLog(LogEntity logEntity, String userName) {
        Optional<UserEntity> userEntityOptional = this.userRepositoryJpa.findBySAMAccountName(userName);
        if (userEntityOptional.isPresent()) {
            logEntity.setUser(userEntityOptional.get());
            LogEntity log = this.logRepositoryJpa.save(logEntity);
            return log;
        } else {
            throw new ResourceNotFoundException("el usuario con nombre=" + userName + " no existe");
        }
    }

}
