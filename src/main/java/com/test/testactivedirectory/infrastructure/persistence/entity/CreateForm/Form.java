package com.test.testactivedirectory.infrastructure.persistence.entity.CreateForm;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "Create_Form")
public class Form {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;

    @NotBlank 
    @Column(name = "name_form", length = 255, nullable = false)
    private String formName;

    @NotBlank
    @Lob
    @Column(name = "questions")
    private String questions;

}
