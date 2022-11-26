package com.suki.curso.cursos.domain.services.impl;

import com.suki.curso.cursos.domain.models.Curso;
import com.suki.curso.cursos.domain.models.Usuario;
import com.suki.curso.cursos.domain.ports.CursosPort;
import com.suki.curso.cursos.domain.services.CursosService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursosServiceImpl implements CursosService {

    private final CursosPort cursosPort;

    public CursosServiceImpl(CursosPort cursosPort) {
        this.cursosPort = cursosPort;
    }

    @Override
    public Curso save(Curso curso) {
        return cursosPort.save(curso);
    }

    @Override
    public Optional<Curso> update(Curso curso, Long id) {
        return cursosPort.update(curso, id);
    }

    @Override
    public List<Curso> findAll() {
        return cursosPort.findAll();
    }

    @Override
    public Optional<Curso> findById(Long id) {
        return cursosPort.findById(id);
    }

    @Override
    public Optional<String> delete(Long id) {
        return cursosPort.delete(id);
    }

    @Override
    public Optional<Usuario> asignarUsuario(Usuario usuario, Long cursoId) {
        return cursosPort.asignarUsuario(usuario, cursoId);
    }

    @Override
    public Optional<Usuario> crearUsuario(Usuario usuario, Long cursoId) {
        return cursosPort.crearUsuario(usuario, cursoId);
    }

    @Override
    public Optional<Usuario> eliminarUsuario(Usuario usuario, Long cursoId) {
        return cursosPort.eliminarUsuario(usuario, cursoId);
    }

    @Override
    public Optional<String> eliminarUsuarioDeCursos(Long usuarioId) {
        return cursosPort.eliminarUsuarioDeCursos(usuarioId);
    }
}
