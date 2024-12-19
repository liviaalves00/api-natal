package com.amigo_oculto.api.interface_adapters.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amigo_oculto.api.Aplicattion.use_cases.implementation.GerenciarSorteios;
import com.amigo_oculto.api.entities.Sorteio;

@RestController
@RequestMapping("/api/sorteios")
public class SorteioController {
     @Autowired
    private final GerenciarSorteios gerenciarSorteios;

    public SorteioController(GerenciarSorteios gerenciarSorteios) {
        this.gerenciarSorteios = gerenciarSorteios;
    }

    @PostMapping
    public ResponseEntity<Sorteio> criarSorteio(@RequestBody Sorteio sorteio) {
        return ResponseEntity.ok(gerenciarSorteios.criar(sorteio));
    }

    @GetMapping
    public ResponseEntity<List<Sorteio>> listarSorteios() {
        return ResponseEntity.ok(gerenciarSorteios.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sorteio> buscarPorId(@PathVariable Long id) {
        return gerenciarSorteios.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sorteio> atualizarSorteio(@PathVariable Long id, @RequestBody Sorteio sorteio) {
        return ResponseEntity.ok(gerenciarSorteios.atualizar(id, sorteio));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSorteio(@PathVariable Long id) {
        gerenciarSorteios.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
