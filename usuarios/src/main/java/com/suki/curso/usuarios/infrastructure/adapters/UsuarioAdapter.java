package com.suki.curso.usuarios.infrastructure.adapters;

import com.suki.curso.usuarios.domain.models.Usuario;
import com.suki.curso.usuarios.domain.ports.UsuarioPort;
import com.suki.curso.usuarios.infrastructure.clients.CursoClient;
import com.suki.curso.usuarios.infrastructure.mappers.UsuarioEntityMapper;
import com.suki.curso.usuarios.infrastructure.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioAdapter implements UsuarioPort {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioEntityMapper usuarioMapper;
    private final CursoClient cursoClient;

    public UsuarioAdapter(UsuarioRepository usuarioRepository, UsuarioEntityMapper usuarioMapper, CursoClient cursoClient) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.cursoClient = cursoClient;
    }

    @Override
    @Transactional
    public Optional<Usuario> save(Usuario usuario) {
        var exists = usuarioRepository.findByEmail(usuario.email());
        if (exists.isPresent()) {
            return Optional.empty();
        }
        var usuarioEntitySaved = usuarioRepository.save(usuarioMapper.toUsuarioEntity(usuario));
        return Optional.of(usuarioMapper.toUsuario(usuarioEntitySaved));
    }

    @Override
    @Transactional
    public Optional<Usuario> update(Usuario usuario, Long id) {
        var usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isEmpty()) {
            return Optional.empty();
        }
        var exists = usuarioRepository.findByEmail(usuario.email());
        if (exists.isPresent() && !exists.get().getId().equals(id)) {
            return Optional.empty();
        }
        var usuarioEntity = usuarioOptional.get();
        usuarioEntity.setNombre(usuario.nombre());
        usuarioEntity.setEmail(usuario.email());
        usuarioEntity.setPassword(usuario.password());
        return Optional.of(usuarioMapper.toUsuario(usuarioRepository.save(usuarioEntity)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return usuarioRepository.findAll().stream().map(usuarioMapper::toUsuario).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id).map(usuarioMapper::toUsuario);
    }

    @Override
    @Transactional
    public Optional<String> delete(Long id) {
        var usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isEmpty()) {
            return Optional.empty();
        }
        usuarioRepository.deleteById(id);
        cursoClient.eliminarUsuarioDeCursos(id);
        return Optional.of("Usuario eliminado");
    }

    @Override
    public List<Usuario> findAllByIds(List<Long> ids) {
        return usuarioRepository.findAllById(ids).stream().map(usuarioMapper::toUsuario).toList();
    }
}
