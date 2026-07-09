package com.gabriellefelix.biblioteca.ui;

import com.gabriellefelix.biblioteca.service.BibliotecaService;

import com.gabriellefelix.biblioteca.model.Livro;

import java.util.Scanner;

public class MenuBiblioteca {

    private final BibliotecaService bibliotecaService;
    private final Scanner scanner;

    public MenuBiblioteca(BibliotecaService bibliotecaService) {
        this.bibliotecaService = bibliotecaService;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {

        String opcao;

        do {

            System.out.println("\n===== SISTEMA DE BIBLIOTECA =====");
            System.out.println("1 - Cadastrar livro");
            System.out.println("2 - Cadastrar usuário");
            System.out.println("3 - Emprestar livro");
            System.out.println("4 - Devolver livro");
            System.out.println("5 - Renovar empréstimo");
            System.out.println("6 - Listar livros disponíveis");
            System.out.println("7 - Listar empréstimos atrasados");
            System.out.println("8 - Ver histórico de usuário");
            System.out.println("0 - Sair");

            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextLine();

            switch (opcao) {

                case "1": {

                    System.out.print("Digite o título do livro: ");
                    String titulo = scanner.nextLine();

                    System.out.print("Digite o autor do livro: ");
                    String autor = scanner.nextLine();

                    System.out.print("Digite o ISBN do livro: ");
                    String isbn = scanner.nextLine();

                    System.out.print("Digite o ano de publicação: ");
                    int anoPublicacao = Integer.parseInt(scanner.nextLine());

                    System.out.print("Digite a quantidade de páginas: ");
                    int quantidadePaginas = Integer.parseInt(scanner.nextLine());

                    Livro livro = new Livro(
                            titulo,
                            autor,
                            isbn,
                            anoPublicacao,
                            quantidadePaginas
                    );

                    bibliotecaService.cadastrarLivro(livro);

                    System.out.println("Livro cadastrado com sucesso!");

                    break;
                }

                case "2":
                    System.out.println("Cadastrar usuário");
                    break;

                case "3":
                    System.out.println("Emprestar livro");
                    break;

                case "4":
                    System.out.println("Devolver livro");
                    break;

                case "5":
                    System.out.println("Renovar empréstimo");
                    break;

                case "6":
                    System.out.println("Listar livros disponíveis");
                    break;

                case "7":
                    System.out.println("Listar empréstimos atrasados");
                    break;

                case "8":
                    System.out.println("Histórico do usuário");
                    break;

                case "0":
                    System.out.println("Encerrando sistema...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (!opcao.equals("0"));

        scanner.close();
    }
}
