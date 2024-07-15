package com.example.forum_hub.domain.topicos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizarTopico(
                                   @NotBlank(message = "Titulo obrigatório")
                                    String titulo,
                                   @NotBlank(message = "Mensagem obrigatória")
                                    String mensagem
) {
}
