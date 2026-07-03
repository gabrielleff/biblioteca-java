package com.gabriellefelix.biblioteca.repository;

import com.gabriellefelix.biblioteca.model.Emprestimo;
import com.gabriellefelix.biblioteca.model.Livro;
import com.gabriellefelix.biblioteca.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class EmprestimoRepository {

    private final List<Emprestimo> emprestimos;

    public EmprestimoRepository() {
        this.emprestimos = new ArrayList<>();
    }

    public void cadastrar(Emprestimo emprestimo) {
        this.emprestimos.add(emprestimo);
    }

    public List<Emprestimo> listarTodos() {
        return new ArrayList<>(this.emprestimos); }

    public List<Emprestimo> buscarPorLivro(Livro livro) {
        List<Emprestimo> resultado = new ArrayList<>();

        for (Emprestimo emprestimo : this.emprestimos) {
            if (emprestimo.getLivro().getIsbn().equals(livro.getIsbn())) {
                resultado.add(emprestimo);
            }
        }

        return resultado;
    }

    public List<Emprestimo> buscarPorUsuario(Usuario usuario) {
        List<Emprestimo> resultado = new ArrayList<>();

        for (Emprestimo emprestimo : this.emprestimos) {
            if (emprestimo.getUsuario().getId() == usuario.getId()) {
                resultado.add(emprestimo);
            }
        }

        return resultado;
    }

    public List<Emprestimo> listarAtrasados() {
        List<Emprestimo> resultado = new ArrayList<>();

        for (Emprestimo emprestimo : this.emprestimos) {
            if (emprestimo.estaAtrasado()) {
                resultado.add(emprestimo);
            }
        }

        return resultado;
    }

    public boolean remover(Emprestimo emprestimo) {
        return this.emprestimos.remove(emprestimo);
    }
}
