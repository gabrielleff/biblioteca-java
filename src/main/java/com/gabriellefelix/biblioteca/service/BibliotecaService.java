package com.gabriellefelix.biblioteca.service;

import com.gabriellefelix.biblioteca.repository.EmprestimoRepository;
import com.gabriellefelix.biblioteca.repository.LivroRepository;
import com.gabriellefelix.biblioteca.repository.UsuarioRepository;

import com.gabriellefelix.biblioteca.model.Emprestimo;
import com.gabriellefelix.biblioteca.model.Livro;
import com.gabriellefelix.biblioteca.model.StatusLivro;
import com.gabriellefelix.biblioteca.model.Usuario;

import java.util.List;

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

        Emprestimo emprestimo = emprestimoRepository.buscarEmprestimoAtivoPorLivro(livro);

        if (emprestimo == null) {
            throw new IllegalStateException("Não existe empréstimo ativo para esse livro.");
        }

        livro.devolver();

        emprestimo.finalizarEmprestimo();
    }

    public void renovarEmprestimo(String isbn) {

        Livro livro = livroRepository.buscarPorIsbn(isbn);

        if (livro == null) {
            throw new IllegalArgumentException("Livro não encontrado.");
        }

        Emprestimo emprestimo = emprestimoRepository.buscarEmprestimoAtivoPorLivro(livro);

        if (emprestimo == null) {
            throw new IllegalStateException("Não existe empréstimo ativo para esse livro.");
        }

        emprestimo.renovarPrazo();
    }

    public List<Emprestimo> listarEmprestimosAtrasados() {
        return emprestimoRepository.listarAtrasados();
    }

    public List<Livro> listarLivrosDisponiveis() {
        return livroRepository.listarDisponiveis();
    }

    public List<Emprestimo> buscarHistoricoUsuario(int idUsuario) {

        Usuario usuario = usuarioRepository.buscarPorId(idUsuario);

        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }

        return emprestimoRepository.buscarPorUsuario(usuario);
    }

    public List<Emprestimo> buscarEmprestimosAtivosUsuario(int idUsuario) {

        Usuario usuario = usuarioRepository.buscarPorId(idUsuario);

        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado.");
        }

        return emprestimoRepository.buscarEmprestimosAtivosPorUsuario(usuario);
    }
}
