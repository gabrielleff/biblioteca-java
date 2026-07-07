package com.gabriellefelix.biblioteca.model;
import java.time.LocalDate;

public class Emprestimo {
    private final Livro livro;
    private final Usuario usuario;
    private final LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucao;

    public Emprestimo(Livro livro, Usuario usuario) {

        if (livro == null || usuario == null) {
            throw new IllegalArgumentException("Livro e usuário são obrigatórios.");
        }

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
                ", dataDevolucao=" + dataDevolucao +
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

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void renovarPrazo() {
        if (estaAtrasado()) {
            throw new IllegalStateException("Não é possível renovar um empréstimo já atrasado.");
        }

        this.dataPrevistaDevolucao = this.dataPrevistaDevolucao.plusDays(15);
    }

    public boolean estaAtrasado() {
        return estaAtivo() &&
                LocalDate.now().isAfter(dataPrevistaDevolucao);
    }

    public void finalizarEmprestimo() {
        if (!estaAtivo()) {
            throw new IllegalStateException("O empréstimo já foi finalizado.");
        }

        this.dataDevolucao = LocalDate.now();
    }

    public boolean estaAtivo() {
        return dataDevolucao == null;
    }
}
