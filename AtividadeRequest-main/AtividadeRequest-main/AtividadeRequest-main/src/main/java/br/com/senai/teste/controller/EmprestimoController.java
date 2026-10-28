package br.com.senai.teste.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.senai.teste.model.Emprestimo;
import br.com.senai.teste.service.EmprestimoService;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @PostMapping
    public ResponseEntity<Emprestimo> cadastrar(
            @RequestBody Emprestimo emprestimo) {

        if (emprestimo.getAluno() == null ||
            emprestimo.getLivro() == null) {

            return ResponseEntity.badRequest().build();
        }

        Optional<Emprestimo> cadastrado =
                emprestimoService.cadastrar(
                        emprestimo.getAluno().getId(),
                        emprestimo.getLivro().getId()
                );

        return cadastrado
                .map(e -> ResponseEntity.status(201).body(e))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping
    public ResponseEntity<List<Emprestimo>> listar() {

        return ResponseEntity.ok(
                emprestimoService.listar()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emprestimo> buscarPorId(
            @PathVariable Integer id) {

        return emprestimoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/devolver")
    public ResponseEntity<Emprestimo> devolver(
            @PathVariable Integer id) {

        return emprestimoService.devolver(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Integer id) {

        if (emprestimoService.deletar(id)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/atrasados")
    public ResponseEntity<List<Emprestimo>> listarAtrasados() {

        return ResponseEntity.ok(
                emprestimoService.listarAtrasados()
        );
    }
}
