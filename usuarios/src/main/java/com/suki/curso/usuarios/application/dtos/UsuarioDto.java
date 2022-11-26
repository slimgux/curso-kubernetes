package com.suki.curso.usuarios.application.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDto(Long id, @NotBlank String nombre, @NotBlank @Email String email, @NotBlank String password) {

}
