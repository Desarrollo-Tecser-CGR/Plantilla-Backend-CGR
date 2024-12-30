package com.cgr.bbp.application.resume.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IdentityFilterRequest {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Bogota")
    private Date fechaDiligenciamientoInicio;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "America/Bogota")
    private Date fechaDiligenciamientoFinal;

    private String nombreEntidad;

    private String nombre;

    private String tipoEstrategiaIdentificacion;

    private String tipoPractica;

    private String codigoPractica;

    private String rol;

}
