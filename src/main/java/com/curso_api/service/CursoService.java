package com.curso_api.service;

import com.curso_api.dto.CursoRequestDto;
import com.curso_api.dto.CursoResponseDto;
import com.curso_api.entity.Curso;
import com.curso_api.entity.Instrutor;
import com.curso_api.mapper.CursoMapper;
import com.curso_api.repository.CursoRepository;
import com.curso_api.repository.InstrutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    private  final InstrutorRepository instrutorRepository;

    private final CursoMapper cursoMapper;

    public CursoService(CursoRepository cursoRepository, InstrutorRepository instrutorRepository, CursoMapper cursoMapper) {
        this.cursoRepository = cursoRepository;
        this.instrutorRepository = instrutorRepository;
        this.cursoMapper = cursoMapper;
    }

    public List<CursoResponseDto> listarTodos(){
        return cursoRepository.findAll()
                .stream()
                .map(c -> cursoMapper.toResponse(c))
                .toList();
    }

    public CursoResponseDto buscarPorId(Long id){
        return cursoMapper.toResponse(cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado")));
    }

    public void deletar(Long id){
        buscarPorId(id);
        cursoRepository.deleteById(id);
    }

    public CursoResponseDto criar(CursoRequestDto cursoRequestDto){
        Curso curso = cursoMapper.toEntity(cursoRequestDto);

        //busca o instrutor no banco
        Instrutor instrutor = instrutorRepository.findById(cursoRequestDto.instrutorId())
                        .orElseThrow(() -> new RuntimeException("Instrutor não encontrado"));

        //usa o instrutor que já existe
        curso.setInstrutor(instrutor);

        return cursoMapper.toResponse(cursoRepository.save(curso)) ;
    }

    public CursoResponseDto atualizar(Long cursoId, CursoRequestDto cursoRequestDto){
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("instrutor não encontrado"));

        //busca o instrutor no banco
        Instrutor instrutor = instrutorRepository.findById(cursoRequestDto.instrutorId())
                .orElseThrow(() -> new RuntimeException("Instrutor não encontrado"));

        cursoMapper.update(cursoRequestDto, curso);

        //usa o instrutor que já existe
        curso.setInstrutor(instrutor);

        return cursoMapper.toResponse( cursoRepository.save(curso));
    }

}
