package com.cgr.bbp.presentation.controller.Traceability;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgr.bbp.application.traceability.services.TraceabilityService;
import com.cgr.bbp.application.traceability.usecase.ITraceabilityUseCase;
import com.cgr.bbp.infrastructure.persistence.entity.Traceability.TraceabilityEntity;
import com.cgr.bbp.infrastructure.persistence.entity.resumen.Identity;
import com.cgr.bbp.presentation.controller.AbstractController;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/traceability")
public class TraceabilityController extends AbstractController {

    @Autowired
    private ITraceabilityUseCase traceabilityService;

    @PostMapping("saveTraceability")
    public ResponseEntity<?> postsaveTraceability(@Valid @RequestBody TraceabilityEntity requesIdentity, BindingResult result) {
         return  this.requestResponse(result, () -> traceabilityService.createTraceability(requesIdentity),
                "Traceabiliti is Created", HttpStatus.OK,
                true);        
        
    }
}
