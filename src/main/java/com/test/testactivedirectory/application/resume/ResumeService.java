package com.test.testactivedirectory.application.resume;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.test.testactivedirectory.application.email.service.EmailService;
import com.test.testactivedirectory.application.user.dto.UserWithRolesResponseDto;
import com.test.testactivedirectory.application.user.usecase.UserUseCase;
import com.test.testactivedirectory.infrastructure.persistence.entity.resumen.Identity;
import com.test.testactivedirectory.infrastructure.persistence.repository.HojaDeVida.ResumRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ResumeService {
    private final ResumRepository resumRepository;

    private final UserUseCase userService;

    private final EmailService emailService;

    public Identity registrarHojaDeVida(Identity hojadevida) {

        Identity hojadevidaguardada = resumRepository.save(hojadevida);

        List<UserWithRolesResponseDto> usuarios = this.userService.findByCargo("Validador");

        usuarios.forEach(usuario -> {
            sendEmailAsync(usuario);
        });

        return hojadevidaguardada;

    }

    public Identity buscarHojaDeVida(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarHojaDeVida'");
    }

    private void sendEmailAsync(UserWithRolesResponseDto user) {
        String subject = "Formulario completado con éxito";
        String body = String.format(
                "Hola %s,\n\nGracias por completar el formulario. Hemos recibido tus datos correctamente.\n\nSaludos,\nEl equipo.",
                user.getFullName());
        emailService.sendEmailAsync(user.getEmail(), subject, body);
    }
}
