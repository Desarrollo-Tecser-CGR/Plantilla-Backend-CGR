package com.cgr.bbp.presentation.controller.entityCgr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgr.bbp.application.resume.caseUse.IEntityCgr;
import com.cgr.bbp.application.resume.services.ResumeService;
import com.cgr.bbp.presentation.controller.AbstractController;

@RestController
@RequestMapping("/api/v1/entityCgr")
public class EntityCgrController extends AbstractController{
    
    @Autowired
    private IEntityCgr serviceEntity;

    @GetMapping("/getAllEntitys")
    public ResponseEntity<?> getEntity() {
        return requestResponse(this.serviceEntity.getAllEntityCgr(), "listado de entidades", HttpStatus.OK, true);
    }

    
}
