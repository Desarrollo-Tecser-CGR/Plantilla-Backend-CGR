package com.cgr.bbp.application.resume.services;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.cgr.bbp.application.email.service.EmailService;
import com.cgr.bbp.application.resume.Utilities.ReferencedEntityResolver;
import com.cgr.bbp.application.resume.dto.IdentityFilterRequest;
import com.cgr.bbp.application.resume.dto.IdentityFilterResponse;
import com.cgr.bbp.application.resume.dto.IdentityRequestDto;
import com.cgr.bbp.application.resume.dto.IdentityResponseDto;
import com.cgr.bbp.application.resume.dto.ResumTypeAllResponseDto;
import com.cgr.bbp.application.resume.interfaces.IReferenceEntity;
import com.cgr.bbp.application.user.dto.UserWithRolesResponseDto;
import com.cgr.bbp.application.user.usecase.IUserUseCase;
import com.cgr.bbp.infrastructure.exception.customException.ResourceNotFoundException;
import com.cgr.bbp.infrastructure.persistence.entity.Types.LevelGoodPractice;
import com.cgr.bbp.infrastructure.persistence.entity.Types.ObjectiveMainPractice;
import com.cgr.bbp.infrastructure.persistence.entity.Types.RecognitionsNationalInternational;
import com.cgr.bbp.infrastructure.persistence.entity.Types.StagesMethodology;
import com.cgr.bbp.infrastructure.persistence.entity.Types.SupportReceived;
import com.cgr.bbp.infrastructure.persistence.entity.Types.TaxonomyEvent;
import com.cgr.bbp.infrastructure.persistence.entity.Types.TypeMaterialProduced;
import com.cgr.bbp.infrastructure.persistence.entity.Types.TypePerformance;
import com.cgr.bbp.infrastructure.persistence.entity.Types.TypePractice;
import com.cgr.bbp.infrastructure.persistence.entity.Types.TypeStrategyIdentification;
import com.cgr.bbp.infrastructure.persistence.entity.Types.Typology;
import com.cgr.bbp.infrastructure.persistence.entity.entityCgr.EntityCgr;
import com.cgr.bbp.infrastructure.persistence.entity.Types.ControlObject;
import com.cgr.bbp.infrastructure.persistence.entity.Types.DurationImplementation;
import com.cgr.bbp.infrastructure.persistence.entity.Types.ExpectedImpact;
import com.cgr.bbp.infrastructure.persistence.entity.resumen.Identity;
import com.cgr.bbp.infrastructure.persistence.repository.HojaDeVida.ResumRepository;
import com.cgr.bbp.infrastructure.persistence.repository.resume.IdentityRespository;
import com.cgr.bbp.infrastructure.utilities.helpers.DtoMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ResumeService {

        private final ResumRepository resumRepository;
        private final TemplateEngine templateEngine;
        private final IUserUseCase userService;
        private final IdentityRespository identityRespository;
        private final ReferencedEntityResolver entityResolver;

        private final EmailService emailService;
        private final DtoMapper mapper;

        @Transactional
        public IdentityResponseDto registrarHojaDeVida(IdentityRequestDto hojadevida) {

                TypeStrategyIdentification typeStrategy = this.identityRespository
                                .findTypeStrategyIdentificationById(hojadevida.getTypeStrategyIdentification())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "el tipo de estrategia con id="
                                                                + hojadevida.getTypeStrategyIdentification()
                                                                + " no existe"));

                EntityCgr entityCgr = this.identityRespository
                                .findEntityCgrById(hojadevida.getEntityCgr())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "el tipo de EntityCgr con id="
                                                                + hojadevida.getEntityCgr()
                                                                + " no existe"));

                TypePractice typePractice = this.identityRespository
                                .findTypePracticeById(hojadevida.getTypePractice())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "el tipo de práctica con id="
                                                                + hojadevida.getTypeStrategyIdentification()
                                                                + " no existe"));

                Typology typology = this.identityRespository
                                .findTypologybyId(hojadevida.getTypology())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "el tipo de Tipologia con id=" + hojadevida.getTypology()
                                                                + " no existe"));

                LevelGoodPractice levelGoodPractice = this.identityRespository
                                .findlevelGoodPrecticebyId(hojadevida.getLevelGoodPractice())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "el tipo de LevelGoodPractice con id="
                                                                + hojadevida.getLevelGoodPractice() + " no existe"));

                ObjectiveMainPractice objectiveMainPractice = this.identityRespository
                                .findonjectiveMainbyId(hojadevida.getObjectiveMainPractice())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "el tipo de objetivo con id=" + hojadevida.getObjectiveMainPractice()
                                                                + " no existe"));

                List<ExpectedImpact> expectedImpact = this.identityRespository
                                .findExpectedImpactByIds(hojadevida.getExpectedImpact());

                DurationImplementation durationImplementation = this.identityRespository
                                .findDurationImplementation(hojadevida.getDurationImplementation())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "el tipo de duracion de implementacion  con id="
                                                                + hojadevida.getDurationImplementation()
                                                                + " no existe"));

                List<StagesMethodology> stagesMethodology = this.identityRespository
                                .findStagesMethodologyByIds(hojadevida.getStagesMethodology());

                List<TypeMaterialProduced> typeMaterialProduced = this.identityRespository
                                .findTypeMaterialProducedByIds(hojadevida.getTypeMaterialProduced());

                List<SupportReceived> supportReceived = this.identityRespository
                                .findSupportReceivedByIds(hojadevida.getSupportReceived());

                RecognitionsNationalInternational recognitionsNationalInternational = this.identityRespository
                                .findrecognitionsNationalInternational(
                                                hojadevida.getRecognitionsNationalInternational())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "el tipo de reconocimiento nac e int con id="
                                                                + hojadevida.getRecognitionsNationalInternational()
                                                                + " no existe"));

                ControlObject controlObject = this.identityRespository
                                .findControlObject(hojadevida.getControlObject())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "el tipo de objeto control con id="
                                                                + hojadevida.getControlObject()
                                                                + " no existe"));

                List<TaxonomyEvent> taxonomyEvent = this.identityRespository
                                .findTaxonomyEventByIds(hojadevida.getTaxonomyEvent());

                TypePerformance typePerformance = this.identityRespository
                                .findTypePerformance(hojadevida.getTypePerformance())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "el tipo de taxonomia evento con id="
                                                                + hojadevida.getTypePerformance()
                                                                + " no existe"));

                Identity identity = this.mapperIdentityRequestDto(hojadevida);

                identity.setTypeStrategyIdentification(typeStrategy);
                identity.setEntityCgr(entityCgr);
                identity.setTypePractice(typePractice);
                identity.setTypology(typology);
                identity.setLevelGoodPractice(levelGoodPractice);
                identity.setObjectiveMainPractice(objectiveMainPractice);
                identity.setExpectedImpact(expectedImpact);
                identity.setDurationImplementation(durationImplementation);
                identity.setStagesMethodology(stagesMethodology);
                identity.setTypeMaterialProduced(typeMaterialProduced);
                identity.setSupportReceived(supportReceived);
                identity.setRecognitionsNationalInternational(recognitionsNationalInternational);
                identity.setControlObject(controlObject);
                identity.setTaxonomyEvent(taxonomyEvent);
                identity.setTypePerformance(typePerformance);

                Identity hojadevidaguardada = resumRepository.save(identity);

                List<UserWithRolesResponseDto> usuarios = this.userService.findByCargo("Validador");

                usuarios.forEach(usuario -> {
                        sendEmailAsync(usuario);
                });

                return this.mapperIdentityResponsetDto(hojadevidaguardada);

        }

        private Identity mapperIdentityRequestDto(IdentityRequestDto identityRequestDto) {
                Identity identity = new Identity();
                identity.setFechaDiligenciamiento(identityRequestDto.getFechaDiligenciamiento());
                // identity.setEntityCgr(identityRequestDto.getEntityCgr());
                identity.setNombreDependenciaArea(identityRequestDto.getNombreDependenciaArea());
                identity.setNombre(identityRequestDto.getNombre());
                identity.setCargo(identityRequestDto.getCargo());
                identity.setCorreo(identityRequestDto.getCorreo());
                identity.setContacto(identityRequestDto.getContacto());
                identity.setCodigoPractica(identityRequestDto.getCodigoPractica());
                // identity.setTipologia(identityRequestDto.getTipologia());
                identity.setEstadoFlujo(identityRequestDto.getEstadoFlujo());
                // identity.setNivelBuenaPractica(identityRequestDto.getNivelBuenaPractica());
                identity.setNombreDescriptivoBuenaPractica(identityRequestDto.getNombreDescriptivoBuenaPractica());
                identity.setPropositoPractica(identityRequestDto.getPropositoPractica());
                // identity.setObjetivoPrincipalPractica(identityRequestDto.getObjetivoPrincipalPractica());
                // identity.setImpactoEsperado(identityRequestDto.getImpactoEsperado());
                identity.setMetodologiaUsada(identityRequestDto.getMetodologiaUsada());
                // identity.setDuracionImplementacion(identityRequestDto.getDuracionImplementacion());
                // identity.setEtapasMetodologia(identityRequestDto.getEtapasMetodologia());
                identity.setPeriodoDesarrolloInicio(identityRequestDto.getPeriodoDesarrolloInicio());
                identity.setPeriodoDesarrolloFin(identityRequestDto.getPeriodoDesarrolloFin());
                // identity.setTipoMaterialProducido(identityRequestDto.getTipoMaterialProducido());
                // identity.setApoyoRecibido(identityRequestDto.getApoyoRecibido());
                // identity.setReconocimientosNacionalesInternacionales(
                // identityRequestDto.getReconocimientosNacionalesInternacionales());
                // identity.setObjetoControl(identityRequestDto.getObjetoControl());
                // identity.setTaxonomiaEvento(identityRequestDto.getTaxonomiaEvento());
                // identity.setTipoActuacion(identityRequestDto.getTipoActuacion());
                identity.setDocumentoActuacion(identityRequestDto.getDocumentoActuacion());
                identity.setDescripcionResultados(identityRequestDto.getDescripcionResultados());

                return identity;
        }

        private IdentityResponseDto mapperIdentityResponsetDto(Identity identity) {
                IdentityResponseDto dto = new IdentityResponseDto();
                dto.setId(identity.getId());
                dto.setFechaDiligenciamiento(identity.getFechaDiligenciamiento());
                dto.setEntityCgr(
                                identity.getEntityCgr() != null
                                                ? identity.getEntityCgr().getName()
                                                : null);
                dto.setNombreDependenciaArea(identity.getNombreDependenciaArea());
                dto.setNombre(identity.getNombre());
                dto.setCargo(identity.getCargo());
                dto.setCorreo(identity.getCorreo());
                dto.setContacto(identity.getContacto());
                dto.setTypeStrategyIdentification(
                                identity.getTypeStrategyIdentification() != null
                                                ? identity.getTypeStrategyIdentification().getName()
                                                : null);
                dto.setTypePractice(
                                identity.getTypePractice() != null ? identity.getTypePractice().getName() : null);
                dto.setCodigoPractica(identity.getCodigoPractica());
                dto.setTypology(identity.getTypology() != null ? identity.getTypology().getName() : null);
                dto.setEstadoFlujo(identity.getEstadoFlujo());
                dto.setLevelGoodPractice(
                                identity.getLevelGoodPractice() != null
                                                ? identity.getLevelGoodPractice().getName()
                                                : null);
                dto.setNombreDescriptivoBuenaPractica(identity.getNombreDescriptivoBuenaPractica());
                dto.setPropositoPractica(identity.getPropositoPractica());
                dto.setObjectiveMainPractice(
                                identity.getObjectiveMainPractice() != null
                                                ? identity.getObjectiveMainPractice().getName()
                                                : null);
                dto.setExpectedImpact(
                                identity.getExpectedImpact() != null
                                                ? identity.getExpectedImpact().stream().map(type -> {
                                                        return type.getName();
                                                }).toList()
                                                : null);
                dto.setMetodologiaUsada(identity.getMetodologiaUsada());
                dto.setDurationImplementation(
                                identity.getDurationImplementation() != null
                                                ? identity.getDurationImplementation().getName()
                                                : null);
                dto.setStagesMethodology(
                                identity.getStagesMethodology() != null
                                                ? identity.getStagesMethodology().stream().map(type -> {
                                                        return type.getName();
                                                }).toList()
                                                : null);
                dto.setPeriodoDesarrolloInicio(identity.getPeriodoDesarrolloInicio());
                dto.setPeriodoDesarrolloFin(identity.getPeriodoDesarrolloFin());
                dto.setTypeMaterialProduced(
                                identity.getTypeMaterialProduced() != null
                                                ? identity.getTypeMaterialProduced().stream().map(type -> {
                                                        return type.getName();
                                                }).toList()
                                                : null);
                dto.setSupportReceived(
                                identity.getSupportReceived() != null
                                                ? identity.getSupportReceived().stream().map(type -> {
                                                        return type.getName();
                                                }).toList()
                                                : null);
                dto.setRecognitionsNationalInternational(
                                identity.getRecognitionsNationalInternational() != null
                                                ? identity.getRecognitionsNationalInternational().getName()
                                                : null);
                dto.setControlObject(
                                identity.getControlObject() != null
                                                ? identity.getControlObject().getName()
                                                : null);
                dto.setTaxonomyEvent(
                                identity.getTaxonomyEvent() != null
                                                ? identity.getTaxonomyEvent().stream().map(type -> {
                                                        return type.getName();
                                                }).toList()
                                                : null);
                dto.setTypePerformance(
                                identity.getTypePerformance() != null
                                                ? identity.getTypePerformance().getName()
                                                : null);
                dto.setDocumentoActuacion(identity.getDocumentoActuacion());
                dto.setDescripcionResultados(identity.getDescripcionResultados());
                return dto;
        }

        @Transactional
        public List<IdentityFilterResponse> getListIdentity() {

                List<Identity> listIdentity = resumRepository.findAll();

                listIdentity = listIdentity.stream().collect(Collectors.toList());
                // Convertir las entidades filtradas a DTOs de respuesta
                return this.mapper.convertToListDto(listIdentity, IdentityFilterResponse.class);
        }

        @Transactional
        public Identity getIdentityById(Long id) {
                return resumRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "la hoja de vida con id=" + id + " no existe"));
        }

        @Transactional
        public Identity updateIdentityById(Long id, Identity updateIdentityById) {
                // Buscar la entidad existente
                Identity existingIdentity = resumRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "La hoja de vida con id=" + id + " no existe"));

                updateIdentityById.setId(id.intValue());

                return this.resumRepository.save(updateIdentityById);

        }

        @Transactional
        public Identity updateStatusById(Long id) {
                // Verifica si el ID proporcionado no es nulo
                if (id == null) {
                        throw new IllegalArgumentException("El ID proporcionado no puede ser nulo.");
                }

                // Obtén la entidad existente
                Identity existingEntity = this.resumRepository.findById(id).get();

                if (existingEntity == null) {
                        throw new IllegalArgumentException("La entidad con el ID proporcionado no existe.");
                }
                existingEntity.setEstadoFlujo("validacion");
                // Guarda la entidad actualizada
                try {
                        return this.resumRepository.save(existingEntity);
                } catch (Exception e) {
                        // Lanza una excepción más específica si la operación de guardado falla
                        throw new RuntimeException("Error al guardar la entidad en la base de datos: " + e.getMessage(),
                                        e);
                }

        }

        @Transactional
        public List<IdentityFilterResponse> getResumWithFilter(IdentityFilterRequest filter) {
                // Obtener todas las entidades
                List<Identity> identities = resumRepository.findAll();

                // Aplicar los filtros proporcionados
                List<Identity> filteredIdentities = identities.stream()
                                .filter(identity -> filter.getFechaDiligenciamientoInicio() == null ||
                                                (identity.getFechaDiligenciamiento() != null &&
                                                                !identity.getFechaDiligenciamiento().before(filter
                                                                                .getFechaDiligenciamientoInicio())))
                                .filter(identity -> filter.getFechaDiligenciamientoFinal() == null ||
                                                (identity.getFechaDiligenciamiento() != null &&
                                                                !identity.getFechaDiligenciamiento().after(filter
                                                                                .getFechaDiligenciamientoFinal())))
                                // .filter(identity -> filter.getEntityCgr() == null ||
                                //                 (identity.getEntityCgr() != null
                                //                                 && identity.getEntityCgr().getName().toLowerCase()
                                //                                                 .contains(filter.getEntityCgr()
                                //                                                                 .toLowerCase())))
                                .filter(identity -> filter.getNombre() == null ||
                                                (identity.getNombre() != null
                                                                && identity.getNombre().toLowerCase().contains(
                                                                                filter.getNombre().toLowerCase())))
                                // .filter(identity -> filter.getTipoEstrategiaIdentificacion() == null ||
                                // (identity.getTypeStrategyIdentification() != null
                                // && identity.getTypeStrategyIdentification().getName().toLowerCase()
                                // .contains(filter.getTipoEstrategiaIdentificacion().toLowerCase())))
                                // .filter(identity -> filter.getTipoPractica() == null ||
                                // (identity.getTypePractice() != null &&
                                // identity.getTypePractice().getName().toLowerCase()
                                // .contains(filter.getTipoPractica().toLowerCase())))
                                // .filter(identity -> filter.getCodigoPractica() == null ||
                                // (identity.getCodigoPractica() != null &&
                                // identity.getCodigoPractica().toLowerCase()
                                // .contains(filter.getCodigoPractica().toLowerCase()))
                                // )
                                .filter(identity -> {
                                        if (filter.getRol() == null) {
                                                return true;
                                        }
                                        String estadoFlujo = identity.getEstadoFlujo();
                                        switch (filter.getRol().toLowerCase()) {
                                                case "validador":
                                                        return "Candidata".equalsIgnoreCase(estadoFlujo)
                                                                        || "validacion".equalsIgnoreCase(estadoFlujo);
                                                case "caracterizador":
                                                        return "caracterizada".equalsIgnoreCase(estadoFlujo);
                                                case "evaluador":
                                                        return "evaluacion".equalsIgnoreCase(estadoFlujo);
                                                case "publicador":
                                                        return "establecida".equalsIgnoreCase(estadoFlujo);
                                                default:
                                                        return true;
                                        }
                                })
                                .collect(Collectors.toList());

                // Convertir las entidades filtradas a DTOs de respuesta
                return this.mapper.convertToListDto(filteredIdentities, IdentityFilterResponse.class);
        }

        public Identity buscarHojaDeVida(Long id) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'buscarHojaDeVida'");
        }

        // private void sendEmailAsync(UserWithRolesResponseDto user) {
        // String subject = "Formulario completado con éxito";
        // String body = String.format(
        // "Hola %s,\n\nGracias por completar el formulario. Hemos recibido tus datos
        // correctamente.\n\nSaludos,\nEl equipo.",
        // user.getFullName());
        // emailService.sendEmailAsync(user.getEmail(), subject, body);
        // }

        public void sendEmailAsync(UserWithRolesResponseDto user) {

                String subject = "Registro de hoja de vida exitoso";
                String imagePath = "src\\main\\resources\\img\\logo.png";

                // Configurar Thymeleaf context con variables dinámicas
                Context context = new Context();
                context.setVariable("name", user.getFullName());

                // Renderizar el template Thymeleaf
                String htmlContent = templateEngine.process("emailTemplate", context);

                // Llamar al servicio para enviar el correo
                emailService.sendHtmlEmailAsync(user.getEmail(), subject, htmlContent, imagePath);
        }

        @Transactional
        public ResumTypeAllResponseDto getListResumTypeAll() {
                ResumTypeAllResponseDto resumTypeAllResponseDto = new ResumTypeAllResponseDto();

                resumTypeAllResponseDto.setControlObjects(this.identityRespository.findAllControlObject());
                resumTypeAllResponseDto
                                .setDurationImplementations(this.identityRespository.findAllDurationImplementation());
                resumTypeAllResponseDto.setExpectedImpacts(this.identityRespository.findAllExpectedImpact());
                resumTypeAllResponseDto.setLevelGoodPractice(this.identityRespository.findAllLevelGoodPractice());
                resumTypeAllResponseDto
                                .setObjectiveMainPractices(this.identityRespository.findAllObjectiveMainPractice());
                resumTypeAllResponseDto.setRecognitionsNationalInternationals(
                                this.identityRespository.findAllRecognitionsNationalInternational());
                resumTypeAllResponseDto.setStagesMethodologys(this.identityRespository.findAllStagesMethodology());
                resumTypeAllResponseDto.setSupportReceiveds(this.identityRespository.findAllSupportReceived());
                resumTypeAllResponseDto.setTaxonomyEvents(this.identityRespository.findAllTaxonomyEvent());
                resumTypeAllResponseDto
                                .setTypeMaterialProduceds(this.identityRespository.findAllTypeMaterialProduced());
                resumTypeAllResponseDto.setTypePerformances(this.identityRespository.findAllTypePerformance());
                resumTypeAllResponseDto.setTypePractices(this.identityRespository.findAllTypePractice());
                resumTypeAllResponseDto.setTypeStrategyIdentifications(
                                this.identityRespository.findAllTypeStrategyIdentification());
                resumTypeAllResponseDto.setTypologies(this.identityRespository.findAllTypology());

                return resumTypeAllResponseDto;
        }

        @Transactional
        public Boolean actualizarParcial(Long id, Map<String, Object> actualizaciones) {

                Identity identidad = resumRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "La hoja de vida con id=" + id + " no existe"));

                actualizaciones.forEach((campo, valor) -> {
                        Field field = ReflectionUtils.findField(Identity.class, campo);
                        if (field != null) {
                                field.setAccessible(true);

                                // Verificar si el campo es una relación
                                // TODO: esto hay que mejorarlo, para que si valide correctamnte
                                if (IReferenceEntity.class.isAssignableFrom(field.getType())
                                                || List.class.isAssignableFrom(field.getType())) {
                                        Object entidadRelacionada = entityResolver.resolve(field.getType(),
                                                        valor, campo);
                                        ReflectionUtils.setField(field, identidad, entidadRelacionada);
                                } else {
                                        ReflectionUtils.setField(field, identidad, valor);
                                }
                        }
                });

                resumRepository.save(identidad);

                return true;
        }
}
