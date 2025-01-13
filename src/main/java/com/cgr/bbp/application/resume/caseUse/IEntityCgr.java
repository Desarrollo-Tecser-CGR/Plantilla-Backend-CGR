package com.cgr.bbp.application.resume.caseUse;

import java.util.List;
import java.util.Optional;

import javax.swing.text.html.parser.Entity;

import com.cgr.bbp.infrastructure.persistence.entity.entityCgr.EntityCgr;

public interface IEntityCgr {
    public abstract List<EntityCgr> getAllEntityCgr();
    Optional<EntityCgr> getEntityCgrById(Integer id);
    EntityCgr saveEntityCgr(EntityCgr entityCgr);
    EntityCgr updateEntityCgr(Integer id, EntityCgr entityCgrDetails);
    void deleteEntityCgr(Integer id);
}
