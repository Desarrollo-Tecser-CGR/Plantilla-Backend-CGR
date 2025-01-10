package com.cgr.bbp.application.resume.caseUse;

import java.util.List;

import javax.swing.text.html.parser.Entity;

import com.cgr.bbp.infrastructure.persistence.entity.entityCgr.EntityCgr;

public interface IEntityCgr {
    public abstract List<EntityCgr> getAllEntityCgr();

}
