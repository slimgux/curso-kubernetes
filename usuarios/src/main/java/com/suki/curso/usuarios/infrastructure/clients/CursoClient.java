package com.suki.curso.usuarios.infrastructure.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cursos") //es el nombre del servicio que se va a consumir
public interface CursoClient {
    @DeleteMapping("/eliminar-usuario-cursos/{usuarioId}")
    void eliminarUsuarioDeCursos(@PathVariable Long usuarioId);
}
