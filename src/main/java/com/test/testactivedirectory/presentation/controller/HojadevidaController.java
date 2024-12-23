package com.test.testactivedirectory.presentation.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.test.testactivedirectory.application.auth.dto.AuthRequestDto;
import com.test.testactivedirectory.application.email.service.EmailService;
import com.test.testactivedirectory.application.resume.ResumeService;
import com.test.testactivedirectory.application.resume.dto.IdentityFilterRequest;
import com.test.testactivedirectory.application.resume.dto.ValidateStatusDto;
import com.test.testactivedirectory.application.user.usecase.UserUseCase;
import com.test.testactivedirectory.infrastructure.exception.customException.ResourceNotFoundException;
import com.test.testactivedirectory.infrastructure.persistence.entity.resumen.Identity;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/hojadevida")
public class HojadevidaController extends AbstractController {

    @Autowired
    private ResumeService resumeService;
    private UserUseCase userService;
    private UserController userController;

    @Autowired
    private EmailService emailService;
    @PostMapping("/guardar")
    public ResponseEntity<?> saveHojadevida(@Valid @RequestBody Identity requesIdentity, BindingResult result) {
        return this.processRequest(result, () -> ResponseEntity.ok(resumeService.registrarHojaDeVida(requesIdentity)));
    }

    @PostMapping("/cargar-archivo")
    public ResponseEntity<?> cargarArchivo(@RequestPart(value = "file", required = false) MultipartFile file) {
        try {
            if (file != null && !file.isEmpty()) {
                // Guardar el archivo
                String fileName = saveFile(file);
                return ResponseEntity.ok("Archivo cargado exitosamente: " + fileName);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se proporcionó un archivo.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar el archivo: " + e.getMessage());
        }
    }

    // Método para guardar el archivo en la carpeta

    private String saveFile(MultipartFile file) throws IOException {
        // Define la ruta de la carpeta donde deseas guardar los archivos
        String directoryPath = "C:/mi-carpeta-de-archivos/";

        // Crea la carpeta si no existe
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        String fileName = file.getOriginalFilename();
        String filePath = directoryPath + fileName;
        Path path = Paths.get(filePath);
        Files.write(path, file.getBytes());

        return fileName;
    }

    @PostMapping("/inbox-bbp")
    public ResponseEntity<?> getInboxWithFilter(@Valid @RequestBody IdentityFilterRequest filter,
            BindingResult result) {
        return this.processRequest(result, () -> ResponseEntity.ok(this.resumeService.getResumWithFilter(filter)));
    }

    @PostMapping("/setValidateStatus")
    public ResponseEntity<?> setValidateStatus(
            @RequestBody ValidateStatusDto request,
            final HttpServletRequest servletRequest) {
        try {
            Identity updatedEntity = this.resumeService.updateStatusById(request.getId());

            return ResponseEntity.ok(updatedEntity);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor: " + e.getMessage());
        }
    }

    @GetMapping("/getIdentity")
    public ResponseEntity<?> getIdentity() {
        return ResponseEntity.ok(this.resumeService.getListIdentity());
    }

    @GetMapping("/getIdentity/{id}")
    public ResponseEntity<?> getIdentityById(@PathVariable Long id) {
        return ResponseEntity.ok(this.resumeService.getIdentityById(id));
    }

    @PutMapping("/updateIdentity/{id}")
    public ResponseEntity<?> updateIdentityById(@PathVariable Long id, @RequestBody Identity updatedIdentity) {
        try {
            Identity updatedEntity = resumeService.updateIdentityById(id, updatedIdentity);
            return ResponseEntity.ok(updatedEntity);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
  
    // Metodo obtener, falta arreglar para que funciona correctamente
    // @GetMapping("/obtener")
    // public ResponseEntity<?> getHojadevida(@RequestParam("id") Long id) {
    // try {
    // // Buscar la hoja de vida en la base de datos por ID
    // Identity identity = resumeService.buscarHojaDeVida(id);
    // if (identity == null) {
    // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hoja de vida no
    // encontrada.");
    // }
    // return ResponseEntity.ok(identity);
    // } catch (Exception e) {
    // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al
    // obtener la hoja de vida.");
    // }
    // }
}
