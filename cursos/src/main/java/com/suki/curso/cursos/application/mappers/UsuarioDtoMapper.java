package com.suki.curso.cursos.application.mappers;

import com.suki.curso.cursos.application.dtos.UsuarioDto;
import com.suki.curso.cursos.domain.models.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioDtoMapper {

    @Mapping(source = "id", target = "id")
    UsuarioDto toDto(Usuario usuario);

    @Mapping(source = "id", target = "id")
    Usuario toDomain(UsuarioDto usuarioDto);
}
