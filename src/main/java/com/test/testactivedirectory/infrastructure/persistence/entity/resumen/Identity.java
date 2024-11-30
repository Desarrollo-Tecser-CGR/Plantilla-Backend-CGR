package com.test.testactivedirectory.infrastructure.persistence.entity.resumen;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "Identificacion")
public class Identity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación automática del ID
    private Integer id;

    @Column(name = "Fecha_Diligenciamiento", nullable = false)
    private String fechaDiligenciamiento;

    @Column(name = "Nombre_Entidad", length = 255, nullable = false)
    private String nombreEntidad;

    @Column(name = "Nombre_Dependencia_Area", length = 255)
    private String nombreDependenciaArea;

    @Column(name = "Nombre", length = 255, nullable = false)
    private String nombre;

    @Column(name = "Cargo", length = 255)
    private String cargo;

    @Column(name = "Correo", length = 255)
    private String correo;

    @Column(name = "Contacto", length = 255)
    private String contacto;

    @Column(name = "Tipo_Estrategia_Identificacion", length = 255, nullable = false)
    private String tipoEstrategiaIdentificacion;

    @Column(name = "Tipo_Practica", length = 255, nullable = false)
    private String tipoPractica;

    @Column(name = "Codigo_Practica", length = 255)
    private String codigoPractica;

    @Column(name = "Tipologia", length = 255)
    private String tipologia;

    @Column(name = "Estado_Flujo", length = 255)
    private String estadoFlujo;

    @Column(name = "Nivel_Buena_Practica", length = 255)
    private String nivelBuenaPractica;

    @Column(name = "Nombre_Descriptivo_Buena_Practica", length = 255)
    private String nombreDescriptivoBuenaPractica;

    @Column(name = "Proposito_Practica", length = 500)
    private String propositoPractica;

    @Column(name = "Objetivo_Principal_Practica", length = 500)
    private String objetivoPrincipalPractica;

    @Column(name = "Impacto_Esperado", length = 500)
    private String impactoEsperado;

    @Column(name = "Metodologia_Usada", length = 500)
    private String metodologiaUsada;

    @Column(name = "Duracion_Implementacion", length = 255)
    private String duracionImplementacion;

    @Column(name = "Etapas_Metodologia", length = 500)
    private String etapasMetodologia;

    @Column(name = "Periodo_Desarrollo", length = 255)
    private String periodoDesarrollo;

    @Column(name = "Tipo_Material_Producido", length = 255)
    private String tipoMaterialProducido;

    @Column(name = "Apoyo_Recibido", length = 500)
    private String apoyoRecibido;

    @Column(name = "Reconocimientos_Nacionales_Internacionales", length = 500)
    private String reconocimientosNacionalesInternacionales;

    @Column(name = "Objeto_Control", length = 255)
    private String objetoControl;

    @Column(name = "Taxonomia_Evento", length = 255)
    private String taxonomiaEvento;

    @Column(name = "Tipo_Actuacion", length = 255)
    private String tipoActuacion;

    @Column(name = "Documento_Actuacion", length = 500)
    private String documentoActuacion;

    @Column(name = "Descripcion_Resultados", length = 1000)
    private String descripcionResultados;

}
