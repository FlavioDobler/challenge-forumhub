package com.example.forum_hub.controller;


import com.example.forum_hub.domain.topicos.*;
import com.example.forum_hub.domain.usuario.Usuario;
import com.example.forum_hub.service.TopicoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;


import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private TopicoService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosTopico> cadastrarTopico(@Valid @RequestBody DadosTopico dados, UriComponentsBuilder builder,
                                                 @AuthenticationPrincipal Usuario usuario) {
        DadosTopico dadosTopico = service.registrar(dados, usuario);
        URI uri = builder.path("/topicos/{id}").buildAndExpand(dadosTopico.id()).toUri();
        return ResponseEntity.created(uri).body(dadosTopico);
    }

    @GetMapping
    public ResponseEntity<Page<DadosTopicoResponse>> listarTopicos(@PageableDefault(sort = {"dataCriacao"}) Pageable paginacao){
        var page = service.listarTopicos(paginacao).map(DadosTopicoResponse::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharTopico(@PathVariable Long id) {
        var topico = service.detalharTopico(id);
        return ResponseEntity.ok(new DadosTopicoResponse(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarTopico(@PathVariable Long id) {
        service.deletarTopico(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarTopico(@RequestBody @Valid DadosAtualizarTopico dados) {
        var topico = service.atualizarTopico(new Topico(dados));
        return ResponseEntity.ok(new DadosTopicoResponse(topico));
    }


}
