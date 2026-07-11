package com.gabriellefelix.biblioteca.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LivroTest {


    @Test
    void livroDeveIniciarComoDisponivel() {

        Livro livro = new Livro(
                "Clean Code",
                "Robert C. Martin",
                "9780132350884",
                2008,
                464
        );


        assertEquals(
                StatusLivro.DISPONIVEL,
                livro.getStatus()
        );
    }


    @Test
    void livroDeveFicarEmprestadoAoEmprestar() {

        Livro livro = new Livro(
                "Clean Code",
                "Robert C. Martin",
                "9780132350884",
                2008,
                464
        );


        livro.emprestar();


        assertEquals(
                StatusLivro.EMPRESTADO,
                livro.getStatus()
        );
    }


    @Test
    void livroNaoPodeSerEmprestadoDuasVezes() {

        Livro livro = new Livro(
                "Clean Code",
                "Robert C. Martin",
                "9780132350884",
                2008,
                464
        );


        livro.emprestar();


        assertThrows(
                IllegalStateException.class,
                () -> livro.emprestar()
        );
    }


    @Test
    void livroDeveFicarDisponivelAoDevolver() {

        Livro livro = new Livro(
                "Clean Code",
                "Robert C. Martin",
                "9780132350884",
                2008,
                464
        );


        livro.emprestar();

        livro.devolver();


        assertEquals(
                StatusLivro.DISPONIVEL,
                livro.getStatus()
        );
    }


    @Test
    void livroDisponivelNaoPodeSerDevolvidoNovamente() {

        Livro livro = new Livro(
                "Clean Code",
                "Robert C. Martin",
                "9780132350884",
                2008,
                464
        );


        assertThrows(
                IllegalStateException.class,
                () -> livro.devolver()
        );
    }
}