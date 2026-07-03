package com.gabriellefelix.biblioteca.repository;

import com.gabriellefelix.biblioteca.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {

    private final List<Usuario> usuarios;

    public UsuarioRepository() {
        this.usuarios = new ArrayList<>();
    }

    public void cadastrar(Usuario usuario) {
        if (buscarPorEmail(usuario.getEmail()) != null) {
            throw new IllegalStateException("Já existe um usuário cadastrado com esse email.");
        }

        this.usuarios.add(usuario);
    }

    public List<Usuario> listarTodos() { return new ArrayList<>(this.usuarios); }

    public Usuario buscarPorId(int id) {
        for (Usuario usuario : this.usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        return null;
    }

    public List<Usuario> buscarPorNome(String nome) {
        List<Usuario> resultado = new ArrayList<>();

        for (Usuario usuario : this.usuarios) {
            if (usuario.getNome().toLowerCase().contains(nome.toLowerCase())) {
                resultado.add(usuario);
            }
        }

        return resultado;
    }

    public Usuario buscarPorEmail(String email) {
        for (Usuario usuario : this.usuarios) {
            if (usuario.getEmail().equals(email)) {
                return usuario;
            }
        }
        return null;
    }

    public boolean remover(int id) {
        Usuario usuarioEncontrado = buscarPorId(id);

        if (usuarioEncontrado != null) {
            this.usuarios.remove(usuarioEncontrado);
            return true;
        }

        return false;
    }
}
