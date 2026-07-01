package com.gabriellefelix.biblioteca.model;

public class Livro {
    private String titulo;
    private String autor;
    private final String isbn;
    private int anoPublicacao;
    private int quantidadePaginas;
    private StatusLivro status;

    public Livro(String titulo, String autor, String isbn, int anoPublicacao, int quantidadePaginas) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.anoPublicacao = anoPublicacao;
        this.quantidadePaginas = quantidadePaginas;
        this.status = StatusLivro.DISPONIVEL;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public int getQuantidadePaginas() {
        return quantidadePaginas;
    }

    public StatusLivro getStatus() {
        return status;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public void setQuantidadePaginas(int quantidadePaginas) {
        this.quantidadePaginas = quantidadePaginas;
    }

    public void emprestar() {
        if (this.status == StatusLivro.EMPRESTADO) {
            throw new IllegalStateException("O livro já está emprestado.");
        }
        status = StatusLivro.EMPRESTADO;
    }

    public void devolver() {
        if (this.status == StatusLivro.DISPONIVEL) {
            throw new IllegalStateException("O livro já está disponível.");
        }
        status = StatusLivro.DISPONIVEL;
    }
}
