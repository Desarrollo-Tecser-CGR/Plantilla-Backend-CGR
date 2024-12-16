package com.test.testactivedirectory.application.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.testactivedirectory.application.user.dto.UserWithRolesRequestDto;
import com.test.testactivedirectory.application.user.dto.UserWithRolesResponseDto;
import com.test.testactivedirectory.application.user.usecase.UserUseCase;
import com.test.testactivedirectory.domain.repository.IUserRoleRepository;
import com.test.testactivedirectory.infrastructure.persistence.entity.UserEntity;
import com.test.testactivedirectory.infrastructure.utilities.DtoMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserUseCase {

    private final IUserRoleRepository userRoleRepository;

    private final DtoMapper dtoMawpper;

    @Transactional(readOnly = true)
    @Override
    public List<UserWithRolesResponseDto> findAll() {
        List<UserWithRolesResponseDto> users = new ArrayList<>();
        this.userRoleRepository.findAll().forEach(user -> {
            var userResponse = new UserWithRolesResponseDto();
            userResponse.setIdUser(user.getId());
            userResponse.setUserName(user.getSAMAccountName());
            userResponse.setFullName(user.getFullName());
            userResponse.setEmail(user.getEmail());
            userResponse.setPhone(user.getPhone());
            userResponse.setEnabled(user.getEnabled());
            userResponse.setDateModify(user.getDateModify());
            userResponse.setCargo(user.getCargo());

            userResponse.addRole(user.getRoles());

            users.add(userResponse);
        });
        return users;
    }

    @Transactional
    @Override
    public UserWithRolesResponseDto assignRolesToUser(UserWithRolesRequestDto requestDto) {
        UserEntity userEntity = this.userRoleRepository.assignRolesToUser(requestDto);
        var userResponse = new UserWithRolesResponseDto();
        userResponse.setIdUser(userEntity.getId());
        userResponse.setUserName(userEntity.getSAMAccountName());
        userResponse.addRole(userEntity.getRoles());
        return userResponse;
    }

}