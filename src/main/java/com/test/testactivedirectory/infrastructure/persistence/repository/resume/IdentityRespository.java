package com.test.testactivedirectory.infrastructure.persistence.repository.resume;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.testactivedirectory.infrastructure.persistence.entity.resumen.Identity;

public interface IdentityRespository extends JpaRepository<Identity,Integer>  {
    
}
