package com.cgr.bbp.application.resume.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cgr.bbp.application.resume.caseUse.IEntityCgr;
import com.cgr.bbp.infrastructure.persistence.entity.entityCgr.EntityCgr;
import com.cgr.bbp.infrastructure.persistence.repository.entityCgr.IEntityCgrRepository;

@Service
public class EntityCgrImpl implements IEntityCgr {

    @Autowired
    private IEntityCgrRepository entityCgrRepository;

    @Override
    @Transactional
    public List<EntityCgr> getAllEntityCgr() {
        return entityCgrRepository.findAll();
    };
}
