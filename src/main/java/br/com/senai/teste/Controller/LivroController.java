package br.com.senai.teste.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.teste.model.Livro;
import br.com.senai.teste.service.LivroService;

@RestController 
@RequestMapping("/livros")
public class LivroController {
    
    @Autowired 
    private LivroService livroService;

    @PostMapping 
    public ResponseEntity<Livro> cadastrar(@RequestBody Livro livro) {
        Livro novoLivro = livroService.cadastrar(livro);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoLivro);
    }

    @GetMapping 
    public ResponseEntity<List<Livro>> listar() {
        List<Livro> livros = livroService.listar();
        return ResponseEntity.ok(livros);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Livro> buscarPorId(@PathVariable Integer id) {
        Livro livro = livroService.buscarPorId(id);
        if (livro != null) {
            return ResponseEntity.ok(livro);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Livro> atualizar(@PathVariable Integer id, @RequestBody Livro livro) {
        Livro livroAtualizado = livroService.atualizar(id, livro);
        if (livroAtualizado != null) {
            return ResponseEntity.ok(livroAtualizado);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        Livro livro = livroService.buscarPorId(id);
        if (livro != null) {
            livroService.excluir(id);
            return  ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
