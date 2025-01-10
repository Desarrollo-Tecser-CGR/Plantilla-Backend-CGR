package com.cgr.bbp.application.resume.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class IdentityResponseDto {

    private Integer id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Bogota")
    private Date fechaDiligenciamiento;

    private String entityCgr;

    private String nombreDependenciaArea;

    private String nombre;

    private String cargo;

    private String correo;

    private String contacto;

    private String typeStrategyIdentification;

    private String typePractice;

    private String codigoPractica;

    private String typology;

    private String estadoFlujo;

    private String levelGoodPractice;

    private String nombreDescriptivoBuenaPractica;

    private String propositoPractica;

    private String objectiveMainPractice;

    private List<String> expectedImpact;

    private String metodologiaUsada;

    private String DurationImplementation;

    private List<String> stagesMethodology;

    private String periodoDesarrolloInicio;

    private String periodoDesarrolloFin;

    private List<String> typeMaterialProduced;

    private List<String> supportReceived;

    private String recognitionsNationalInternational;

    private String controlObject;

    private List<String> taxonomyEvent;

    private String typePerformance;

    private String documentoActuacion;

    private String descripcionResultados;
}
