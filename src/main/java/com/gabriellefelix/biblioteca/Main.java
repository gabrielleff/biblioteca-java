package com.gabriellefelix.biblioteca;
import com.gabriellefelix.biblioteca.model.Livro;

public class Main {
    public static void main(String[] args) {
        Livro livro = new Livro("Clean Code",
                "Robert C. Martin",
                "9780132350884",
                2008,
                464);

        System.out.println(livro);
    }
}
