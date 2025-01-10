package com.cgr.bbp.infrastructure.persistence.entity.file;

import java.util.ArrayList;
import java.util.List;

import com.cgr.bbp.infrastructure.persistence.entity.UserEntity;
import com.cgr.bbp.infrastructure.persistence.entity.resumen.Identity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "file_identity")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación automática del ID
    private Integer id;
    
    @NotBlank
    @Column(name = "path", length = 255, nullable = false)
    private String path;

    @NotBlank
    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "identity_id", nullable = false)
    private Identity identity;

}
