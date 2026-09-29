package com.curso_api.dto;

public record CursoResponseDto(
        Long id,
        String nome,
        String descricao,
        Integer cargaHoraria,
        String instrutorNome
) {
}
