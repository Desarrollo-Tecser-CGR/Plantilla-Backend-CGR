package com.test.testactivedirectory.application.resume;

import org.springframework.stereotype.Service;

import com.test.testactivedirectory.infrastructure.persistence.entity.resumen.Identity;
import com.test.testactivedirectory.infrastructure.persistence.repository.HojaDeVida.ResumRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ResumeService {
    private final ResumRepository resumRepository;

    public Identity registrarHojaDeVida(Identity hojadevida) {

        Identity hojadevidaguardada = resumRepository.save(hojadevida);

        return hojadevidaguardada;

    }

    public Identity buscarHojaDeVida(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarHojaDeVida'");
    }
}
