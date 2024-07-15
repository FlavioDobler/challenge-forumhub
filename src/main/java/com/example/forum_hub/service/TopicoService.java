package com.example.forum_hub.service;


import com.example.forum_hub.domain.topicos.DadosTopico;
import com.example.forum_hub.domain.topicos.Topico;
import com.example.forum_hub.domain.topicos.TopicoRepository;
import com.example.forum_hub.domain.usuario.Usuario;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository repository;

    private Short ATIVO = (short) 1;
    public DadosTopico registrar(DadosTopico dados, Usuario usuario) {
        Optional<Topico> optionalTopico = repository.findByTituloAndMensagem(dados.titulo(), dados.mensagem());

        if(optionalTopico.isPresent()) {
            throw new RuntimeException("Tópico com título e mensagem ja existentes!");
        }
        Topico topico = repository.save(new Topico(dados, usuario));
        return new DadosTopico(topico);
    }


    public Page<Topico> listarTopicos(Pageable paginacao) {
        return repository.findAllByStatus(ATIVO, paginacao);
    }


    public void deletarTopico(Long id) {
        var topico = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, "O tópico com o id :" + id + "nao foi encontrado para ser deletado"));
        topico.excluir();
    }

    public Topico detalharTopico(Long id) {
        var topico = repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, "O tópico com o id :" + id + "nao foi encontrado"));
        return topico;
    }

    public Topico atualizarTopico(Topico topico) {
        var topicoToAtt =  repository.findById(topico.getId())
                .orElseThrow(() -> new ObjectNotFoundException(topico.getId(), "O tópico com o id :" + topico.getId() + "nao foi encontrado para ser atualizado"));
       return topicoToAtt.atualizarTopico(topico);
    }


}
