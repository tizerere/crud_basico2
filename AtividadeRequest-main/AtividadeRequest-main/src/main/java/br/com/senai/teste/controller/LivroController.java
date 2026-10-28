package br.com.senai.teste.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.senai.teste.model.Aluno;
import br.com.senai.teste.service.AlunoService;

@RestController
@RequestMapping("/livros")
public class LivroController {
    
    private final AlunoService livroService;

    public LivroController(AlunoService AlunoService) {
       this.livroService = AlunoService;
    }

    @PostMapping
    public ResponseEntity<Aluno> cadastrar(
             @RequestBody Aluno livro) {
        
        Aluno livroCadastrado = livroService.cadastrar(livro);
        
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(livroCadastrado);
        
        
        }
    
    @GetMapping
    public ResponseEntity<List<Aluno>> listar() {
        List<Aluno> livros = livroService.listar();

        return ResponseEntity.ok(livros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscarPorId(@PathVariable Integer id){
        Optional<Aluno> livroOptional = livroService.buscarPorId(id);
        
        if (livroOptional.isPresent()){
            return ResponseEntity.ok(livroOptional.get());

        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizar(
        @PathVariable Integer id,
        @RequestBody Aluno novosDados){
            Optional<Aluno> livroOptional = livroService.atualizar(id, novosDados);

            if (livroOptional.isPresent()){
                return ResponseEntity.ok(livroOptional.get());
            }
            return ResponseEntity.notFound().build();
        }
    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id ){
        boolean deletado = livroService.deletar(id);

        if (deletado){
            return ResponseEntity.noContent().build();

        }   
        return ResponseEntity.notFound().build();
    }
}
