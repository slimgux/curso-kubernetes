package com.suki.curso.cursos.application.controllers;

import com.suki.curso.cursos.application.dtos.CursoDto;
import com.suki.curso.cursos.application.dtos.UsuarioDto;
import com.suki.curso.cursos.application.mappers.CursoDtoMapper;
import com.suki.curso.cursos.application.mappers.UsuarioDtoMapper;
import com.suki.curso.cursos.domain.services.CursosService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cursos")
public class CursosController {
    private final CursosService cursoService;
    private final CursoDtoMapper cursoDtoMapper;
    private final UsuarioDtoMapper usuarioDtoMapper;

    public CursosController(CursosService cursoService, CursoDtoMapper cursoDtoMapper, UsuarioDtoMapper usuarioDtoMapper) {
        this.cursoService = cursoService;
        this.cursoDtoMapper = cursoDtoMapper;
        this.usuarioDtoMapper = usuarioDtoMapper;
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody CursoDto cursoDto, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        var curso = cursoService.save(cursoDtoMapper.toDomain(cursoDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoDtoMapper.toDto(curso));
    }

    @PutMapping("/save/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody CursoDto cursoDto, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        var cursoOptional = cursoService.update(cursoDtoMapper.toDomain(cursoDto), id);
        if (cursoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoDtoMapper.toDto(cursoOptional.get()));
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(cursoService.findAll().stream().map(cursoDtoMapper::toDto).toList());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        var curso = cursoService.findById(id);
        if (curso.isPresent()) {
            return ResponseEntity.ok(cursoDtoMapper.toDto(curso.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        var retorno = cursoService.delete(id);
        if (retorno.isPresent()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/asignar-usuario/{cursoId}")
    public ResponseEntity<?> asignarUsuario(@Valid @RequestBody UsuarioDto usuarioDto, BindingResult result, @PathVariable Long cursoId) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        var usuarioOptional = cursoService.asignarUsuario(usuarioDtoMapper.toDomain(usuarioDto), cursoId);
        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDtoMapper.toDto(usuarioOptional.get()));
    }

    @PostMapping("/crear-usuario/{cursoId}")
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody UsuarioDto usuarioDto, BindingResult result, @PathVariable Long cursoId) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        var usuarioOptional = cursoService.crearUsuario(usuarioDtoMapper.toDomain(usuarioDto), cursoId);
        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDtoMapper.toDto(usuarioOptional.get()));
    }

    @DeleteMapping("/eliminar-usuario/{cursoId}")
    public ResponseEntity<?> eliminarUsuario(@Valid @RequestBody UsuarioDto usuarioDto, BindingResult result, @PathVariable Long cursoId) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        var usuarioOptional = cursoService.eliminarUsuario(usuarioDtoMapper.toDomain(usuarioDto), cursoId);
        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDtoMapper.toDto(usuarioOptional.get()));
    }

    @DeleteMapping("/eliminar-usuario-cursos/{usuarioId}")
    public ResponseEntity<?> eliminarUsuarioDeCursos(@PathVariable Long usuarioId) {
        var usuarioOptional = cursoService.eliminarUsuarioDeCursos(usuarioId);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario eliminado de todos los cursos");
    }
}
