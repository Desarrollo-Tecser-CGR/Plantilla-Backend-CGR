package com.cgr.bbp.application.resume.dto;

import java.util.List;

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

import lombok.Data;

@Data
public class ResumTypeAllResponseDto {

    List<ControlObject> controlObjects;
    List<DurationImplementation> durationImplementations;
    List<ExpectedImpact> expectedImpacts;
    List<LevelGoodPractice> levelGoodPractice;
    List<ObjectiveMainPractice> objectiveMainPractices;
    List<RecognitionsNationalInternational> recognitionsNationalInternationals;
    List<StagesMethodology> stagesMethodologys;
    List<SupportReceived> supportReceiveds;
    List<TaxonomyEvent> taxonomyEvents;
    List<TypeMaterialProduced> typeMaterialProduceds;
    List<TypePerformance> typePerformances;
    List<TypePractice> typePractices;
    List<TypeStrategyIdentification> typeStrategyIdentifications;
    List<Typology> typologies;


}
