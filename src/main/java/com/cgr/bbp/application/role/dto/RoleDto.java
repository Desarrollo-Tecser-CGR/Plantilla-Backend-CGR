package com.cgr.bbp.application.role.dto;

import lombok.Data;

@Data
public class RoleDto {

    private String name;

    public RoleDto(String name) {
        this.name = name;
    }

}
