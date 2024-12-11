package com.test.testactivedirectory.application.resume;

import java.beans.Transient;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public List<Identity> getResumAll() {
        return resumRepository.findAll();
    }

    public Identity buscarHojaDeVida(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarHojaDeVida'");
    }
}
