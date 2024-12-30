package com.cgr.bbp.domain.repository;

import java.util.List;

import com.cgr.bbp.infrastructure.persistence.entity.UserEntity;

public interface IActiveDirectoryUserRepository {
    Boolean checkAccount(String samAccountName, String password);

    List<UserEntity> getAllUsers();
}
