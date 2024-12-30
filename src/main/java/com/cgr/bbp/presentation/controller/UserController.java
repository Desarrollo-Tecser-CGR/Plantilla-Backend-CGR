package com.cgr.bbp.presentation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgr.bbp.application.user.dto.UserWithRolesRequestDto;
import com.cgr.bbp.application.user.usecase.IUserSynchronizerUseCase;
import com.cgr.bbp.application.user.usecase.IUserUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
public class UserController extends AbstractController {

    private IUserUseCase userService;

    private IUserSynchronizerUseCase synchronizerUsers;

    public UserController(IUserUseCase userService, IUserSynchronizerUseCase synchronizerUsers) {
        this.userService = userService;
        this.synchronizerUsers = synchronizerUsers;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return requestResponse(this.userService.findAll(), "usuarios del sistema", HttpStatus.OK, true);
    }

    @PostMapping
    public ResponseEntity<?> assignRole(@Valid @RequestBody UserWithRolesRequestDto rolesRequestDto,
            BindingResult result) {
        return requestResponse(result, () -> this.userService.assignRolesToUser(rolesRequestDto), "roles actualizados",
                HttpStatus.CREATED, true);
    }

    @GetMapping("/synchronize")
    public ResponseEntity<?> synchronizeAD() {
        return requestResponse(this.synchronizerUsers.synchronizeUsers(),
                "sistema sincronizado exitosamente con el Directorio Activo", HttpStatus.OK, true);
    }

    @GetMapping("/cargo/{cargo}")
    public ResponseEntity<?> getByCargo(@PathVariable String cargo) {
        return ResponseEntity.ok(this.userService.findByCargo(cargo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(this.userService.findById(id));
    }

}
