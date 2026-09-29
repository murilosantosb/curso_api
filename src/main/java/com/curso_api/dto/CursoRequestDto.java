package com.curso_api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CursoRequestDto(

        @NotBlank(message = "Nome do curso é obrigatório")
        String nome,

        @NotBlank(message = "A descrição é obrigatória")
        String descricao,

        @NotNull(message = "Carga horária é obrigatoria")
        @Min(value= 1, message = "Carga horária minima é de 1 hora")
        Integer cargaHoraria,

        @NotNull(message = "Instrutor é obrigatorio")
        Long instrutorId
) {
}
