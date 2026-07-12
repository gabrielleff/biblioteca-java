package com.gabriellefelix.biblioteca.service;

import com.gabriellefelix.biblioteca.model.Livro;
import com.gabriellefelix.biblioteca.model.Usuario;
import com.gabriellefelix.biblioteca.repository.EmprestimoRepository;
import com.gabriellefelix.biblioteca.repository.LivroRepository;
import com.gabriellefelix.biblioteca.repository.UsuarioRepository;

import com.gabriellefelix.biblioteca.model.StatusLivro;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BibliotecaServiceTest {

    private BibliotecaService bibliotecaService;

    @BeforeEach
    void setUp() {

        LivroRepository livroRepository = new LivroRepository();
        UsuarioRepository usuarioRepository = new UsuarioRepository();
        EmprestimoRepository emprestimoRepository = new EmprestimoRepository();

        bibliotecaService = new BibliotecaService(
                livroRepository,
                usuarioRepository,
                emprestimoRepository
        );
    }

    protected Livro criarLivro() {
        return new Livro(
                "Clean Code",
                "Robert C. Martin",
                "9780132350884",
                2008,
                464
        );
    }

    protected Usuario criarUsuario() {
        return new Usuario(
                "Gabrielle",
                "gabrielle@email.com",
                "83999999999"
        );
    }

    @Test
    void deveCadastrarLivro() {

        Livro livro = criarLivro();

        bibliotecaService.cadastrarLivro(livro);

        assertEquals(
                1,
                bibliotecaService.listarLivrosDisponiveis().size()
        );
    }

    @Test
    void deveCadastrarUsuario() {

        Usuario usuario = criarUsuario();

        bibliotecaService.cadastrarUsuario(usuario);

        assertTrue(
                bibliotecaService
                        .buscarHistoricoUsuario(usuario.getId())
                        .isEmpty()
        );
    }

    @Test
    void deveEmprestarLivro() {

        Livro livro = criarLivro();
        Usuario usuario = criarUsuario();

        bibliotecaService.cadastrarLivro(livro);
        bibliotecaService.cadastrarUsuario(usuario);

        bibliotecaService.emprestarLivro(
                livro.getIsbn(),
                usuario.getId()
        );

        assertEquals(
                StatusLivro.EMPRESTADO,
                livro.getStatus()
        );

        assertEquals(
                1,
                bibliotecaService
                        .buscarEmprestimosAtivosUsuario(usuario.getId())
                        .size()
        );
    }

    @Test
    void naoDeveEmprestarLivroJaEmprestado() {

        Livro livro = criarLivro();
        Usuario usuario = criarUsuario();

        bibliotecaService.cadastrarLivro(livro);
        bibliotecaService.cadastrarUsuario(usuario);

        bibliotecaService.emprestarLivro(
                livro.getIsbn(),
                usuario.getId()
        );

        assertThrows(
                IllegalStateException.class,
                () -> bibliotecaService.emprestarLivro(
                        livro.getIsbn(),
                        usuario.getId()
                )
        );
    }

    @Test
    void deveDevolverLivro() {

        Livro livro = criarLivro();
        Usuario usuario = criarUsuario();

        bibliotecaService.cadastrarLivro(livro);
        bibliotecaService.cadastrarUsuario(usuario);

        bibliotecaService.emprestarLivro(
                livro.getIsbn(),
                usuario.getId()
        );

        bibliotecaService.devolverLivro(
                livro.getIsbn()
        );

        assertEquals(
                StatusLivro.DISPONIVEL,
                livro.getStatus()
        );

        assertEquals(
                0,
                bibliotecaService
                        .buscarEmprestimosAtivosUsuario(usuario.getId())
                        .size()
        );
    }

    @Test
    void deveRenovarEmprestimo() {

        Livro livro = criarLivro();
        Usuario usuario = criarUsuario();

        bibliotecaService.cadastrarLivro(livro);
        bibliotecaService.cadastrarUsuario(usuario);

        bibliotecaService.emprestarLivro(
                livro.getIsbn(),
                usuario.getId()
        );

        LocalDate dataOriginal =
                bibliotecaService
                        .buscarEmprestimosAtivosUsuario(usuario.getId())
                        .get(0)
                        .getDataPrevistaDevolucao();

        bibliotecaService.renovarEmprestimo(
                livro.getIsbn()
        );

        LocalDate novaData =
                bibliotecaService
                        .buscarEmprestimosAtivosUsuario(usuario.getId())
                        .get(0)
                        .getDataPrevistaDevolucao();

        assertEquals(
                dataOriginal.plusDays(15),
                novaData
        );
    }

    @Test
    void deveListarLivrosDisponiveis() {

        Livro livro = criarLivro();

        bibliotecaService.cadastrarLivro(livro);

        assertEquals(
                1,
                bibliotecaService
                        .listarLivrosDisponiveis()
                        .size()
        );
    }

    @Test
    void deveRetornarHistoricoDoUsuario() {

        Livro livro = criarLivro();
        Usuario usuario = criarUsuario();

        bibliotecaService.cadastrarLivro(livro);
        bibliotecaService.cadastrarUsuario(usuario);

        bibliotecaService.emprestarLivro(
                livro.getIsbn(),
                usuario.getId()
        );

        bibliotecaService.devolverLivro(
                livro.getIsbn()
        );

        assertEquals(
                1,
                bibliotecaService
                        .buscarHistoricoUsuario(usuario.getId())
                        .size()
        );
    }
}