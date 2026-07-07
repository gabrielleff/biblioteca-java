package com.gabriellefelix.biblioteca;

import com.gabriellefelix.biblioteca.model.*;
import com.gabriellefelix.biblioteca.repository.*;
import com.gabriellefelix.biblioteca.service.BibliotecaService;

public class Main {

    public static void main(String[] args) {

        LivroRepository livroRepository = new LivroRepository();
        UsuarioRepository usuarioRepository = new UsuarioRepository();
        EmprestimoRepository emprestimoRepository = new EmprestimoRepository();

        BibliotecaService bibliotecaService = new BibliotecaService(
                livroRepository,
                usuarioRepository,
                emprestimoRepository
        );


        Livro livro1 = new Livro(
                "Clean Code",
                "Robert C. Martin",
                "9780132350884",
                2008,
                464
        );

        Usuario usuario1 = new Usuario(
                "Gabrielle",
                "gabrielle@email.com",
                "83999999999"
        );


        bibliotecaService.cadastrarLivro(livro1);

        bibliotecaService.cadastrarUsuario(usuario1);


// EMPRESTAR LIVRO
        bibliotecaService.emprestarLivro(
                "9780132350884",
                usuario1.getId()
        );

        System.out.println("=== Após empréstimo ===");
        System.out.println(livro1);
        System.out.println(bibliotecaService.buscarHistoricoUsuario(usuario1.getId()));


// RENOVAR EMPRÉSTIMO
        bibliotecaService.renovarEmprestimo(
                "9780132350884"
        );

        System.out.println("\n=== Após renovação ===");
        System.out.println(bibliotecaService.buscarHistoricoUsuario(usuario1.getId()));


// DEVOLVER LIVRO
        bibliotecaService.devolverLivro(
                "9780132350884"
        );

        System.out.println("\n=== Após devolução ===");
        System.out.println(livro1);
        System.out.println(bibliotecaService.buscarHistoricoUsuario(usuario1.getId()));
    }
}