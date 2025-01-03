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
import java.util.Date;

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

    @NotBlank
    @Column(name = "Nombre_Entidad", length = 255, nullable = false)
    private String nombreEntidad;

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

    @ManyToOne
    @JoinColumn(name = "Expected_impact_id", nullable = true)
    private ExpectedImpact expectedImpact;

    @Column(name = "Metodologia_Usada", length = 500)
    @Size(min = 0, max = 500)
    private String metodologiaUsada;

    @ManyToOne
    @JoinColumn(name = "Duration_implementation_id", nullable = true)
    private DurationImplementation durationImplementation;

    @ManyToOne
    @JoinColumn(name = "Stages_methodology_id", nullable = true)
    private StagesMethodology stagesMethodology;

    @Column(name = "Periodo_Desarrollo_Inicio", length = 255)
    private String periodoDesarrolloInicio;

    @Column(name = "Periodo_Desarrollo_Fin", length = 255)
    private String periodoDesarrolloFin;

    @ManyToOne
    @JoinColumn(name = "Type_material_produced_id", nullable = true)
    private TypeMaterialProduced typeMaterialProduced;

    @ManyToOne
    @JoinColumn(name = "Support_received_id", nullable = true)
    private SupportReceived supportReceived;

    @ManyToOne
    @JoinColumn(name = "Recognitions_national_international_id", nullable = true)
    private RecognitionsNationalInternational recognitionsNationalInternational;

    @ManyToOne
    @JoinColumn(name = "Control_object_id", nullable = true)
    private ControlObject controlObject;

    @ManyToOne
    @JoinColumn(name = "Taxonomy_event_id", nullable = true)
    private TaxonomyEvent taxonomyEvent;

    @ManyToOne
    @JoinColumn(name = "Type_performance_id", nullable = true)
    private TypePerformance typePerformance;

    @Column(name = "Documento_Actuacion", length = 500)
    private String documentoActuacion;

    @Column(name = "Descripcion_Resultados", length = 1000)
    private String descripcionResultados;

}
