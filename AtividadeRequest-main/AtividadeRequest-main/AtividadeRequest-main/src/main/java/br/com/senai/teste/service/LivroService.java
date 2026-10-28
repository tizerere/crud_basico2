package br.com.senai.teste.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import br.com.senai.teste.model.Livro;
import br.com.senai.teste.repository.LivroRepository;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public Livro cadastrar(Livro livro) {
        return livroRepository.save(livro);
    }

    public List<Livro> listar() {
        return livroRepository.findAll();
    }

    public Optional<Livro> buscarPorId(Integer id) {
        return livroRepository.findById(id);
    }

    public Optional<Livro> atualizar(Integer id, Livro novosDados) {
        if (livroRepository.existsById(id)) {
            novosDados.setId(id);
            return Optional.of(livroRepository.save(novosDados));
        }
        return Optional.empty();
    }

    public boolean deletar(Integer id) {
        if (livroRepository.existsById(id)) {
            livroRepository.deleteById(id);
            return true;
        }
        return false;
    }
}