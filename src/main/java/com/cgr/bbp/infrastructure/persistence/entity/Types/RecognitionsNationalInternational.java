package com.cgr.bbp.infrastructure.persistence.entity.Types;

import java.util.ArrayList;
import java.util.List;

import com.cgr.bbp.infrastructure.persistence.entity.resumen.Identity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "recognitions_national_international")
public class RecognitionsNationalInternational {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación automática del ID
    private Integer id;

    @NotBlank
    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "recognitionsNationalInternational")
    private List<Identity> identities = new ArrayList<>();
}
