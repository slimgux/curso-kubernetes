package com.suki.curso.cursos.infrastructure.repositories;

import com.suki.curso.cursos.infrastructure.entities.CursoUsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CursoUsuariosRepository extends JpaRepository<CursoUsuarioEntity, Long> {

    @Query("delete from CursoUsuarioEntity cu where cu.usuarioId = :usuarioId")
    @Modifying
    void deleteByUsuarioId(Long usuarioId);
}
