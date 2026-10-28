package br.com.senai.teste.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.senai.teste.model.Livro;
import br.com.senai.teste.service.LivroService;

@RestController
@RequestMapping("/livros")
public class LivroController {
    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping
    public ResponseEntity<Livro> cadastrar(@RequestBody Livro livro) {
        return ResponseEntity.status(HttpStatus.CREATED).body(livroService.cadastrar(livro));
    }

    @GetMapping
    public ResponseEntity<List<Livro>> listar() {
        return ResponseEntity.ok(livroService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarPorId(@PathVariable Integer id) {
        return livroService.buscarPorId(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizar(@PathVariable Integer id, @RequestBody Livro novosDados) {
        Optional<Livro> livro = livroService.atualizar(id, novosDados);
        return livro.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        return livroService.deletar(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
