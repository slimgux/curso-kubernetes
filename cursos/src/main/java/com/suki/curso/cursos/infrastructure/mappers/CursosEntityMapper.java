package com.suki.curso.cursos.infrastructure.mappers;

import com.suki.curso.cursos.domain.models.Curso;
import com.suki.curso.cursos.infrastructure.entities.CursoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CursosEntityMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "usuarios", target = "usuarios")
    CursoEntity toEntity(Curso curso);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "usuarios", target = "usuarios")
    Curso toDomain(CursoEntity cursoEntity);
}
