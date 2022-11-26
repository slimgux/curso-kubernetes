package com.suki.curso.usuarios.application.mappers;

import com.suki.curso.usuarios.application.dtos.UsuarioDto;
import com.suki.curso.usuarios.domain.models.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioDtoMapper {

    @Mapping(source = "id", target = "id")
    UsuarioDto toDto(Usuario usuario);

    @Mapping(source = "id", target = "id")
    Usuario toDomain(UsuarioDto usuarioDto);

}
