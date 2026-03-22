package com.rotalivre.model;

public class Usuario {
    private String nome;
    private String email;
    private boolean anonimo;

    public Usuario(String nome, String email, boolean anonimo) {
        this.nome = anonimo ? "Anônimo" : nome;
        this.email = anonimo ? "" : email;
        this.anonimo = anonimo;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public boolean isAnonimo() {
        return anonimo;
    }

    @Override
    public String toString() {
        return anonimo ? "Usuário Anônimo" : nome + " (" + email + ")";
    }
}
