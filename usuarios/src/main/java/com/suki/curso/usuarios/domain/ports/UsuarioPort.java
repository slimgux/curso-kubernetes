package com.suki.curso.usuarios.domain.ports;

import com.suki.curso.usuarios.domain.models.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioPort {
    Optional<Usuario> save(Usuario usuarioDto);

    Optional<Usuario> update(Usuario usuarioDto, Long id);

    List<Usuario> findAll();

    Optional<Usuario> findById(Long id);

    Optional<String> delete(Long id);

    List<Usuario> findAllByIds(List<Long> ids);
}
