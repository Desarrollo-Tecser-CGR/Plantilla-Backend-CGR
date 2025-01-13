package com.cgr.bbp.application.resume.services;

import com.cgr.bbp.application.resume.caseUse.IEntityCgr;
import com.cgr.bbp.infrastructure.persistence.entity.entityCgr.EntityCgr;
import com.cgr.bbp.infrastructure.persistence.repository.entityCgr.IEntityCgrRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EntityCgrImpl implements IEntityCgr {

    @Autowired
    private IEntityCgrRepository entityCgrRepository;

    @Override
    @Transactional(readOnly = true)
    public List<EntityCgr> getAllEntityCgr() {
        return entityCgrRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EntityCgr> getEntityCgrById(Integer id) {
        return entityCgrRepository.findById(id);
    }

    @Override
    @Transactional
    public EntityCgr saveEntityCgr(EntityCgr entityCgr) {
        return entityCgrRepository.save(entityCgr);
    }

    @Override
    @Transactional
    public EntityCgr updateEntityCgr(Integer id, EntityCgr entityCgrDetails) {
        return entityCgrRepository.findById(id).map(existingEntity -> {
            existingEntity.setName(entityCgrDetails.getName());
            return entityCgrRepository.save(existingEntity);
        }).orElseThrow(() -> new RuntimeException("EntityCgr not found with id " + id));
    }

    @Override
    @Transactional
    public void deleteEntityCgr(Integer id) {
        if (entityCgrRepository.existsById(id)) {
            entityCgrRepository.deleteById(id);
        } else {
            throw new RuntimeException("EntityCgr not found with id " + id);
        }
    }
}
