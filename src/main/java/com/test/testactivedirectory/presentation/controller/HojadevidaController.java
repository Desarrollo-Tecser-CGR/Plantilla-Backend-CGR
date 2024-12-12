package com.test.testactivedirectory.presentation.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.test.testactivedirectory.application.email.service.EmailService;
import com.test.testactivedirectory.application.resume.ResumeService;
import com.test.testactivedirectory.application.user.usecase.UserUseCase;
import com.test.testactivedirectory.application.resume.dto.IdentityFilterRequest;
import com.test.testactivedirectory.infrastructure.persistence.entity.resumen.Identity;

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
    
    //  @PostMapping("guardar")
    //  public ResponseEntity<?> saveHojadevida(@Valid @RequestBody Identity requesIdentity, BindingResult result) {
    //      return this.processRequest(result, () -> ResponseEntity.ok(resumeService.registrarHojaDeVida(requesIdentity)));
    //  }

    @PostMapping("guardar")
    public ResponseEntity<?> saveHojadevida(@Valid @RequestBody Identity requestIdentity, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Errores en los datos del formulario.");
        }
    
        // Guardar los datos del formulario
        Identity savedIdentity = resumeService.registrarHojaDeVida(requestIdentity);
    
        // Llamar al método getByCargo del UserController
        ResponseEntity<?> validadoresResponse = userController.getByCargo("Validador");
    
        // Asegurarte de que la respuesta contiene datos válidos
        if (validadoresResponse.getBody() instanceof List) {
            List<Identity> validadores = (List<Identity>) validadoresResponse.getBody();
    
            if (!validadores.isEmpty()) {
                // Enviar correos a los validadores
                String subject = "Nuevo formulario completado";
                String body = String.format("Hola,\n\nSe ha completado un nuevo formulario por parte de %s.\n\nPor favor revisa los detalles.\n\nSaludos,\nEl equipo.",
                                            savedIdentity.getNombre());
    
                for (Identity validador : validadores) {
                    emailService.sendEmailAsync(validador.getCorreo(), subject, body);
                }
            } else {
                System.out.println("No se encontraron usuarios con el cargo 'Validador'.");
            }
    
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener usuarios con el cargo 'Validador'.");
        }
    
        return ResponseEntity.ok("Formulario guardado y notificaciones enviadas si es necesario.");
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
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el archivo: " + e.getMessage());
         }
     }
     
     // Método para guardar el archivo en la carpeta
    // @PostMapping("/guardar")
    // public ResponseEntity<?> saveHojadevida(@Valid @RequestPart("requesIdentity")
    // Identity requesIdentity, BindingResult result, @RequestPart(value = "file",
    // required = false) MultipartFile file) {

    // try{
    // if (file != null && !file.isEmpty()) {
    // // Guardar el archivo
    // String fileName = saveFile(file);
    // }

    // } catch (Exception e) {
    // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al
    // guardar el archivo.");
    // }

    // return this.processRequest(result, () ->
    // ResponseEntity.ok(resumeservice.registrarHojaDeVida(requesIdentity)));
    // }

    // // Método para guardar el archivo en la carpeta
    // private String saveFile(MultipartFile file) throws IOException {
    // // Define la ruta de la carpeta donde deseas guardar los archivos
    // String directoryPath = "C:/mi-carpeta-de-archivos/"; // Puedes modificar esta
    // ruta a la que prefieras

    // // Crea la carpeta si no existe
    // File directory = new File(directoryPath);
    // if (!directory.exists()) {
    // directory.mkdirs(); // Crea las carpetas necesarias
    // }

    // // Nombre del archivo (puedes usar el nombre original o generar uno nuevo)
    // String fileName = file.getOriginalFilename();

    // // Asegurarte de que no haya conflictos de nombre (opcional)
    // String filePath = directoryPath + fileName;

    // // Guardar el archivo en la carpeta
    // Path path = Paths.get(filePath);
    // Files.write(path, file.getBytes());

    // return fileName; // Puedes devolver el nombre del archivo para guardarlo en
    // la entidad
    // }

    @PostMapping("guardar")
    public ResponseEntity<?> saveHojadevida(@Valid @RequestBody Identity requesIdentity, BindingResult result) {
        return this.processRequest(result, () -> ResponseEntity.ok(resumeservice.registrarHojaDeVida(requesIdentity)));
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
    public ResponseEntity<?> getInboxWithFilter(@Valid @RequestBody IdentityFilterRequest filter, BindingResult result) {
        return this.processRequest(result, () -> ResponseEntity.ok(this.resumeservice.getResumWithFilter(filter)));
    }

    // @PostMapping("guardar")
    // public ResponseEntity<?> saveHojadevida(@Valid @RequestBody Identity
    // requesIdentity, BindingResult result) {
    // return this.processRequest(result, () ->
    // ResponseEntity.ok(resumeservice.registrarHojaDeVida(requesIdentity)));
    // }

    // Metodo obtener, falta arreglar para que funciona correctamente
    // @GetMapping("/obtener")
    // public ResponseEntity<?> getHojadevida(@RequestParam("id") Long id) {
    // try {
    // // Buscar la hoja de vida en la base de datos por ID
    // Identity identity = resumeservice.buscarHojaDeVida(id);
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
