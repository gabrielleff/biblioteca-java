package com.gabriellefelix.biblioteca;

import com.gabriellefelix.biblioteca.model.*;
import com.gabriellefelix.biblioteca.repository.*;
import com.gabriellefelix.biblioteca.service.BibliotecaService;

import java.util.List;
import java.util.Scanner;

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

        Scanner scanner = new Scanner(System.in);

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


                case "2": {

                    System.out.print("Digite o nome do usuário: ");
                    String nome = scanner.nextLine();

                    System.out.print("Digite o email do usuário: ");
                    String email = scanner.nextLine();

                    System.out.print("Digite o telefone do usuário: ");
                    String telefone = scanner.nextLine();


                    Usuario usuario = new Usuario(
                            nome,
                            email,
                            telefone
                    );


                    bibliotecaService.cadastrarUsuario(usuario);

                    System.out.println("Usuário cadastrado com sucesso!");

                    break;
                }


                case "3": {

                    System.out.print("Digite o ISBN do livro: ");
                    String isbn = scanner.nextLine();

                    System.out.print("Digite o ID do usuário: ");
                    int idUsuario = Integer.parseInt(scanner.nextLine());


                    bibliotecaService.emprestarLivro(
                            isbn,
                            idUsuario
                    );


                    System.out.println("Empréstimo realizado com sucesso!");

                    break;
                }


                case "4": {

                    System.out.print("Digite o ISBN do livro: ");
                    String isbn = scanner.nextLine();


                    bibliotecaService.devolverLivro(isbn);


                    System.out.println("Livro devolvido com sucesso!");

                    break;
                }


                case "5": {

                    System.out.print("Digite o ISBN do livro: ");
                    String isbn = scanner.nextLine();


                    bibliotecaService.renovarEmprestimo(isbn);


                    System.out.println("Empréstimo renovado com sucesso!");

                    break;
                }


                case "6": {

                    System.out.println("\n=== LIVROS DISPONÍVEIS ===");


                    List<Livro> livrosDisponiveis =
                            bibliotecaService.listarLivrosDisponiveis();


                    if (livrosDisponiveis.isEmpty()) {

                        System.out.println("Nenhum livro disponível.");

                    } else {

                        for (Livro livro : livrosDisponiveis) {
                            System.out.println(livro);
                        }
                    }

                    break;
                }


                case "7": {

                    System.out.println("\n=== EMPRÉSTIMOS ATRASADOS ===");


                    List<Emprestimo> emprestimosAtrasados =
                            bibliotecaService.listarEmprestimosAtrasados();


                    if (emprestimosAtrasados.isEmpty()) {

                        System.out.println("Não existem empréstimos atrasados.");

                    } else {

                        for (Emprestimo emprestimo : emprestimosAtrasados) {
                            System.out.println(emprestimo);
                        }
                    }

                    break;
                }


                case "8": {

                    System.out.print("Digite o ID do usuário: ");
                    int idUsuario = Integer.parseInt(scanner.nextLine());


                    List<Emprestimo> historico =
                            bibliotecaService.buscarHistoricoUsuario(idUsuario);


                    System.out.println("\n=== HISTÓRICO DO USUÁRIO ===");


                    if (historico.isEmpty()) {

                        System.out.println("Nenhum empréstimo encontrado.");

                    } else {

                        for (Emprestimo emprestimo : historico) {
                            System.out.println(emprestimo);
                        }
                    }

                    break;
                }


                case "0": {

                    System.out.println("Encerrando sistema...");

                    break;
                }


                default:

                    System.out.println("Opção inválida.");
            }


        } while (!opcao.equals("0"));


        scanner.close();
    }
}