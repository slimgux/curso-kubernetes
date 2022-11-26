package com.suki.curso.usuarios.domain.services.impl;

import com.suki.curso.usuarios.domain.models.Usuario;
import com.suki.curso.usuarios.domain.ports.UsuarioPort;
import com.suki.curso.usuarios.domain.services.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioPort usuarioPort;

    public UsuarioServiceImpl(UsuarioPort usuarioPort) {
        this.usuarioPort = usuarioPort;
    }

    @Override
    public Optional<Usuario> save(Usuario usuarioDto) {
        return usuarioPort.save(usuarioDto);
    }

    @Override
    public Optional<Usuario> update(Usuario usuario, Long id) {
        return usuarioPort.update(usuario, id);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioPort.findAll();
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return usuarioPort.findById(id);
    }

    @Override
    public Optional<String> delete(Long id) {
        return usuarioPort.delete(id);
    }

    @Override
    public List<Usuario> findAllByIds(List<Long> ids) {
        return usuarioPort.findAllByIds(ids);
    }
}
