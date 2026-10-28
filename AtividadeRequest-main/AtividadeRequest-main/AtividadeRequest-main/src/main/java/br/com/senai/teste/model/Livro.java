package br.com.senai.teste.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name = "livros")
public class Livro {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY) 
    private int id;
    
    private String titulo; 
    private String autor;
    private int ano_publicacao;
    

    public Livro() {
    }

    public Livro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }       

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getAno_publicacao() {
        return ano_publicacao;
    }
    public void setAno_publicacao(int ano_publicacao) {
        this.ano_publicacao = ano_publicacao;
    }
    
    
}
