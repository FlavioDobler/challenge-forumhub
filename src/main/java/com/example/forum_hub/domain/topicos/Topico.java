package com.example.forum_hub.domain.topicos;

import com.example.forum_hub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String mensagem;

    @Column(name = "data_criacao", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dataCriacao;

    private Short status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    private String curso;

    public Topico(DadosTopico dados,  Usuario autor) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.dataCriacao = LocalDateTime.now();
        this.status = 1;
        this.autor = autor;
        this.curso = dados.curso();
    }

    public Topico(DadosAtualizarTopico dados) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
    }


    public void excluir() {
        this.status = (short) 0;
    }

    public Topico atualizarTopico(Topico topico) {
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.autor = topico.getAutor();
        this.curso = topico.getCurso();
        return topico;
    }
}
