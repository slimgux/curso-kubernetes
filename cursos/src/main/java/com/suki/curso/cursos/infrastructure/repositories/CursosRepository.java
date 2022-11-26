package com.suki.curso.cursos.infrastructure.repositories;

import com.suki.curso.cursos.infrastructure.entities.CursoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursosRepository extends JpaRepository<CursoEntity, Long> {

}
