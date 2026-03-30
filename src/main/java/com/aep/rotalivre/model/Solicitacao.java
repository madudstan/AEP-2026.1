package com.aep.rotalivre.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Solicitacao {
    private final String protocolo;
    private final String categoria;
    private final String descricao;
    private final String localizacao;
    private final Usuario solicitante;
    private final com.aep.rotalivre.model.Prioridade prioridade;
    private Status statusAtual;
    private final LocalDateTime dataCriacao;
    private LocalDate dataPrevisao;
    private final List<com.aep.rotalivre.model.HistoricoStatus> historico;

    public Solicitacao(String categoria, String descricao, String localizacao, Usuario solicitante, com.aep.rotalivre.model.Prioridade prioridade) {
        this.protocolo = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.categoria = categoria;
        this.descricao = descricao;
        this.localizacao = localizacao;
        this.solicitante = solicitante;
        this.prioridade = prioridade;
        this.statusAtual = Status.ABERTO;
        this.dataCriacao = LocalDateTime.now();
        this.dataPrevisao = LocalDate.now().plusDays(prioridade.getDiasSla());
        this.historico = new ArrayList<>();

        registrarHistorico(null, Status.ABERTO, "Solicitação criada no sistema", "SISTEMA");
    }

    public void atualizarStatus(Status novoStatus, String comentario, String responsavel) {
        if (novoStatus == Status.ENCERRADO && (comentario == null || comentario.trim().isEmpty())) {
            throw new IllegalArgumentException("Comentário obrigatório para encerrar solicitação.");
        }
        registrarHistorico(this.statusAtual, novoStatus, comentario, responsavel);
        this.statusAtual = novoStatus;
    }

    private void registrarHistorico(Status anterior, Status novo, String comentario, String responsavel) {
        this.historico.add(new com.aep.rotalivre.model.HistoricoStatus(anterior, novo, comentario, responsavel));
    }

    // Getters
    public String getProtocolo() { return protocolo; }
    public String getCategoria() { return categoria; }
    public String getDescricao() { return descricao; }
    public String getLocalizacao() { return localizacao; }
    public Usuario getSolicitante() { return solicitante; }
    public com.aep.rotalivre.model.Prioridade getPrioridade() { return prioridade; }
    public Status getStatusAtual() { return statusAtual; }
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public LocalDate getDataPrevisao() { return dataPrevisao; }
    public List<com.aep.rotalivre.model.HistoricoStatus> getHistorico() { return new ArrayList<>(historico); }

    @Override
    public String toString() {
        return String.format("Protocolo: %s | Categoria: %s | Status: %s | Prioridade: %s",
                protocolo, categoria, statusAtual.getDescricao(), prioridade.getDescricao());
    }
}