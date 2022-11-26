package com.suki.curso.cursos.application.dtos;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record CursoDto(Long id, @NotBlank String nombre, List<UsuarioDto> usuarios) {

}
