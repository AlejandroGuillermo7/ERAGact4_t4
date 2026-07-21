package com.enriquez.crudrapido.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private Long studentId;
    @NotBlank(message="El nombre no puede estar vacio.")
    private String firstName;
    @NotBlank(message="El apellido no puede estar vacio.")
    private String lastName;
    @NotBlank(message="El email es obligatorio")
    @Email(message="El formato el email no es valido.")
    private String email;
}
