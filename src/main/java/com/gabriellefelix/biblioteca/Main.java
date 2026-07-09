package com.gabriellefelix.biblioteca;

import com.gabriellefelix.biblioteca.repository.EmprestimoRepository;
import com.gabriellefelix.biblioteca.repository.LivroRepository;
import com.gabriellefelix.biblioteca.repository.UsuarioRepository;
import com.gabriellefelix.biblioteca.service.BibliotecaService;
import com.gabriellefelix.biblioteca.ui.MenuBiblioteca;

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

        MenuBiblioteca menu = new MenuBiblioteca(bibliotecaService);

        menu.iniciar();
    }
}