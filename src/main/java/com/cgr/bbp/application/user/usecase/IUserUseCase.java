package com.cgr.bbp.application.user.usecase;

import java.util.List;

import com.cgr.bbp.application.user.dto.UserWithRolesRequestDto;
import com.cgr.bbp.application.user.dto.UserWithRolesResponseDto;
import com.cgr.bbp.application.user.dto.UserDto;

public interface IUserUseCase {

    public abstract List<UserWithRolesResponseDto> findAll();

    public abstract UserDto create(UserDto requestDto);
    
    public abstract UserWithRolesResponseDto assignRolesToUser(UserWithRolesRequestDto requestDto);

    public abstract List<UserWithRolesResponseDto> findByCargo(String cargo);

    public abstract UserWithRolesResponseDto findById(Long id);

}
