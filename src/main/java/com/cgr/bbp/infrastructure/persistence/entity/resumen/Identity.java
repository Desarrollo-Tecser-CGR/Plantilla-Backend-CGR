package com.cgr.bbp.infrastructure.persistence.entity.resumen;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.cgr.bbp.infrastructure.persistence.entity.entityCgr.EntityCgr;
import com.cgr.bbp.infrastructure.persistence.entity.file.FileEntity;
import com.cgr.bbp.infrastructure.persistence.entity.LogEntity;
import com.cgr.bbp.infrastructure.persistence.entity.Types.ControlObject;
import com.cgr.bbp.infrastructure.persistence.entity.Types.DurationImplementation;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "Identificacion")
public class Identity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación automática del ID
    private Integer id;

    @NotNull
    @Column(name = "Fecha_Diligenciamiento", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Bogota")
    private Date fechaDiligenciamiento;

    @ManyToOne
    @JoinColumn(name = "entity_cgr_id", nullable = true)
    private EntityCgr entityCgr;
  

    @NotBlank
    @Column(name = "Nombre_Dependencia_Area", length = 255)
    private String nombreDependenciaArea;

    @NotBlank
    @Column(name = "Nombre", length = 255, nullable = false)
    @Size(min = 1, max = 50)
    private String nombre;

    @NotBlank
    @Column(name = "Cargo", length = 255)
    @Size(min = 0, max = 50)
    private String cargo;

    @NotBlank
    @Email
    @Column(name = "Correo", length = 255)
    private String correo;

    @NotNull
    @Column(name = "Contacto", length = 255)
    @Pattern(regexp = "^\\d{10}$")
    private String contacto;

    @ManyToOne
    @JoinColumn(name = "Type_strategy_id", nullable = true)
    private TypeStrategyIdentification typeStrategyIdentification;

    @ManyToOne
    @JoinColumn(name = "Type_practice_id", nullable = true)
    private TypePractice typePractice;

    @Column(name = "Codigo_Practica", length = 255)
    private String codigoPractica;

    @ManyToOne
    @JoinColumn(name = "Typology_id", nullable = true)
    private Typology typology;

    @Column(name = "Estado_Flujo", length = 255)
    private String estadoFlujo;

    @ManyToOne
    @JoinColumn(name = "Level_good_practice", nullable = true)
    private LevelGoodPractice LevelGoodPractice;

    @Column(name = "Nombre_Descriptivo_Buena_Practica", length = 255)
    @Size(min = 0, max = 100)
    private String nombreDescriptivoBuenaPractica;

    @Column(name = "Proposito_Practica", length = 500)
    @Size(min = 0, max = 300)
    private String propositoPractica;

    @ManyToOne
    @JoinColumn(name = "Objective_main_practice_id", nullable = true)
    private ObjectiveMainPractice ObjectiveMainPractice;

    @ManyToMany
    @JoinTable(name = "resume_expect_impact", joinColumns = @JoinColumn(name = "resume_id"), inverseJoinColumns = @JoinColumn(name = "expect_id"), uniqueConstraints = {
            @UniqueConstraint(columnNames = { "resume_id", "expect_id" }) })
    private List<ExpectedImpact> expectedImpact;

    @Column(name = "Metodologia_Usada", length = 500)
    @Size(min = 0, max = 500)
    private String metodologiaUsada;

    @ManyToOne
    @JoinColumn(name = "Duration_implementation_id", nullable = true)
    private DurationImplementation durationImplementation;

    @ManyToMany
    @JoinTable(name = "resume_stages_methology", joinColumns = @JoinColumn(name = "resume_id"), inverseJoinColumns = @JoinColumn(name = "stages_methology_id"), uniqueConstraints = {
            @UniqueConstraint(columnNames = { "resume_id", "stages_methology_id" }) })
    private List<StagesMethodology> stagesMethodology;

    @Column(name = "Periodo_Desarrollo_Inicio", length = 255)
    private String periodoDesarrolloInicio;

    @Column(name = "Periodo_Desarrollo_Fin", length = 255)
    private String periodoDesarrolloFin;

    @ManyToMany
    @JoinTable(name = "resume_material_produced", joinColumns = @JoinColumn(name = "resume_id"), inverseJoinColumns = @JoinColumn(name = "material_produced_id"), uniqueConstraints = {
        @UniqueConstraint(columnNames = { "resume_id", "material_produced_id" }) })
    private List<TypeMaterialProduced> typeMaterialProduced;

    @ManyToMany
    @JoinTable(name = "resume_support_received", joinColumns = @JoinColumn(name = "resume_id"), inverseJoinColumns = @JoinColumn(name = "support_received_id"), uniqueConstraints = {
        @UniqueConstraint(columnNames = { "resume_id", "support_received_id" }) })
    private List<SupportReceived> supportReceived;

    @ManyToOne
    @JoinColumn(name = "Recognitions_national_international_id", nullable = true)
    private RecognitionsNationalInternational recognitionsNationalInternational;

    @ManyToOne
    @JoinColumn(name = "Control_object_id", nullable = true)
    private ControlObject controlObject;

    @ManyToMany
    @JoinTable(name = "resume_taxonomy_event", joinColumns = @JoinColumn(name = "resume_id"), inverseJoinColumns = @JoinColumn(name = "taxonomy_event_id"), uniqueConstraints = {
        @UniqueConstraint(columnNames = { "resume_id", "taxonomy_event_id" }) })
    private List<TaxonomyEvent> taxonomyEvent;

    @ManyToOne
    @JoinColumn(name = "Type_performance_id", nullable = true)
    private TypePerformance typePerformance;

    @Column(name = "Documento_Actuacion", length = 500)
    private String documentoActuacion;

    @Column(name = "Descripcion_Resultados", length = 1000)
    private String descripcionResultados;

    @OneToMany(mappedBy = "identity")
    private List<FileEntity> files = new ArrayList<>();

}
