package com.example.forum_hub.domain.topicos;

import jakarta.validation.constraints.NotBlank;

public record DadosTopico(Long id,
                          @NotBlank(message = "Informe o título!")
                           String titulo,
                          @NotBlank(message = "Mensagem em branco!")
                           String mensagem,
                          @NotBlank(message = "Autor obrigatório")
                           String autor,
                          @NotBlank(message = "Curso obrigatório")
                           String curso) {

    public DadosTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), String.valueOf(topico.getDataCriacao()), topico.getCurso());
    }
}
