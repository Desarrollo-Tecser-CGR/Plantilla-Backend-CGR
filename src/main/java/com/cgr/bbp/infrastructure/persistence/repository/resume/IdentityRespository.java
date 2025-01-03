package com.cgr.bbp.infrastructure.persistence.repository.resume;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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
import com.cgr.bbp.infrastructure.persistence.entity.resumen.Identity;

public interface IdentityRespository extends JpaRepository<Identity, Integer> {

    // Método para obtener TypeStrategyIdentification por su ID
    @Query("SELECT ts FROM TypeStrategyIdentification ts WHERE ts.id = :typeStrategyId")
    Optional<TypeStrategyIdentification> findTypeStrategyIdentificationById(
            @Param("typeStrategyId") Integer typeStrategyId);

    // Método para obtener TypePractice por su ID
    @Query("SELECT tp FROM TypePractice tp WHERE tp.id = :typePracticeId")
    Optional<TypePractice> findTypePracticeById(@Param("typePracticeId") Integer typePracticeId);

    // Método para obtener Typology por su ID
    @Query("SELECT t FROM Typology t WHERE t.id = :typologyId")
    Optional<Typology> findTypologybyId(@Param("typologyId") Integer typologyId);

    // Método para obtener LevelGoodPracticeId por su ID
    @Query("SELECT le FROM LevelGoodPractice le WHERE le.id = :LevelGoodPracticeId")
    Optional<LevelGoodPractice> findlevelGoodPrecticebyId(@Param("LevelGoodPracticeId") Integer LevelGoodPracticeId);

    // Método para obtener ObjectiveMainPractice por su ID
    @Query("SELECT ob FROM ObjectiveMainPractice ob WHERE ob.id = :ObjectiveMainPracticeId")
    Optional<ObjectiveMainPractice> findonjectiveMainbyId(
            @Param("ObjectiveMainPracticeId") Integer ObjectiveMainPracticeId);

    // Método para obtener ExpectedImpact por su ID
    @Query("SELECT ei FROM ExpectedImpact ei WHERE ei.id = :ExpectedImpactId")
    Optional<ExpectedImpact> findExpectImpact(@Param("ExpectedImpactId") Integer ExpectedImpactId);

    // Método para obtener DurationImplementation por su ID
    @Query("SELECT di FROM DurationImplementation di WHERE di.id = :DurationImplementationId")
    Optional<DurationImplementation> findDurationImplementation(
            @Param("DurationImplementationId") Integer DurationImplementationId);

    // Método para obtener StagesMethodology por su ID
    @Query("SELECT sm FROM StagesMethodology sm WHERE sm.id = :StagesMethodologyId")
    Optional<StagesMethodology> findStagesMethodology(@Param("StagesMethodologyId") Integer StagesMethodologyId);

    // Método para obtener TypeMaterialProduced por su ID
    @Query("SELECT tmp FROM TypeMaterialProduced tmp WHERE tmp.id = :TypeMaterialProducedId")
    Optional<TypeMaterialProduced> findTypeMaterialProduced(
            @Param("TypeMaterialProducedId") Integer TypeMaterialProducedId);

    // Método para obtener SupportReceived por su ID
    @Query("SELECT sr FROM SupportReceived sr WHERE sr.id = :SupportReceivedId")
    Optional<SupportReceived> findSupportReceived(@Param("SupportReceivedId") Integer SupportReceivedId);

    // Método para obtener RecognitionsNationalInternational por su ID
    @Query("SELECT rn FROM RecognitionsNationalInternational rn WHERE rn.id = :RecognitionsNationalInternationalId")
    Optional<RecognitionsNationalInternational> findrecognitionsNationalInternational(
            @Param("RecognitionsNationalInternationalId") Integer RecognitionsNationalInternationalId);

    // Método para obtener ControlObject por su ID
    @Query("SELECT co FROM ControlObject co WHERE co.id = :ControlObjectId")
    Optional<ControlObject> findControlObject(@Param("ControlObjectId") Integer ControlObjectId);

    // Método para obtener TaxonomyEvent por su ID
    @Query("SELECT te FROM TaxonomyEvent te WHERE te.id = :TaxonomyEventId")
    Optional<TaxonomyEvent> findTaxonomyEvent(@Param("TaxonomyEventId") Integer TaxonomyEventId);

    // Método para obtener TypePerformance por su ID
    @Query("SELECT tp FROM TypePerformance tp WHERE tp.id = :TypePerformanceId")
    Optional<TypePerformance> findTypePerformance(@Param("TypePerformanceId") Integer TypePerformanceId);

}
