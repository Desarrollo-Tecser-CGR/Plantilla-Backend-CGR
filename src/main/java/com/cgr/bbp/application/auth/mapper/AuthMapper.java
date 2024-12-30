package com.cgr.bbp.application.auth.mapper;

import java.util.function.Function;

import com.cgr.bbp.application.auth.dto.AuthRequestDto;
import com.cgr.bbp.application.auth.dto.AuthResponseDto;
import com.cgr.bbp.domain.models.UserModel;

public enum AuthMapper implements Function<UserModel, AuthRequestDto> {
    INSTANCE;

    @Override
    public AuthRequestDto apply(UserModel t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'apply'");
    }

    public AuthResponseDto toAuthResponDto(AuthRequestDto userRequestDto) {
        return mapToAuthResponseDto(userRequestDto.getSAMAccountName());
    }

    public AuthResponseDto toAuthResponDto(UserModel userEntity) {
        return mapToAuthResponseDto(userEntity.getSAMAccountName());
    }

    // Método privado común
    private AuthResponseDto mapToAuthResponseDto(String sAMAccountName) {
        return AuthResponseDto.builder()
                .sAMAccountName(sAMAccountName)
                .isEnable(true)
                .build();
    }

}
