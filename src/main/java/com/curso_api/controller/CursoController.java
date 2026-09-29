package com.curso_api.controller;

import com.curso_api.dto.CursoRequestDto;
import com.curso_api.dto.CursoResponseDto;
import com.curso_api.entity.Curso;
import com.curso_api.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/curso")
public class CursoController {
    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @PostMapping
    public CursoResponseDto criar (@Valid @RequestBody CursoRequestDto dtoRequest){
        return cursoService.criar(dtoRequest);
    }

    @GetMapping
    public List<CursoResponseDto> listarTodos(){
        return cursoService.listarTodos();
    }

    @GetMapping("/{id}")
    public CursoResponseDto buscarPorId(@PathVariable Long id){
        return cursoService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        cursoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public CursoResponseDto atualizar(@Valid @PathVariable Long id, @RequestBody CursoRequestDto cursoRequestDto){
        return cursoService.atualizar(id, cursoRequestDto);
    }

}
