package com.cgr.bbp.domain.repository;

import java.util.List;

import com.cgr.bbp.application.user.dto.UserWithRolesRequestDto;
import com.cgr.bbp.infrastructure.persistence.entity.UserEntity;

public interface IUserRoleRepository {

    public abstract List<UserEntity> findAll();

    public abstract UserEntity assignRolesToUser(UserWithRolesRequestDto requestDto);

    public abstract List<UserEntity> findByCargo(String cargo);

    public abstract UserEntity findById(Long id);

}
