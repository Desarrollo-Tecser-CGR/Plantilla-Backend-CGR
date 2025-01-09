package com.cgr.bbp.application.resume.Utilities;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cgr.bbp.infrastructure.exception.customException.ResourceNotFoundException;
import com.cgr.bbp.infrastructure.persistence.entity.Types.ControlObject;
import com.cgr.bbp.infrastructure.persistence.entity.Types.DurationImplementation;
import com.cgr.bbp.infrastructure.persistence.entity.Types.ExpectedImpact;
import com.cgr.bbp.infrastructure.persistence.entity.Types.LevelGoodPractice;
import com.cgr.bbp.infrastructure.persistence.entity.Types.ObjectiveMainPractice;
import com.cgr.bbp.infrastructure.persistence.entity.Types.RecognitionsNationalInternational;
import com.cgr.bbp.infrastructure.persistence.entity.Types.StagesMethodology;
import com.cgr.bbp.infrastructure.persistence.entity.Types.SupportReceived;
import com.cgr.bbp.infrastructure.persistence.entity.Types.TaxonomyEvent;
import com.cgr.bbp.infrastructure.persistence.entity.Types.TypeMaterialProduced;
import com.cgr.bbp.infrastructure.persistence.entity.Types.TypePerformance;
import com.cgr.bbp.infrastructure.persistence.entity.Types.TypePractice;
import com.cgr.bbp.infrastructure.persistence.entity.Types.TypeStrategyIdentification;
import com.cgr.bbp.infrastructure.persistence.entity.Types.Typology;
import com.cgr.bbp.infrastructure.persistence.repository.resume.IdentityRespository;

@Component
public class ReferencedEntityResolver {

    @Autowired
    private IdentityRespository identityRepository;

    public Object resolve(Class<?> type, Object id, String campo) {

        if (type.equals(TypeStrategyIdentification.class)) {
            return identityRepository.findTypeStrategyIdentificationById((Integer) id)
                    .orElseThrow(() -> new ResourceNotFoundException("Tipo de estrategia seleccionada no existe"));
        } else if (type.equals(TypePractice.class)) {
            return identityRepository.findTypePracticeById((Integer) id)
                    .orElseThrow(() -> new ResourceNotFoundException("Tipo de práctica seleccionada no existe"));
        } else if (type.equals(Typology.class)) {
            return identityRepository.findTypologybyId((Integer) id)
                    .orElseThrow(() -> new ResourceNotFoundException("Tipología seleccionada no existe"));
        } else if (type.equals(LevelGoodPractice.class)) {
            return identityRepository.findlevelGoodPrecticebyId((Integer) id)
                    .orElseThrow(() -> new ResourceNotFoundException("Nivel de buena práctica seleccionado no existe"));
        } else if (type.equals(ObjectiveMainPractice.class)) {
            return identityRepository.findonjectiveMainbyId((Integer) id)
                    .orElseThrow(() -> new ResourceNotFoundException("Objetivo principal seleccionado no existe"));
        } else if (type.equals(ExpectedImpact.class)) {
            return identityRepository.findExpectImpact((Integer) id)
                    .orElseThrow(() -> new ResourceNotFoundException("Impacto esperado seleccionado no existe"));
        } else if (type.equals(DurationImplementation.class)) {
            return identityRepository.findDurationImplementation((Integer) id)
                    .orElseThrow(
                            () -> new ResourceNotFoundException("Duración de implementación seleccionada no existe"));
        } else if (type.equals(StagesMethodology.class)) {
            return identityRepository.findStagesMethodology((Integer) id)
                    .orElseThrow(() -> new ResourceNotFoundException("Etapas de metodología seleccionadas no existen"));
        } else if (type.equals(TypeMaterialProduced.class)) {
            return identityRepository.findTypeMaterialProduced((Integer) id)
                    .orElseThrow(
                            () -> new ResourceNotFoundException("Tipo de material producido seleccionado no existe"));
        } else if (type.equals(SupportReceived.class)) {
            return identityRepository.findSupportReceived((Integer) id)
                    .orElseThrow(() -> new ResourceNotFoundException("Soporte recibido seleccionado no existe"));
        } else if (type.equals(RecognitionsNationalInternational.class)) {
            return identityRepository.findrecognitionsNationalInternational((Integer) id)
                    .orElseThrow(
                            () -> new ResourceNotFoundException("Reconocimiento nacional/internacional no existe"));
        } else if (type.equals(ControlObject.class)) {
            return identityRepository.findControlObject((Integer) id)
                    .orElseThrow(() -> new ResourceNotFoundException("Objeto de control seleccionado no existe"));
        } else if (type.equals(TaxonomyEvent.class)) {
            return identityRepository.findTaxonomyEvent((Integer) id)
                    .orElseThrow(() -> new ResourceNotFoundException("Evento de taxonomía seleccionado no existe"));
        } else if (type.equals(TypePerformance.class)) {
            return identityRepository.findTypePerformance((Integer) id)
                    .orElseThrow(() -> new ResourceNotFoundException("Tipo de rendimiento seleccionado no existe"));
        } else if (type.equals(List.class)) {
            switch (campo) {
                case "expectedImpact":
                    return identityRepository.findExpectedImpactByIds((List<Long>) id);
                case "stagesMethodology":
                    return identityRepository.findStagesMethodologyByIds((List<Long>) id);
                case "typeMaterialProduced":
                    return identityRepository.findTypeMaterialProducedByIds((List<Long>) id);
                case "supportReceived":
                    return identityRepository.findSupportReceivedByIds((List<Long>) id);
                case "taxonomyEvent":
                    return identityRepository.findTaxonomyEventByIds((List<Long>) id);
                default:
                    break;
            }

        }

        throw new IllegalArgumentException("Tipo de entidad desconocido: " + type.getName());
    }

}
