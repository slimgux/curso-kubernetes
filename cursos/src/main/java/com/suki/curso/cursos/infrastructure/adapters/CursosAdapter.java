package com.suki.curso.cursos.infrastructure.adapters;

import com.suki.curso.cursos.domain.models.Curso;
import com.suki.curso.cursos.domain.models.Usuario;
import com.suki.curso.cursos.domain.ports.CursosPort;
import com.suki.curso.cursos.infrastructure.clients.UsuarioClient;
import com.suki.curso.cursos.infrastructure.entities.CursoUsuarioEntity;
import com.suki.curso.cursos.infrastructure.mappers.CursosEntityMapper;
import com.suki.curso.cursos.infrastructure.repositories.CursoUsuariosRepository;
import com.suki.curso.cursos.infrastructure.repositories.CursosRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CursosAdapter implements CursosPort {

    private final CursosRepository cursosRepository;
    private final CursosEntityMapper cursosMapper;
    private final UsuarioClient usuarioClient;
    private final CursoUsuariosRepository cursoUsuariosRepository;

    public CursosAdapter(CursosRepository cursosRepository, CursosEntityMapper cursosMapper, UsuarioClient usuarioClient, CursoUsuariosRepository cursoUsuariosRepository) {
        this.cursosRepository = cursosRepository;
        this.cursosMapper = cursosMapper;
        this.usuarioClient = usuarioClient;
        this.cursoUsuariosRepository = cursoUsuariosRepository;
    }

    @Override
    @Transactional
    public Curso save(Curso curso) {
        var cursoCreado = cursosRepository.save(cursosMapper.toEntity(curso));
        return cursosMapper.toDomain(cursoCreado);
    }

    @Override
    @Transactional
    public Optional<Curso> update(Curso curso, Long id) {
        var cursoActual = cursosRepository.findById(id);
        if (cursoActual.isPresent()) {
            var cursoBBDD = cursoActual.get();
            cursoBBDD.setNombre(curso.nombre());
            cursoBBDD = cursosRepository.save(cursoBBDD);
            return Optional.of(cursosMapper.toDomain(cursoBBDD));
        }
        return Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Curso> findAll() {
        return cursosRepository.findAll().stream().map(cursosMapper::toDomain).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> findById(Long id) {
        var curso = cursosRepository.findById(id);
        if (curso.isPresent()) {
            var cursoBBDD = curso.get();
            var cursoDomain = cursosMapper.toDomain(cursoBBDD);
            var usuarios = cursoBBDD.getCursoUsuarios().isEmpty() ? new ArrayList<Usuario>() :
                    usuarioClient.findAllByIds(cursoBBDD.getCursoUsuarios().stream().map(CursoUsuarioEntity::getUsuarioId).toList());
            cursoDomain.usuarios().addAll(usuarios);
            return Optional.of(cursoDomain);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<String> delete(Long id) {
        var cursoActual = cursosRepository.findById(id);
        if (cursoActual.isPresent()) {
            cursosRepository.delete(cursoActual.get());
            return Optional.of("Curso eliminado");
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Usuario> asignarUsuario(Usuario usuario, Long cursoId) {
        var cursoActual = cursosRepository.findById(cursoId);
        if (cursoActual.isPresent()) {
            var usarioDetalle = usuarioClient.findById(usuario.id());
            if (Objects.isNull(usarioDetalle)) {
                return Optional.empty();
            }
            var cursoBBDD = cursoActual.get();
            var cursoUsuario = new CursoUsuarioEntity();
            cursoUsuario.setUsuarioId(usarioDetalle.id());
            cursoBBDD.addCursoUsuario(cursoUsuario);
            cursosRepository.save(cursoBBDD);
            return Optional.of(usarioDetalle);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Usuario> crearUsuario(Usuario usuario, Long cursoId) {
        var cursoActual = cursosRepository.findById(cursoId);
        if (cursoActual.isPresent()) {
            var usuarioSave = usuarioClient.save(usuario);
            if (Objects.isNull(usuarioSave)) {
                return Optional.empty();
            }
            var cursoBBDD = cursoActual.get();
            var cursoUsuario = new CursoUsuarioEntity();
            cursoUsuario.setUsuarioId(usuarioSave.id());
            cursoBBDD.addCursoUsuario(cursoUsuario);
            cursosRepository.save(cursoBBDD);
            return Optional.of(usuarioSave);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Usuario> eliminarUsuario(Usuario usuario, Long cursoId) {
        var cursoActual = cursosRepository.findById(cursoId);
        if (cursoActual.isPresent()) {
            var usarioDetalle = usuarioClient.findById(usuario.id());
            if (Objects.isNull(usarioDetalle)) {
                return Optional.empty();
            }
            var cursoBBDD = cursoActual.get();
            var cursoUsuario = new CursoUsuarioEntity();
            cursoUsuario.setUsuarioId(usarioDetalle.id());
            cursoBBDD.removeCursoUsuario(cursoUsuario);
            cursosRepository.save(cursoBBDD);
            return Optional.of(usarioDetalle);
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<String> eliminarUsuarioDeCursos(Long usuarioId) {
        cursoUsuariosRepository.deleteByUsuarioId(usuarioId);
        return Optional.of("Usuario eliminado de cursos");
    }
}
