package com.suki.curso.cursos.infrastructure.clients;

import com.suki.curso.cursos.domain.models.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "usuarios", url = "http://ec2-54-89-250-222.compute-1.amazonaws.com:8001/usuarios")
public interface UsuarioClient {

    @PostMapping("/save")
    Usuario save(@RequestBody Usuario usuario);

    @PutMapping("/save/{id}")
    Usuario update(@RequestBody Usuario usuario, @PathVariable Long id);

    @GetMapping("/all")
    List<Usuario> findAll();

    @GetMapping("/find/{id}")
    Usuario findById(@PathVariable Long id);

    @DeleteMapping("/delete/{id}")
    void delete(@PathVariable Long id);

    @GetMapping("/allByIds")
    List<Usuario> findAllByIds(@RequestBody List<Long> ids);
}
