package br.com.senai.teste.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import br.com.senai.teste.model.Emprestimo;

@Repository
public interface EmprestimoRepository
extends org.springframework.data.jpa.repository.JpaRepository<Emprestimo, Integer> {

    boolean existsByAlunoIdAndLivroIdAndDataDevolucaoIsNull(Integer alunoId, Integer livroId);
    
    List<Emprestimo> findByDataDevolucaoIsNull();
    

}
