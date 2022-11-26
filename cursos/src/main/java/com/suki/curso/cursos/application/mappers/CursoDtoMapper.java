package com.suki.curso.cursos.application.mappers;

import com.suki.curso.cursos.application.dtos.CursoDto;
import com.suki.curso.cursos.domain.models.Curso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CursoDtoMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "usuarios", target = "usuarios")
    CursoDto toDto(Curso curso);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "usuarios", target = "usuarios")
    Curso toDomain(CursoDto cursoDto);
}
