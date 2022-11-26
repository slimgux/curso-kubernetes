package com.suki.curso.cursos.domain.ports;

import com.suki.curso.cursos.domain.models.Curso;
import com.suki.curso.cursos.domain.models.Usuario;

import java.util.List;
import java.util.Optional;

public interface CursosPort {
    Curso save(Curso curso);

    Optional<Curso> update(Curso curso, Long id);

    List<Curso> findAll();

    Optional<Curso> findById(Long id);

    Optional<String> delete(Long id);

    Optional<Usuario> asignarUsuario(Usuario usuario, Long cursoId);

    Optional<Usuario> crearUsuario(Usuario usuario, Long cursoId);

    Optional<Usuario> eliminarUsuario(Usuario usuario, Long cursoId);

    Optional<String> eliminarUsuarioDeCursos(Long usuarioId);
}
