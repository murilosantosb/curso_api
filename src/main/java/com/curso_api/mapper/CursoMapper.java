package com.curso_api.mapper;

import com.curso_api.dto.CursoRequestDto;
import com.curso_api.dto.CursoResponseDto;
import com.curso_api.entity.Curso;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CursoMapper {

    //Ele mapeia automaticamente campos com o mesmo nome
    //Em alguns casos que os campos possuem nomes diferentes precisa explicar como será feito
    @Mapping(source = "instrutor.nome", target = "instrutorNome")
    CursoResponseDto toResponse(Curso curso);

    //Não mapeia o id pq estamos criando um novo curso
    @Mapping(target = "id", ignore = true)
    //O instrutorId não pode ser convertido direto para uma classe Instrutor
    //Isso precisa ser feito pelo service
    @Mapping(target = "instrutor", ignore = true)
    Curso toEntity(CursoRequestDto cursoRequestDto);

    //O id nunca deve ser alterado uma vez que ja foi salvo
    //o instrutor id não pode ser direto para uma classe instrutor
    @Mapping(target= "id", ignore = true)
    void update(CursoRequestDto dto, @MappingTarget Curso curso);


}
