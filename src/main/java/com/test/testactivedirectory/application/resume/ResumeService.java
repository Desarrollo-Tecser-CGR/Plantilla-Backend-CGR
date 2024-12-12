package com.test.testactivedirectory.application.resume;

import java.beans.Transient;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.testactivedirectory.application.resume.dto.IdentityFilterRequest;
import com.test.testactivedirectory.application.resume.dto.IdentityFilterResponsive;
import com.test.testactivedirectory.infrastructure.persistence.entity.resumen.Identity;
import com.test.testactivedirectory.infrastructure.persistence.repository.HojaDeVida.ResumRepository;
import com.test.testactivedirectory.infrastructure.utilities.DtoMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ResumeService {
    private final ResumRepository resumRepository;

    private final DtoMapper mapper;

    public Identity registrarHojaDeVida(Identity hojadevida) {

        Identity hojadevidaguardada = resumRepository.save(hojadevida);

        return hojadevidaguardada;

    }

    @Transactional
    public List<IdentityFilterResponsive> getResumWithFilter(IdentityFilterRequest filter) {
        // Obtener todas las entidades
        List<Identity> identities = resumRepository.findAll();
    
        // Aplicar los filtros proporcionados
        List<Identity> filteredIdentities = identities.stream()
                .filter(identity -> filter.getFechaDiligenciamientoInicio() == null || 
                        (identity.getFechaDiligenciamiento() != null && 
                        !identity.getFechaDiligenciamiento().before(filter.getFechaDiligenciamientoInicio())))
                .filter(identity -> filter.getFechaDiligenciamientoFinal() == null || 
                        (identity.getFechaDiligenciamiento() != null && 
                        !identity.getFechaDiligenciamiento().after(filter.getFechaDiligenciamientoFinal())))
                .filter(identity -> filter.getNombreEntidad() == null || 
                        (identity.getNombreEntidad() != null && identity.getNombreEntidad().toLowerCase().contains(filter.getNombreEntidad().toLowerCase())))
                .filter(identity -> filter.getNombre() == null || 
                        (identity.getNombre() != null && identity.getNombre().toLowerCase().contains(filter.getNombre().toLowerCase())))
                .filter(identity -> filter.getTipoEstrategiaIdentificacion() == null || 
                        (identity.getTipoEstrategiaIdentificacion() != null && identity.getTipoEstrategiaIdentificacion().toLowerCase().contains(filter.getTipoEstrategiaIdentificacion().toLowerCase())))
                .filter(identity -> filter.getTipoPractica() == null || 
                        (identity.getTipoPractica() != null && identity.getTipoPractica().toLowerCase().contains(filter.getTipoPractica().toLowerCase())))
                .filter(identity -> filter.getCodigoPractica() == null || 
                        (identity.getCodigoPractica() != null && identity.getCodigoPractica().toLowerCase().contains(filter.getCodigoPractica().toLowerCase())))
                .filter(identity -> {
                    if (filter.getRol() == null) {
                        return true;
                    }
                    String estadoFlujo = identity.getEstadoFlujo();
                    switch (filter.getRol().toLowerCase()) {
                        case "validador":
                            return "Candidata".equalsIgnoreCase(estadoFlujo) || "validacion".equalsIgnoreCase(estadoFlujo);
                        case "caracterizador":
                            return "caracterizacion".equalsIgnoreCase(estadoFlujo);
                        case "evaluador":
                            return "evaluacion".equalsIgnoreCase(estadoFlujo);
                        case "publicador":
                            return "establecida".equalsIgnoreCase(estadoFlujo);
                        default:
                            return true;
                    }
                })
                .collect(Collectors.toList());
    
        // Convertir las entidades filtradas a DTOs de respuesta
        return this.mapper.convertToListDto(filteredIdentities, IdentityFilterResponsive.class);
    }

    public Identity buscarHojaDeVida(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarHojaDeVida'");
    }
}
