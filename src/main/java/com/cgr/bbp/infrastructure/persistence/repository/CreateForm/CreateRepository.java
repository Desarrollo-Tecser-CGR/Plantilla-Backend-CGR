package com.cgr.bbp.infrastructure.persistence.repository.CreateForm;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cgr.bbp.infrastructure.persistence.entity.CreateForm.Form;

public interface CreateRepository extends JpaRepository<Form, Integer> {

}
