package com.suki.curso.usuarios.infrastructure.mappers;

import com.suki.curso.usuarios.domain.models.Usuario;
import com.suki.curso.usuarios.infrastructure.entities.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioEntityMapper {

    @Mapping(source = "id", target = "id")
    Usuario toUsuario(UsuarioEntity usuarioEntity);

    @Mapping(source = "id", target = "id")
    UsuarioEntity toUsuarioEntity(Usuario usuario);

}
