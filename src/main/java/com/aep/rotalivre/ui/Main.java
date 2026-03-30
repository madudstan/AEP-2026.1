package com.aep.rotalivre.ui;

import com.aep.rotalivre.model.*;
import com.aep.rotalivre.repository.MemoriaSolicitacaoRepository;
import com.aep.rotalivre.service.ServicoSolicitacoes;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.List;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ServicoSolicitacoes servico = new ServicoSolicitacoes(new MemoriaSolicitacaoRepository());

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BOLD = "\u001B[1m";

    public static void main(String[] args) {
        exibirCabecalho();

        while (true) {
            exibirMenuPrincipal();
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1": menuCidadao(); break;
                case "2": menuGestor(); break;
                case "0": System.out.println("\nSaindo do RotaLivre. Até mais!"); System.exit(0);
                default: System.out.println("\nOpção inválida. Por favor, escolha uma opção válida.");
            }
        }
    }

    private static void exibirCabecalho() {
        System.out.println(" " + negrito("➤ RotaLivre"));
        System.out.println("- Sistema de Acessibilidade Urbana");
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n╔═════════════════════════════════════════════════════╗");
        System.out.println("║                 ESCOLHA SEU PERFIL                  ║");
        System.out.println("╠═════════════════════════════════════════════════════╣");
        System.out.println("║ [1] Cidadão                                         ║");
        System.out.println("║ [2] Gestor Público                                  ║");
        System.out.println("║ [0] Sair                                            ║");
        System.out.println("╚═════════════════════════════════════════════════════╝");
        System.out.print("Digite sua opção: ");
    }

    private static void menuCidadao() {
        while (true) {
            System.out.println("\n╔═════════════════════════════════════════════════════╗");
            System.out.println("║                   MENU CIDADÃO                      ║");
            System.out.println("╠═════════════════════════════════════════════════════╣");
            System.out.println("║ [1] Registrar Nova Solicitação                      ║");
            System.out.println("║ [2] Consultar Solicitação por Protocolo             ║");
            System.out.println("║ [0] Voltar ao Menu Principal                        ║");
            System.out.println("╚═════════════════════════════════════════════════════╝");
            System.out.print("Digite sua opção: ");

            String opcao = scanner.nextLine();
            switch (opcao) {
                case "1": registrarSolicitacao(); break;
                case "2": consultarProtocolo(); break;
                case "0": return;
                default: System.out.println("\nOpção inválida. Por favor, escolha uma opção válida.");
            }
        }
    }


    private static void registrarSolicitacao() {
        try {
            System.out.println("\n╔═════════════════════════════════════════════════════╗");
            System.out.println("║          NOVA SOLICITAÇÃO DE ACESSIBILIDADE         ║");
            System.out.println("╚═════════════════════════════════════════════════════╝");

            System.out.print("Deseja ser anônimo? (S/N): ");
            boolean anonimo = scanner.nextLine().equalsIgnoreCase("S");

            String nome = "", email = "";
            if (!anonimo) {
                System.out.print("Nome: "); nome = scanner.nextLine();
                System.out.print("Email: "); email = scanner.nextLine();
            }
            Usuario usuario = new Usuario(nome, email, anonimo);

            System.out.println("\n" + negrito("CATEGORIAS DISPONÍVEIS1:\n"));
            System.out.println("  [1] Calçada irregular");
            System.out.println("  [2] Falta de rampa");
            System.out.println("  [3] Semáforo sem sinal sonoro");
            System.out.println("  [4] Ônibus sem elevador");
            System.out.println("  [5] Prédio público inacessível");
            System.out.println("  [6] Outros (Digitar Categoria)");

            System.out.print("\nEscolha a Categoria (1-6): ");
            String optCat = scanner.nextLine();
            String categoria;

            categoria = switch(optCat) {
                case "1" -> "Calçada irregular";
                case "2" -> "Falta de rampa";
                case "3" -> "Semáforo sem sinal sonoro";
                case "4" -> "Ônibus sem elevador";
                case "5" -> "Prédio público inacessível";
                case "6" -> {
                    System.out.print("Digite a nova categoria: ");
                    yield scanner.nextLine();
                }
                default -> {
                    System.out.println("Opção inválida, usando \'Geral\'.");
                    yield "Geral";
                }
            };

            System.out.print("Descrição (mín. 10 caracteres): "); String descricao = scanner.nextLine();
            System.out.print("Localização (Bairro/Rua): "); String localizacao = scanner.nextLine();

            System.out.println("\nImpacto na Mobilidade:");
            System.out.println();
            System.out.println("  [1] Baixo");
            System.out.println("  [2] Médio");
            System.out.println("  [3] Alto");
            System.out.println();
            System.out.print("Escolha o nível de Impacto: ");
            int imp = Integer.parseInt(scanner.nextLine());
            Prioridade prioridade = (imp == 3) ? Prioridade.ALTO : (imp == 2) ? Prioridade.MEDIO : Prioridade.BAIXO;

            String protocolo = servico.criarSolicitacao(categoria, descricao, localizacao, usuario, prioridade);
            System.out.println("\n╔═════════════════════════════════════════════════════╗");
            System.out.println("║                  SOLICITAÇÃO REGISTRADA!            ║");
            System.out.println("╠═════════════════════════════════════════════════════╣");
            System.out.println(String.format("║ Protocolo: %-40s ║", protocolo));
            System.out.println(String.format("║ Prazo estimado: %-2s dias                             ║", prioridade.getDiasSla()));
            System.out.println("╚═════════════════════════════════════════════════════╝");

        } catch (Exception e) {
            System.out.println("\nERRO: Não foi possível registrar a solicitação. Detalhes: " + e.getMessage());
        }
    }

    private static void consultarProtocolo() {
        System.out.println("\n╔═════════════════════════════════════════════════════╗");
        System.out.println("║          CONSULTAR SOLICITAÇÃO POR PROTOCOLO        ║");
        System.out.println("╚═════════════════════════════════════════════════════╝");
        System.out.print("Informe o protocolo: ");
        String protocolo = scanner.nextLine();

        servico.consultarProtocolo(protocolo).ifPresentOrElse(
                s -> {
                    System.out.println("\n╔═════════════════════════════════════════════════════╗");
                    System.out.println("║             DETALHES DA SOLICITAÇÃO                 ║");
                    System.out.println("╠═════════════════════════════════════════════════════╣");
                    System.out.println(String.format("║ Protocolo: %-40s ║", s.getProtocolo()));
                    System.out.println(String.format("║ Status:    %-40s ║", s.getStatusAtual().getDescricao()));
                    System.out.println(String.format("║ Categoria: %-40s ║", s.getCategoria()));
                    System.out.println(String.format("║ Local:     %-40s ║", s.getLocalizacao()));
                    System.out.println(String.format("║ Descrição: %-40s ║", s.getDescricao()));
                    System.out.println(String.format("║ Previsão:  %-40s ║", s.getDataPrevisao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
                    System.out.println("╠═════════════════════════════════════════════════════╣");
                    System.out.println("║               HISTÓRICO DE ATUALIZAÇÕES             ║");
                    System.out.println("╠═════════════════════════════════════════════════════╣");
                    s.getHistorico().forEach(h -> System.out.println(String.format("║ %-51s", h.toString())));
                    System.out.println("╚═════════════════════════════════════════════════════╝");
                },
                () -> System.out.println("\nProtocolo não encontrado. Verifique e tente novamente.")
        );
    }

    private static void menuGestor() {
        while (true) {
            System.out.println("\n╔═════════════════════════════════════════════════════╗");
            System.out.println("║                  PAINEL DO GESTOR                   ║");
            System.out.println("╠═════════════════════════════════════════════════════╣");
            System.out.println("║ [1] Listar Todas as Demandas                        ║");
            System.out.println("║ [2] Atualizar Status de Demanda                     ║");
            System.out.println("║ [0] Voltar ao Menu Principal                        ║");
            System.out.println("╚═════════════════════════════════════════════════════╝");
            System.out.print("Digite sua opção: ");

            String opcao = scanner.nextLine();
            switch (opcao) {
                case "1": listarDemandas(); break;
                case "2": atualizarStatus(); break;
                case "0": return;
                default: System.out.println("\nOpção inválida. Por favor, escolha uma opção válida.");
            }
        }
    }

    private static void listarDemandas() {
        System.out.println("\n╔═════════════════════════════════════════════════════╗");
        System.out.println("║                  LISTA DE DEMANDAS                  ║");
        System.out.println("╚═════════════════════════════════════════════════════╝");
        List<Solicitacao> lista = servico.listarTodas();
        if (lista.isEmpty()) {
            System.out.println("\nNenhuma demanda registrada no momento.");
        } else {
            lista.forEach(s -> {
                System.out.println("-------------------------------------------------------");
                System.out.println(String.format("Protocolo: %-38s", s.getProtocolo()));
                System.out.println(String.format("Status: %-41s", s.getStatusAtual().getDescricao()));
                System.out.println(String.format("Categoria: %-38s", s.getCategoria()));
                System.out.println(String.format("Local: %-42s", s.getLocalizacao()));
                System.out.println(String.format("Descrição: %-38s", s.getDescricao()));
                System.out.println(String.format("Previsão: %-39s", s.getDataPrevisao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
                System.out.println("-------------------------------------------------------");
            });
        }
    }

    private static void atualizarStatus() {
        System.out.println("\n╔═════════════════════════════════════════════════════╗");
        System.out.println("║             ATUALIZAR STATUS DE DEMANDA             ║");
        System.out.println("╚═════════════════════════════════════════════════════╝");
        System.out.print("Informe o protocolo da demanda: ");
        String protocolo = scanner.nextLine();

        System.out.println("\nNovos Status Disponíveis:");
        System.out.println();
        System.out.println("  [1] Triagem");
        System.out.println("  [2] Em Execução");
        System.out.println("  [3] Resolvido");
        System.out.println("  [4] Encerrado");
        System.out.println();
        System.out.print("Escolha o novo Status: ");
        int opt = Integer.parseInt(scanner.nextLine());
        Status novo = switch(opt) {
            case 1 -> Status.TRIAGEM;
            case 2 -> Status.EM_EXECUCAO;
            case 3 -> Status.RESOLVIDO;
            case 4 -> Status.ENCERRADO;
            default -> Status.ABERTO;
        };

        System.out.print("Comentário (obrigatório para encerrar): ");
        String comentario = scanner.nextLine();
        System.out.print("Seu Nome (Responsável): ");
        String responsavel = scanner.nextLine();

        try {
            servico.atualizarStatus(protocolo, novo, comentario, responsavel);
            System.out.println("\nStatus da demanda atualizado com sucesso!");
        } catch (Exception e) {
            System.out.println("\nERRO: Não foi possível atualizar o status. Detalhes: " + e.getMessage());
        }
    }

    private static String negrito(String texto) {
        return ANSI_BOLD + texto + ANSI_RESET;
    }

}