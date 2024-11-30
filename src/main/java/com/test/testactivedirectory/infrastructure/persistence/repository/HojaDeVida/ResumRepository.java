package com.test.testactivedirectory.infrastructure.persistence.repository.HojaDeVida;



import org.springframework.data.jpa.repository.JpaRepository;

import com.test.testactivedirectory.infrastructure.persistence.entity.resumen.Identity;

public interface ResumRepository extends JpaRepository<Identity,Long>{
    
}
