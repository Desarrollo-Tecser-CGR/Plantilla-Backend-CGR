package com.cgr.bbp.application.user.dto;

import com.cgr.bbp.infrastructure.persistence.entity.UserEntity;
import com.cgr.bbp.infrastructure.utilities.Anotations.ExistsField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    @NotBlank
    @JsonProperty("sAMAccountName")
    @ExistsField(entityClass = UserEntity.class, fieldName = "sAMAccountName" )
    private String sAMAccountName;

    @NotBlank
    private String password;
    
    @NotBlank
    private String fullName;

    @NotBlank
    private String email;

    private String phone;

}
