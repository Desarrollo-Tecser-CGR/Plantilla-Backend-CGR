package com.test.testactivedirectory.application.resume;

import java.util.List;
import java.util.stream.Collectors;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.testactivedirectory.application.email.service.EmailService;
import com.test.testactivedirectory.application.resume.dto.IdentityFilterRequest;
import com.test.testactivedirectory.application.resume.dto.IdentityFilterResponse;
import com.test.testactivedirectory.application.user.dto.UserWithRolesResponseDto;
import com.test.testactivedirectory.application.user.usecase.UserUseCase;
import com.test.testactivedirectory.infrastructure.exception.customException.ResourceNotFoundException;
import com.test.testactivedirectory.infrastructure.persistence.entity.resumen.Identity;
import com.test.testactivedirectory.infrastructure.persistence.repository.HojaDeVida.ResumRepository;
import com.test.testactivedirectory.infrastructure.utilities.DtoMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ResumeService {

    private final ResumRepository resumRepository;

    private final UserUseCase userService;

    private final EmailService emailService;
    private final DtoMapper mapper;

    public Identity registrarHojaDeVida(Identity hojadevida) {

        Identity hojadevidaguardada = resumRepository.save(hojadevida);

        List<UserWithRolesResponseDto> usuarios = this.userService.findByCargo("Validador");

        usuarios.forEach(usuario -> {
            sendEmailAsync(usuario);
        });

        return hojadevidaguardada;

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
                .orElseThrow(() -> new ResourceNotFoundException("la hoja de vida con id=" + id + " no existe"));
    }

    @Transactional
    public Identity updateIdentityById(Long id, Identity updateIdentityById) {
        // Buscar la entidad existente
        Identity existingIdentity = resumRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La hoja de vida con id=" + id + " no existe"));

        updateIdentityById.setId(id.intValue());

        return this.resumRepository.save(updateIdentityById);

    }

    @Transactional
    public List<IdentityFilterResponse> getResumWithFilter(IdentityFilterRequest filter) {
        // Obtener todas las entidades
        List<Identity> identities = resumRepository.findAll();

        // Aplicar los filtros proporcionados
        List<Identity> filteredIdentities = identities.stream()
                .filter(identity -> filter.getFechaDiligenciamientoInicio() == null ||
                        (identity.getFechaDiligenciamiento() != null &&
                                !identity.getFechaDiligenciamiento().before(filter.getFechaDiligenciamientoInicio())))
                .filter(identity -> filter.getFechaDiligenciamientoFinal() == null ||
                        (identity.getFechaDiligenciamiento() != null &&
                                !identity.getFechaDiligenciamiento().after(filter.getFechaDiligenciamientoFinal())))
                .filter(identity -> filter.getNombreEntidad() == null ||
                        (identity.getNombreEntidad() != null && identity.getNombreEntidad().toLowerCase()
                                .contains(filter.getNombreEntidad().toLowerCase())))
                .filter(identity -> filter.getNombre() == null ||
                        (identity.getNombre() != null
                                && identity.getNombre().toLowerCase().contains(filter.getNombre().toLowerCase())))
                .filter(identity -> filter.getTipoEstrategiaIdentificacion() == null ||
                        (identity.getTipoEstrategiaIdentificacion() != null
                                && identity.getTipoEstrategiaIdentificacion().toLowerCase()
                                        .contains(filter.getTipoEstrategiaIdentificacion().toLowerCase())))
                .filter(identity -> filter.getTipoPractica() == null ||
                        (identity.getTipoPractica() != null && identity.getTipoPractica().toLowerCase()
                                .contains(filter.getTipoPractica().toLowerCase())))
                .filter(identity -> filter.getCodigoPractica() == null ||
                        (identity.getCodigoPractica() != null && identity.getCodigoPractica().toLowerCase()
                                .contains(filter.getCodigoPractica().toLowerCase())))
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
                            return "caracterizacion".equalsIgnoreCase(estadoFlujo);
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
    //     String subject = "Formulario completado con éxito";
    //     String body = String.format(
    //             "Hola %s,\n\nGracias por completar el formulario. Hemos recibido tus datos correctamente.\n\nSaludos,\nEl equipo.",
    //             user.getFullName());        
    //     emailService.sendEmailAsync(user.getEmail(), subject, body);
    // }

    private void sendEmailAsync(UserWithRolesResponseDto user) {
        String subject = "Confirmación de Correo Electrónico";
    
        // Contenido HTML de la plantilla
        String htmlContent = """
                <style>
                    body {
                        font-family: Arial, sans-serif;
                        background-color: #f4f4f4;
                        margin: 0;
                        padding: 0;
                    }
                    .email-container {
                        max-width: 600px;
                        margin: 20px auto;
                        background-color: #ffffff;
                        border: 1px solid #dddddd;
                        border-radius: 8px;
                        padding: 20px;
                    }
                    .header {
                        text-align: center;
                        padding: 20px 0;
                        border-bottom: 1px solid #dddddd;
                    }
                    .header h1 {
                        color: #333333;
                        margin: 0;
                        font-size: 24px;
                    }
                    .content {
                        padding: 20px;
                        color: #555555;
                        font-size: 16px;
                        line-height: 1.6;
                    }
                    .button-container {
                        text-align: center;
                        margin: 20px 0;
                    }
                    .button {
                        background-color: #007bff;
                        color: #ffffff;
                        padding: 10px 20px;
                        text-decoration: none;
                        border-radius: 5px;
                        font-size: 16px;
                    }
                    .footer {
                        text-align: center;
                        font-size: 12px;
                        color: #999999;
                        padding: 10px;
                    }
                </style>
                <div class="email-container">
                    <div class="header">
                        <h1>Confirmación de Correo Electrónico</h1>
                    </div>
                    <div class="content">
                        <p>Estimado/a <strong>%s</strong>,</p>
                        <p>Gracias por registrarte con nosotros. Por favor, confirma tu dirección de correo electrónico haciendo clic en el botón de abajo.</p>
                        <div class="button-container">
                            <a href="http://localhost:4200/example" class="button">Ingrese Aquí</a>
                        </div>
                        <p>Si no solicitaste esta verificación, puedes ignorar este mensaje.</p>
                        <p>Atentamente,<br>El equipo de [Tu Empresa]</p>
                    </div>
                    <div class="footer">
                        <p>Este correo fue enviado automáticamente. Por favor, no respondas a este mensaje.</p>
                        <p>&copy; 2024 [Tu Empresa]. Todos los derechos reservados.</p>
                    </div>
                </div>
                """;
    
        // Personalizar la plantilla con el nombre del usuario
        String formattedHtmlContent = String.format(htmlContent, user.getFullName());
    
        // Llamada al método de tu emailService (ajustar aquí para contenido HTML)
        emailService.sendHtmlEmailAsync(user.getEmail(), subject, formattedHtmlContent);
    }
    
}
