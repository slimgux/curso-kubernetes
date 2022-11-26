package com.suki.curso.cursos.domain.models;

import java.util.List;

public record Curso(Long id, String nombre, List<Usuario> usuarios) {

}
