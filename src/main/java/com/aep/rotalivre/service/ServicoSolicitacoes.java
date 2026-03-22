package com.rotalivre.service;

import com.rotalivre.model.*;
import com.rotalivre.repository.SolicitacaoRepository;
import java.util.List;
import java.util.Optional;

public class ServicoSolicitacoes {
    private final SolicitacaoRepository repository;

    public ServicoSolicitacoes(SolicitacaoRepository repository) {
        this.repository = repository;
    }

    public String criarSolicitacao(String categoria, String descricao, String localizacao, com.rotalivre.model.Usuario solicitante, com.rotalivre.model.Prioridade prioridade) {
        validarCamposObrigatorios(categoria, descricao, localizacao);
        com.rotalivre.model.Solicitacao nova = new com.rotalivre.model.Solicitacao(categoria, descricao, localizacao, solicitante, prioridade);
        repository.salvar(nova);
        return nova.getProtocolo();
    }

    public Optional<com.rotalivre.model.Solicitacao> consultarProtocolo(String protocolo) {
        return repository.buscarPorProtocolo(protocolo);
    }

    public void atualizarStatus(String protocolo, com.rotalivre.model.Status novoStatus, String comentario, String responsavel) {
        com.rotalivre.model.Solicitacao solicitacao = repository.buscarPorProtocolo(protocolo)
                .orElseThrow(() -> new IllegalArgumentException("Solicitação não encontrada: " + protocolo));

        solicitacao.atualizarStatus(novoStatus, comentario, responsavel);
    }

    public List<com.rotalivre.model.Solicitacao> listarTodas() {
        return repository.listarTodas();
    }

    public List<com.rotalivre.model.Solicitacao> filtrarPorBairro(String bairro) {
        return repository.buscarPorBairro(bairro);
    }

    public List<com.rotalivre.model.Solicitacao> filtrarPorCategoria(String categoria) {
        return repository.buscarPorCategoria(categoria);
    }

    private void validarCamposObrigatorios(String categoria, String descricao, String localizacao) {
        if (categoria == null || categoria.isEmpty()) throw new IllegalArgumentException("Categoria é obrigatória.");
        if (descricao == null || descricao.length() < 10) throw new IllegalArgumentException("Descrição deve ter no mínimo 10 caracteres.");
        if (localizacao == null || localizacao.isEmpty()) throw new IllegalArgumentException("Localização é obrigatória.");
    }
}
