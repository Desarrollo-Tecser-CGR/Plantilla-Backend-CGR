package com.cgr.bbp.application.resume.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IdentityRequestDto {
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Bogota")
    private Date fechaDiligenciamiento;

    @NotBlank
    @Size(max = 255)
    private String nombreEntidad;

    @NotBlank
    @Size(max = 255)
    private String nombreDependenciaArea;

    @NotBlank
    @Size(min = 1, max = 50)
    private String nombre;

    @NotBlank
    @Size(min = 0, max = 50)
    private String cargo;

    @NotBlank
    @Email
    @Size(max = 255)
    private String correo;

    @NotNull
    @Pattern(regexp = "^\\d{10}$")
    private String contacto;

    private Integer typeStrategyIdentification;

    private Integer typePractice;

    @Size(max = 255)
    private String codigoPractica;

    @NotNull
    private Integer typology;

    @Size(max = 255)
    private String estadoFlujo;

    private Integer levelGoodPractice;

    @Size(min = 0, max = 100)
    private String nombreDescriptivoBuenaPractica;

    @Size(min = 0, max = 300)
    private String propositoPractica;

    private Integer objectiveMainPractice;

    private List<Long> expectedImpact;

    @Size(min = 0, max = 500)
    private String metodologiaUsada;

    private Integer DurationImplementation;

    private List<Long> stagesMethodology;

    @Size(max = 255)
    private String periodoDesarrolloInicio;

    @Size(max = 255)
    private String periodoDesarrolloFin;

    private List<Long> typeMaterialProduced;

    private List<Long> supportReceived;

    private Integer recognitionsNationalInternational;

    private Integer controlObject;

    private List<Long> taxonomyEvent;

    private Integer typePerformance;

    @Size(max = 500)
    private String documentoActuacion;

    @Size(max = 1000)
    private String descripcionResultados;

}
