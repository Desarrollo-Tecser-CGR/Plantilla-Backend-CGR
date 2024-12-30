package com.cgr.bbp.infrastructure.persistence.repository.resume;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cgr.bbp.infrastructure.persistence.entity.resumen.Identity;

public interface IdentityRespository extends JpaRepository<Identity,Integer>  {
    
}
