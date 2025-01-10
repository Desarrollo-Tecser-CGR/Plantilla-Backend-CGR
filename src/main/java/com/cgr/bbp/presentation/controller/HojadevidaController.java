package com.cgr.bbp.presentation.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cgr.bbp.application.auth.dto.AuthRequestDto;
import com.cgr.bbp.application.email.service.EmailService;
import com.cgr.bbp.application.resume.dto.IdentityFilterRequest;
import com.cgr.bbp.application.resume.dto.IdentityRequestDto;
import com.cgr.bbp.application.resume.dto.ValidateStatusDto;
import com.cgr.bbp.application.resume.services.FileService;
import com.cgr.bbp.application.resume.services.ResumeService;
import com.cgr.bbp.application.user.usecase.IUserUseCase;
import com.cgr.bbp.infrastructure.exception.customException.ResourceNotFoundException;
import com.cgr.bbp.infrastructure.persistence.entity.resumen.Identity;
import com.fasterxml.jackson.core.JsonProcessingException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/hojadevida")
public class HojadevidaController extends AbstractController {

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private FileService fileService;
    private IUserUseCase userService;
    private UserController userController;

    @Autowired
    private EmailService emailService;

    @PostMapping("/guardar")
    public ResponseEntity<?> saveHojadevida(@Valid @RequestBody IdentityRequestDto requesIdentity,
            BindingResult result) {
        return this.requestResponse(result, () -> resumeService.registrarHojaDeVida(requesIdentity),
                "Hoja de vida guardada", HttpStatus.OK,
                true);
    }

    @PostMapping("/cargar-archivo")
    public ResponseEntity<?> cargarArchivos(
            @RequestPart(value = "files", required = false) List<MultipartFile> files,
            @RequestParam(value = "identityId") Integer identityId) {
        try {
            // Verificar si existe la identidad
            Identity identity = resumeService.getIdentityById(identityId.longValue());

            if (files == null || files.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se proporcionaron archivos.");
            }

            // Guardar archivos usando el servicio
            List<String> fileNames = fileService.saveFiles(files, identity);

            return ResponseEntity.ok("Archivos cargados exitosamente: " + String.join(", ", fileNames));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar los archivos: " + e.getMessage());
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
        return this.requestResponse(result, () -> this.resumeService.getResumWithFilter(filter), "", HttpStatus.OK,
                true);
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

    @PatchMapping("updateIdentity/{id}")
    public ResponseEntity<?> actualizarParcial(
            @PathVariable Long id,
            @RequestBody Map<String, Object> actualizaciones) {

        return requestResponse(resumeService.actualizarParcial(id, actualizaciones), "hoja de vida actualizada",
                HttpStatus.OK, true);
    }

    @GetMapping("/getAllTypes")
    public ResponseEntity<?> getAllResumType() {
        return requestResponse(this.resumeService.getListResumTypeAll(), "listado de tipos", HttpStatus.OK, true);
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
