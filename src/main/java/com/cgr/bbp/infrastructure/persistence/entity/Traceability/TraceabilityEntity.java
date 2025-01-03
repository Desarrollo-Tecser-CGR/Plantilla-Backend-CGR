package com.cgr.bbp.infrastructure.persistence.entity.Traceability;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "traceability")
public class TraceabilityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación automática del ID
    private Integer id;

    @Column(name = "code", length = 255, nullable = false)
    private String codigo;

    @Column(name = "characterizer", length = 255, nullable = false)
    private String characterizer;

    @Column(name = "note_charac", length = 255, nullable = false)
    private String noteCharac;

    @Column(name = "date_charac", nullable = false)
    private String dateCharac;

    @Column(name = "manager", length = 255, nullable = false)
    private String manager;

    @Column(name = "note_manager", length = 255, nullable = false)
    private String noteManager;

    @Column(name = "date_managers", nullable = false)
    private String dateManagers;

    @Column(name = "evaluator", length = 255, nullable = false)
    private String evaluator;

    @Column(name = "note_evalu", length = 255, nullable = false)
    private String noteEvalu;

    @Column(name = "date_evalua", nullable = false)
    private String dateEvalua;

    @Column(name = "committee", length = 255, nullable = false)
    private String committee;

    @Column(name = "note_commit", length = 255, nullable = false)
    private String noteCommit;

    @Column(name = "date_commit", nullable = false)
    private String dateCommit;

    @Column(name = "follow", length = 255, nullable = false)
    private String follow;

    @Column(name = "note_follow", length = 255, nullable = false)
    private String noteFollow;

    @Column(name = "date_follow", nullable = false)
    private String dateFollow;

    @Column(name = "evolution", length = 255, nullable = false)
    private String evolution;

    @Column(name = "note_evolu", length = 255, nullable = false)
    private String noteEvolu;

    @Column(name = "date_evolut", nullable = false)
    private String dateEvolut;

    @Column(name = "validator", length = 255, nullable = false)
    private String validator;

    @Column(name = "note_validator", length = 255, nullable = false)
    private String noteValidator;

    @Column(name = "date_validator", nullable = false)
    private String dateValidator;
}