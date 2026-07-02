package com.gabriellefelix.biblioteca.model;
import java.time.LocalDate;

public class Emprestimo {
    private final Livro livro;
    private final Usuario usuario;
    private final LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;

    public Emprestimo(Livro livro, Usuario usuario) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = LocalDate.now();
        this.dataPrevistaDevolucao = dataEmprestimo.plusDays(15);
    }

    @Override
    public String toString() {
        return "Emprestimo{" +
                "livro=" + livro +
                ", usuario=" + usuario +
                ", dataEmprestimo=" + dataEmprestimo +
                ", dataPrevistaDevolucao=" + dataPrevistaDevolucao +
                '}';
    }


    public Livro getLivro() {
        return livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }


    public void renovarPrazo() {
        if (estaAtrasado()) {
            throw new IllegalStateException("Não é possível renovar um empréstimo já atrasado.");
        }

        this.dataPrevistaDevolucao = this.dataPrevistaDevolucao.plusDays(15);
    }

    public boolean estaAtrasado() {
        LocalDate hoje = LocalDate.now();

        return hoje.isAfter(this.dataPrevistaDevolucao);
    }
}
