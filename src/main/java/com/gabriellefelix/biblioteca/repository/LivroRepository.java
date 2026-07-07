package com.gabriellefelix.biblioteca.repository;

import com.gabriellefelix.biblioteca.model.Livro;
import com.gabriellefelix.biblioteca.model.StatusLivro;

import java.util.ArrayList;
import java.util.List;

public class LivroRepository {

    private final List<Livro> livros;

    public LivroRepository() {
        this.livros = new ArrayList<>();
    }

    public void cadastrar(Livro livro) {
        if (buscarPorIsbn(livro.getIsbn()) != null) {
            throw new IllegalStateException("Já existe um livro cadastrado com esse ISBN.");
        }

        this.livros.add(livro);
    }

    public List<Livro> listarTodos() {
        return new ArrayList<>(this.livros);
    }

    public Livro buscarPorIsbn(String isbn) {
        for (Livro livro : this.livros) {
            if (livro.getIsbn().equals(isbn)) {
                return livro;
            }
        }
        return null;
    }

    public List<Livro> buscarPorTitulo(String titulo) {
        List<Livro> resultado = new ArrayList<>();

        for (Livro livro : this.livros) {
            if (livro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                resultado.add(livro);
            }
        }

        return resultado;
    }

    public List<Livro> buscarPorAutor(String autor) {
        List<Livro> resultado = new ArrayList<>();

        for (Livro livro : this.livros) {
            if (livro.getAutor().toLowerCase().contains(autor.toLowerCase())) {
                resultado.add(livro);
            }
        }

        return resultado;
    }

    public boolean remover(String isbn) {
        Livro livroEncontrado = buscarPorIsbn(isbn);

        if (livroEncontrado != null) {
            this.livros.remove(livroEncontrado);
            return true;
        }

        return false;
    }

    public List<Livro> listarDisponiveis() {

        List<Livro> resultado = new ArrayList<>();

        for (Livro livro : this.livros) {
            if (livro.getStatus() == StatusLivro.DISPONIVEL) {
                resultado.add(livro);
            }
        }

        return resultado;
    }
}
