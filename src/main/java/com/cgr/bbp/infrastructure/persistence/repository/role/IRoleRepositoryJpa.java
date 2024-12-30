package com.cgr.bbp.infrastructure.persistence.repository.role;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cgr.bbp.infrastructure.persistence.entity.RoleEntity;

@Repository
public interface IRoleRepositoryJpa extends JpaRepository<RoleEntity, Long> {

    // Método para encontrar todos los roles cuyo ID esté en una lista de IDs
    List<RoleEntity> findByIdIn(List<Long> ids);

}
