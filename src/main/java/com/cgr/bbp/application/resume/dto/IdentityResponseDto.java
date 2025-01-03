package com.cgr.bbp.application.resume.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class IdentityResponseDto {

    private Integer id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Bogota")
    private Date fechaDiligenciamiento;

    private String nombreEntidad;

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

    private String expectedImpact;

    private String metodologiaUsada;

    private String DurationImplementation;

    private String stagesMethodology;

    private String periodoDesarrolloInicio;

    private String periodoDesarrolloFin;

    private String typeMaterialProduced;

    private String supportReceived;

    private String recognitionsNationalInternational;

    private String controlObject;

    private String taxonomyEvent;

    private String typePerformance;

    private String documentoActuacion;

    private String descripcionResultados;
}
