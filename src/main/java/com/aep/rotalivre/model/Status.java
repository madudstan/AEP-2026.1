package com.rotalivre.model;

public enum Status {
    ABERTO("Aberto"),
    TRIAGEM("Em Triagem"),
    EM_EXECUCAO("Em Execução"),
    RESOLVIDO("Resolvido"),
    ENCERRADO("Encerrado");

    private final String descricao;

    Status(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
