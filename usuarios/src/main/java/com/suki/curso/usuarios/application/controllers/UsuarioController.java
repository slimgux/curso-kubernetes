package com.suki.curso.usuarios.application.controllers;

import com.suki.curso.usuarios.application.dtos.UsuarioDto;
import com.suki.curso.usuarios.application.mappers.UsuarioDtoMapper;
import com.suki.curso.usuarios.domain.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final Logger logger = Logger.getLogger(UsuarioController.class.getName());
    private final UsuarioService usuarioService;
    private final UsuarioDtoMapper usuarioDtoMapper;

    private final ApplicationContext applicationContext;

    public UsuarioController(UsuarioService usuarioService, UsuarioDtoMapper usuarioDtoMapper, ApplicationContext applicationContext) {
        this.usuarioService = usuarioService;
        this.usuarioDtoMapper = usuarioDtoMapper;
        this.applicationContext = applicationContext;
    }

    @GetMapping("cracks")
    public void getCracks() {
        logger.info("getCracks");
        ((ConfigurableApplicationContext)applicationContext).close();
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody UsuarioDto usuarioDto, BindingResult result) {
        logger.info("save()");
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        var usuario = usuarioService.save(usuarioDtoMapper.toDomain(usuarioDto));
        if (usuario.isPresent()) {
            return new ResponseEntity<>(usuarioDtoMapper.toDto(usuario.get()), HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo guardar el usuario, ya existe el email");
    }

    @PutMapping("/save/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody UsuarioDto usuarioDto, BindingResult result, @PathVariable Long id) {
        logger.info("update()");
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        var usuarioOptional = usuarioService.update(usuarioDtoMapper.toDomain(usuarioDto), id);
        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDtoMapper.toDto(usuarioOptional.get()));
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        logger.info("findAll()");
        return ResponseEntity.ok(Collections.singletonMap("users", usuarioService.findAll().stream().map(usuarioDtoMapper::toDto).toList()));
    }



    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        logger.info("findById()");
        var usuario = usuarioService.findById(id);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuarioDtoMapper.toDto(usuario.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        logger.info("delete()");
        var retorno = usuarioService.delete(id);
        if (retorno.isPresent()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/allByIds")
    public ResponseEntity<?> findAllByIds(@RequestBody List<Long> ids) {
        logger.info("findAllByIds()");
        return ResponseEntity.ok(usuarioService.findAllByIds(ids).stream().map(usuarioDtoMapper::toDto).toList());
    }
}
