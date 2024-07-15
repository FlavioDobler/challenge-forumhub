package com.example.forum_hub.domain.topicos;

import com.example.forum_hub.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record DadosTopicoResponse(Long id,
                                  String titulo,
                                  String mensagem,
                                  LocalDateTime dataCriacao,
                                  Short status,
                                  String autor,
                                  String curso) {

    public DadosTopicoResponse(Topico topico) {
        this(topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getStatus(),
                topico.getAutor().getUsername(),
                topico.getCurso()
        );
    }


}
