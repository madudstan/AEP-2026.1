# RotaLivre - Acessibilidade Urbana

O **RotaLivre** é um sistema desenvolvido em Java para gerenciar solicitações de acessibilidade urbana. Ele permite que cidadãos registrem problemas de acessibilidade e que gestores públicos acompanhem e atualizem o status dessas demandas. O objetivo é facilitar a comunicação entre a população e os órgãos responsáveis, promovendo cidades mais inclusivas.

Este projeto faz parte da **AEP 2026.1** do curso de Engenharia de Software (ESOFT5NA).

---

## Objetivo e Alinhamento com ODS (ONU)

Este projeto está alinhado com os Objetivos de Desenvolvimento Sustentável da ONU:

- **ODS 16 – Paz, Justiça e Instituições Eficazes**: Promove a transparência e o acesso a serviços públicos, fortalecendo a confiança nas instituições.
- **ODS 10 – Redução das Desigualdades**: Busca reduzir barreiras e garantir acesso igualitário a serviços, especialmente para grupos em situação de vulnerabilidade.
- **ODS 11 – Cidades e Comunidades Sustentáveis**: Contribui para a criação de cidades mais inclusivas e seguras através da participação ativa da população.

---

## Estrutura Técnica (Versão Beta)

O projeto segue uma estrutura modular, organizada em pacotes para melhor separação de responsabilidades:

- **com.aep.rotalivre.model**: Entidades de domínio (`Solicitacao`, `Usuario`, `Prioridade`, `Status`).
- **com.aep.rotalivre.repository**: Interface e implementação para persistência em memória.
- **com.aep.rotalivre.service**: Lógica de negócio e regras de validação.
- **com.aep.rotalivre.ui**: Interface de usuário baseada em console (CLI).

### Clean Code Aplicado:
1. **Nomes Significativos**: Métodos como `validarCamposObrigatorios()` e `gerarProtocoloUnico()`.
2. **Responsabilidade Única (SRP)**: Classes com funções bem definidas, trazendo organização e evitando misturar as responsabilidades do sistema em um só lugar. 
3. **Extração de Métodos**: Lógicas complexas divididas em métodos menores e legíveis (ex: validações no serviço).

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

## Perfis e Personas (IHC)

Abaixo estão detalhados os perfis de usuários que guiaram o desenvolvimento da interface e das funcionalidades do sistema.

### 1️. Perfil: Cidadão com Baixa Familiaridade Digital
*Usuários com dificuldades tecnológicas ou limitações físicas.*

| Persona | Contexto / Condição | Dores | Necessidades | "Medo"                            |
| :--- | :--- | :--- | :--- |:----------------------------------|
| **José Roque (76)** | Aposentado, mobilidade reduzida. | Calçadas quebradas. | Letras grandes, poucos passos. | Não conseguir usar sozinho.       |
| **Maria Helena (58)** | Auxiliar de cozinha, baixa visão. | Semáforos sem som. | Alto contraste, textos claros. | Não conseguir ler as informações. |
| **Francisco (45)** | Motorista, pai de criança PCD. | Falta de rampas em escolas. | Cadastro rápido, anexo de fotos. | Denúncia não ser levada a sério.  |

### 2️. Perfil: Cidadão em Situação de Vulnerabilidade
*Foco em acessibilidade e proteção contra retaliação.*

| Persona | Contexto / Condição | Dores | Necessidades | "Medo"                         |
| :--- | :--- | :--- | :--- |:-------------------------------|
| **Ana Paula (29)** | Estudante, cadeirante. | Transporte sem acessibilidade. | Registro rápido via celular. | Sofrer represálias de locais.  |
| **Roberto (41)** | Vendedor, deficiência auditiva. | Falta de sinalização visual. | Comunicação 100% textual. | Ter a solicitação ignorada.    |
| **Juliana Ferreira (34)** | Cuidadora, mãe de autista. | Falta de espaços adaptados. | Acompanhamento fácil (protocolo). | Dificuldades afetarem o filho. |

### 3️. Perfil: Servidor Público / Gestor
*Foco em organização e eficiência operacional.*

| Persona | Contexto / Cargo | Dores | Necessidades | "Medo"                         |
| :--- | :--- | :--- | :--- |:-------------------------------|
| **Marcos Pereira (39)** | Atendente da Prefeitura. | Volume alto de demandas. | Painel simples para triagem. | Perder pedidos importantes.    |
| **Fernanda Souza (42)** | Gestora de Mobilidade. | Dificuldade em priorizar. | Filtros por bairro e impacto. | Decidir sem dados suficientes. |
| **Ricardo Almeida (36)** | Analista de Planejamento. | Falta de histórico organizado. | Dados estruturados e históricos. | Problemas recorrentes ficarem. |

---

## Equipe (ESOFT5NA 2026.1)
- **Heloísa Sayuri Silva Saito** - RA: 24062631-2
- **Maria Eduarda de Castro Lachimia** - RA: 24055202-2
- **Matheus Costa E Silva** - RA: 24000729-2
