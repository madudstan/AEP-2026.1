package com.rotalivre.ui;

import com.rotalivre.model.*;
import com.rotalivre.repository.MemoriaSolicitacaoRepository;

import java.util.Scanner;
import java.util.List;

public class 1Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ServicoSolicitacoes servico = new ServicoSolicitacoes(new MemoriaSolicitacaoRepository());

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   RotaLivre - Acessibilidade Urbana    ");
        System.out.println("========================================");

        while (true) {
            System.out.println("\nEscolha seu perfil:");
            System.out.println("1. Cidadão");
            System.out.println("2. Gestor Público");
            System.out.println("0. Sair");
            System.out.print("Opção: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1": menuCidadao(); break;
                case "2": menuGestor(); break;
                case "0": System.exit(0);
                default: System.out.println("Opção inválida!");
            }
        }
    }

    private static void menuCidadao() {
        System.out.println("\n--- MENU CIDADÃO ---");
        System.out.println("1. Registrar Solicitação");
        System.out.println("2. Consultar Protocolo");
        System.out.println("0. Voltar");
        System.out.print("Opção: ");

        String opcao = scanner.nextLine();
        if (opcao.equals("1")) registrarSolicitacao();
        else if (opcao.equals("2")) consultarProtocolo();
    }

    private static void registrarSolicitacao() {
        try {
            System.out.println("\nNova Solicitação de Acessibilidade");

            System.out.print("Deseja ser anônimo? (S/N): ");
            boolean anonimo = scanner.nextLine().equalsIgnoreCase("S");

            String nome = "", email = "";
            if (!anonimo) {
                System.out.print("Nome: "); nome = scanner.nextLine();
                System.out.print("Email: "); email = scanner.nextLine();
            }
            com.rotalivre.model.Usuario usuario = new com.rotalivre.model.Usuario(nome, email, anonimo);

            System.out.println("Categorias: Calçada irregular, Falta de rampa, Semáforo sem sinal sonoro, Ônibus sem elevador, Prédio público inacessível");
            System.out.print("Categoria: "); String categoria = scanner.nextLine();

            System.out.print("Descrição (mín. 10 caracteres): "); String descricao = scanner.nextLine();
            System.out.print("Localização (Bairro/Rua): "); String localizacao = scanner.nextLine();

            System.out.println("Impacto na Mobilidade: 1. Baixo | 2. Médio | 3. Alto");
            System.out.print("Opção: ");
            int imp = Integer.parseInt(scanner.nextLine());
            com.rotalivre.model.Prioridade prioridade = (imp == 3) ? com.rotalivre.model.Prioridade.ALTO : (imp == 2) ? com.rotalivre.model.Prioridade.MEDIO : com.rotalivre.model.Prioridade.BAIXO;

            String protocolo = servico.criarSolicitacao(categoria, descricao, localizacao, usuario, prioridade);
            System.out.println("\nSUCESSO! Guarde seu protocolo: " + protocolo);
            System.out.println("Prazo estimado para resolução: " + prioridade.getDiasSla() + " dias.");

        } catch (Exception e) {
            System.out.println("Erro ao registrar: " + e.getMessage());
        }
    }

    private static void consultarProtocolo() {
        System.out.print("Informe o protocolo: ");
        String protocolo = scanner.nextLine();

        servico.consultarProtocolo(protocolo).ifPresentOrElse(
                s -> {
                    System.out.println("\n--- DETALHES DA SOLICITAÇÃO ---");
                    System.out.println(s);
                    System.out.println("Local: " + s.getLocalizacao());
                    System.out.println("Descrição: " + s.getDescricao());
                    System.out.println("Previsão: " + s.getDataPrevisao());
                    System.out.println("\nHistórico:");
                    s.getHistorico().forEach(System.out::println);
                },
                () -> System.out.println("Protocolo não encontrado.")
        );
    }

    private static void menuGestor() {
        System.out.println("\n--- PAINEL DO GESTOR ---");
        System.out.println("1. Listar Todas as Demandas");
        System.out.println("2. Atualizar Status de Demanda");
        System.out.println("0. Voltar");
        System.out.print("Opção: ");

        String opcao = scanner.nextLine();
        if (opcao.equals("1")) listarDemandas();
        else if (opcao.equals("2")) atualizarStatus();
    }

    private static void listarDemandas() {
        List<com.rotalivre.model.Solicitacao> lista = servico.listarTodas();
        if (lista.isEmpty()) System.out.println("Nenhuma demanda registrada.");
        else lista.forEach(System.out::println);
    }

    private static void atualizarStatus() {
        System.out.print("Protocolo da demanda: ");
        String protocolo = scanner.nextLine();

        System.out.println("Novos Status: 1. Triagem | 2. Em Execução | 3. Resolvido | 4. Encerrado");
        System.out.print("Opção: ");
        int opt = Integer.parseInt(scanner.nextLine());
        com.rotalivre.model.Status novo = switch(opt) {
            case 1 -> com.rotalivre.model.Status.TRIAGEM;
            case 2 -> com.rotalivre.model.Status.EM_EXECUCAO;
            case 3 -> com.rotalivre.model.Status.RESOLVIDO;
            case 4 -> com.rotalivre.model.Status.ENCERRADO;
            default -> com.rotalivre.model.Status.ABERTO;
        };

        System.out.print("Comentário (obrigatório para encerrar): ");
        String comentario = scanner.nextLine();
        System.out.print("Seu Nome (Responsável): ");
        String responsavel = scanner.nextLine();

        try {
            servico.atualizarStatus(protocolo, novo, comentario, responsavel);
            System.out.println("Status atualizado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
