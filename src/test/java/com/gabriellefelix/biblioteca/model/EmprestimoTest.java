package com.gabriellefelix.biblioteca.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmprestimoTest {

    private Livro criarLivro() {
        return new Livro(
                "Clean Code",
                "Robert C. Martin",
                "9780132350884",
                2008,
                464
        );
    }

    private Usuario criarUsuario() {
        return new Usuario(
                "Gabrielle",
                "gabrielle@email.com",
                "83999999999"
        );
    }

    @Test
    void emprestimoDeveSerCriadoComSucesso() {

        Emprestimo emprestimo =
                new Emprestimo(
                        criarLivro(),
                        criarUsuario()
                );

        assertNotNull(emprestimo);

        assertTrue(emprestimo.estaAtivo());
    }

    @Test
    void emprestimoDeveTerPrazoInicialDe15Dias() {

        Emprestimo emprestimo =
                new Emprestimo(
                        criarLivro(),
                        criarUsuario()
                );

        assertEquals(
                LocalDate.now().plusDays(15),
                emprestimo.getDataPrevistaDevolucao()
        );
    }

    @Test
    void emprestimoDeveSerFinalizado() {

        Emprestimo emprestimo =
                new Emprestimo(
                        criarLivro(),
                        criarUsuario()
                );

        emprestimo.finalizarEmprestimo();

        assertFalse(emprestimo.estaAtivo());

        assertNotNull(emprestimo.getDataDevolucao());
    }

    @Test
    void emprestimoNaoPodeSerFinalizadoDuasVezes() {

        Emprestimo emprestimo =
                new Emprestimo(
                        criarLivro(),
                        criarUsuario()
                );

        emprestimo.finalizarEmprestimo();

        assertThrows(
                IllegalStateException.class,
                () -> emprestimo.finalizarEmprestimo()
        );
    }

    @Test
    void emprestimoDeveRenovarMais15Dias() {

        Emprestimo emprestimo =
                new Emprestimo(
                        criarLivro(),
                        criarUsuario()
                );

        LocalDate dataOriginal =
                emprestimo.getDataPrevistaDevolucao();

        emprestimo.renovarPrazo();

        assertEquals(
                dataOriginal.plusDays(15),
                emprestimo.getDataPrevistaDevolucao()
        );
    }

}
