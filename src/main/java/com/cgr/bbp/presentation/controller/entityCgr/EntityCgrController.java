package com.cgr.bbp.presentation.controller.entityCgr;

import com.cgr.bbp.application.resume.caseUse.IEntityCgr;
import com.cgr.bbp.infrastructure.persistence.entity.entityCgr.EntityCgr;
import com.cgr.bbp.presentation.controller.AbstractController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/entityCgr")
public class EntityCgrController extends AbstractController {

    @Autowired
    private IEntityCgr serviceEntity;

    @GetMapping("/getAllEntities")
    public ResponseEntity<?> getAllEntities() {
        return requestResponse(
                serviceEntity.getAllEntityCgr(),
                "List of entities retrieved successfully",
                HttpStatus.OK,
                true
        );
    }

    @GetMapping("/getEntitiesByid/{id}")
    public ResponseEntity<?> getEntityById(@PathVariable Integer id) {
        Optional<EntityCgr> entity = serviceEntity.getEntityCgrById(id);
        return entity.isPresent()
            ? requestResponse(entity.get(), "Entity retrieved successfully", HttpStatus.OK, true)
            : requestResponse(null, "Entity not found", HttpStatus.NOT_FOUND, false);
    }

    @PostMapping("/createEntities")
    public ResponseEntity<?> createEntity(@RequestBody EntityCgr entityCgr) {
        EntityCgr createdEntity = serviceEntity.saveEntityCgr(entityCgr);
        return requestResponse(
                createdEntity,
                "Entity created successfully",
                HttpStatus.CREATED,
                true
        );
    }

    @PutMapping("/updateEntities/{id}")
    public ResponseEntity<?> updateEntity(@PathVariable Integer id, @RequestBody EntityCgr entityCgrDetails) {
        try {
            EntityCgr updatedEntity = serviceEntity.updateEntityCgr(id, entityCgrDetails);
            return requestResponse(
                    updatedEntity,
                    "Entity updated successfully",
                    HttpStatus.OK,
                    true
            );
        } catch (RuntimeException e) {
            return requestResponse(null, e.getMessage(), HttpStatus.NOT_FOUND, false);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEntity(@PathVariable Integer id) {
        try {
            serviceEntity.deleteEntityCgr(id);
            return requestResponse(
                    null,
                    "Entity deleted successfully",
                    HttpStatus.NO_CONTENT,
                    true
            );
        } catch (RuntimeException e) {
            return requestResponse(null, e.getMessage(), HttpStatus.NOT_FOUND, false);
        }
    }
}
