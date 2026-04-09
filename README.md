# RotaLivre – Plataforma Digital para Acessibilidade Urbana e Inclusão Social


O **RotaLivre** é um sistema desenvolvido em Java para gerenciar solicitações de acessibilidade urbana. Ele permite que cidadãos registrem problemas de acessibilidade e que gestores públicos acompanhem e atualizem o status dessas demandas. O objetivo é facilitar a comunicação entre a população e os órgãos responsáveis, promovendo cidades mais inclusivas.

Este projeto faz parte da **AEP 2026.1** do curso de Engenharia de Software (ESOFT5NA).

---

## Estrutura Técnica (Versão Beta)

O projeto segue uma estrutura modular, organizada em pacotes para melhor separação de responsabilidades:

- **com.aep.rotalivre.model**: Entidades de domínio (`Solicitacao`, `Usuario`, `Prioridade`, `Status`).
- **com.aep.rotalivre.repository**: Interface e implementação para persistência em memória.
- **com.aep.rotalivre.service**: Lógica de negócio e regras de validação.
- **com.aep.rotalivre.ui**: Interface de usuário baseada em console (CLI).

---

## Como Compilar e Executar

**Pré-requisitos:**
- Java Development Kit (JDK) 17 ou superior.
- Apache Maven instalado.
- Alguma IDEE para executar o projeto (Sugestão: Intellij Idea 2026)

**Passos:**
1. Clone ou baixe o projeto.
2. Navegue até o diretório raiz.
3. Execute a aplicação através da classe `Main` em `src/main/java/com/aep/rotalivre/ui/Main.java`.

---

## Acadêmicos
- **Heloísa Sayuri Silva Saito** - RA: 24062631-2
- **Maria Eduarda de Castro Lachimia** - RA: 24055202-2
- **Matheus Costa E Silva** - RA: 24000729-2
