package com.enriquez.crudrapido.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class RegisterRequest {
    @NotBlank(message= "El nombre se usuario es obligatorio.")
    private String userName;
    @NotBlank(message = "El correo electronico es obligatorio.")
    @Email(message="Debe ser un correo Válido")
    private String email;
    @NotBlank(message="La contraseña es obligatoria.")
    @Size(min=8, message="La contraseña debe cumplir con al menos 6 caracteres")
    private String password;
}
