package com.gabriellefelix.biblioteca.service;

import com.gabriellefelix.biblioteca.repository.EmprestimoRepository;
import com.gabriellefelix.biblioteca.repository.LivroRepository;
import com.gabriellefelix.biblioteca.repository.UsuarioRepository;

import com.gabriellefelix.biblioteca.model.Emprestimo;
import com.gabriellefelix.biblioteca.model.Livro;
import com.gabriellefelix.biblioteca.model.StatusLivro;
import com.gabriellefelix.biblioteca.model.Usuario;

public class BibliotecaService {
    private final LivroRepository livroRepository;
    private final UsuarioRepository usuarioRepository;
    private final EmprestimoRepository emprestimoRepository;

    public BibliotecaService(
            LivroRepository livroRepository,
            UsuarioRepository usuarioRepository,
            EmprestimoRepository emprestimoRepository
    ) {
        this.livroRepository = livroRepository;
        this.usuarioRepository = usuarioRepository;
        this.emprestimoRepository = emprestimoRepository;
    }

    public void emprestarLivro(String isbn, int idUsuario) {

        Livro livro = livroRepository.buscarPorIsbn(isbn);

        if (livro == null) {
            throw new IllegalArgumentException("Livro não encontrado.");
        }

        Usuario usuario = usuarioRepository.buscarPorId(idUsuario);

        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }

        if (livro.getStatus() == StatusLivro.EMPRESTADO) {
            throw new IllegalStateException("O livro já está emprestado.");
        }

        livro.emprestar();

        Emprestimo emprestimo = new Emprestimo(livro, usuario);

        emprestimoRepository.cadastrar(emprestimo);
    }

    public void devolverLivro(String isbn) {

        Livro livro = livroRepository.buscarPorIsbn(isbn);

        if (livro == null) {
            throw new IllegalArgumentException("Livro não encontrado.");
        }

        if (livro.getStatus() == StatusLivro.DISPONIVEL) {
            throw new IllegalStateException("O livro já está disponível.");
        }

        livro.devolver();

        List<Emprestimo> emprestimos = emprestimoRepository.buscarPorLivro(livro);

        if (!emprestimos.isEmpty()) {
            Emprestimo ultimoEmprestimo = emprestimos.get(emprestimos.size() - 1);
            emprestimoRepository.remover(ultimoEmprestimo);
        }
    }
}
