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

    @Column(name = "manager", length = 255, nullable = false)
    private String manager;

    @Column(name = "note_manager  ", length = 255, nullable = false)
    private String noteManager  ;

    @Column(name = "evaluator", length = 255, nullable = false)
    private String evaluator;

    @Column(name = "note_evalu", length = 255, nullable = false)
    private String noteEvalu;

    @Column(name = "committee", length = 255, nullable = false)
    private String committee;

    @Column(name = "note_commit", length = 255, nullable = false)
    private String noteCommit;

    @Column(name = "follow", length = 255, nullable = false)
    private String follow;

    @Column(name = "note_follow", length = 255, nullable = false)
    private String noteFollow;

    @Column(name = "evolution", length = 255, nullable = false)
    private String evolution;

    @Column(name = "note_evolu", length = 255, nullable = false)
    private String noteEvolu;

    @Column(name = "validator", length = 255, nullable = false)
    private String validator;

    @Column(name = "note_validator", length = 255, nullable = false)
    private String noteValidator;
}
