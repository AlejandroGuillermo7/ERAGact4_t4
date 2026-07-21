package com.enriquez.crudrapido.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message="Es obligatorio introducir el nombre de usuario.")
    private String userName;
    @NotBlank(message="Es obligatorio introducir una contraseña.")
    private String password;
}
