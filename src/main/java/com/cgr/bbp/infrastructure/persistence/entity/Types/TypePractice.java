package com.cgr.bbp.infrastructure.persistence.entity.Types;

import java.util.ArrayList;
import java.util.List;

import com.cgr.bbp.infrastructure.persistence.entity.resumen.Identity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "type_practice")
public class TypePractice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación automática del ID
    private Integer id;

    @NotBlank
    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @OneToMany(mappedBy = "typePractice")
    private List<Identity> identities = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Identity> getIdentities() {
        return identities;
    }

    public void setIdentities(List<Identity> identities) {
        this.identities = identities;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((identities == null) ? 0 : identities.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TypePractice other = (TypePractice) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (identities == null) {
            if (other.identities != null)
                return false;
        } else if (!identities.equals(other.identities))
            return false;
        return true;
    }

}
