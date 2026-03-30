# **RotaLivre - Acessibilidade Urbana**

### **Descrição do Projeto**

O RotaLivre é um sistema desenvolvido em Java para gerenciar solicitações de acessibilidade urbana. Ele permite que cidadãos registrem problemas de acessibilidade e que gestores públicos acompanhem e atualizem o status dessas demandas. O objetivo é facilitar a comunicação entre a população e os órgãos responsáveis, promovendo cidades mais inclusivas.

### **Alinhamento com os ODS**

Este projeto está alinhado com os Objetivos de Desenvolvimento Sustentável (ODS) da ONU, com foco especial em:

• **ODS 16** – Paz, Justiça e Instituições Eficazes: O RotaLivre promove a transparência e o acesso a serviços públicos, permitindo que cidadãos registrem demandas e acompanhem seu status, fortalecendo a confiança nas instituições e garantindo respostas mais eficazes.

• **ODS 10** – Redução das Desigualdades: Ao facilitar o registro e acompanhamento de solicitações de acessibilidade, o sistema busca reduzir barreiras e garantir acesso igualitário a serviços e informações, especialmente para grupos em situação de vulnerabilidade.

• **ODS 11** – Cidades e Comunidades Sustentáveis: Contribui para a criação de cidades mais inclusivas e seguras, ao permitir que a população participe ativamente da identificação e resolução de problemas de acessibilidade urbana, melhorando a qualidade de vida e a gestão urbana.

### **Funcionalidades do Sistema**

O sistema RotaLivre oferece dois perfis de usuário principais, cada um com um conjunto específico de funcionalidades:

**Perfil Cidadão**

• Registrar Nova Solicitação: Permite que o cidadão registre problemas de acessibilidade, como calçadas irregulares, falta de rampas, semáforos sem sinal sonoro, ônibus sem elevador, prédios públicos inacessíveis e entre outros. É possível registrar a solicitação de forma anônima ou identificada, e também adicionar categorias personalizadas.

• Consultar Solicitação por Protocolo: O cidadão pode acompanhar o status e o histórico de atualizações de uma solicitação específica utilizando o número de protocolo gerado no momento do registro.

**Perfil Gestor Público**

• Listar Todas as Demandas: Visualiza todas as solicitações de acessibilidade registradas no sistema, com detalhes como protocolo, categoria, status, localização, descrição e previsão de conclusão.

• Atualizar Status de Demanda: Permite que o gestor altere o status de uma solicitação (Aberto > Triagem > Em Execução > Resolvido > Encerrado) e adicione comentários e o nome do responsável pela atualização.

### **Arquitetura do Projeto**

O projeto segue uma estrutura modular, organizada em pacotes para melhor separação de responsabilidades:

• **com.aep.rotalivre.model:** Contém as classes de modelo de dados, como Solicitacao, Usuario, Prioridade, Status e HistoricoStatus. Estas classes representam as entidades do domínio da aplicação.

• **com.aep.rotalivre.repository:** Define a interface SolicitacaoRepository e sua implementação MemoriaSolicitacaoRepository, responsável pelo armazenamento e recuperação das solicitações.

• **com.aep.rotalivre.service:** Contém a lógica de negócio principal na classe ServicoSolicitacoes, que orquestra as operações de criação, consulta e atualização de solicitações, aplicando as regras de validação.

• **com.aep.rotalivre.ui:** Inclui a classe Main, que é a interface de usuário baseada em console, responsável pela interação com o usuário através de menus e entrada de dados.

### **Como Compilar e Executar**

Este projeto utiliza Apache Maven para gerenciamento de dependências e construção. Siga os passos abaixo para compilar e executar o RotaLivre:

**Pré-requisitos:**

• Java Development Kit (JDK) 17 ou superior instalado.

• Apache Maven instalado.

• Alguma IDEE para executar o projeto (sugestão: **Intellij Idea**)

**> Passos para Execução**

1. *Baixe o projeto*: Faça o download do arquivo ZIP/RAR do projeto e descompacte-o em um diretório de sua preferência.


2. *Abra sua IDEE* de preferência e navegue até a pasta src/main/java/ui dentro do diretório do projeto.


3. *Execute a aplicação* na classe Main diretamente no projeto.

### Exemplo de Uso

Ao iniciar o sistema, você será apresentado ao menu principal:

**ESCOLHA SEU PERFIL**

- [1] Cidadão                                           
- [2] Gestor Público                                    
- [0] Sair

*Digite sua opção:*

- Selecione 1 para o perfil Cidadão e siga as instruções para registrar ou consultar uma solicitação.

- Selecione 2 para o perfil Gestor Público para listar ou atualizar demandas.

### Equipe

Este projeto foi desenvolvido para a AEP ESOFT5NA 2026.1 pelos alunos:

*   Heloísa Sayuri Silva Saito - RA: 24062631-2
*   Maria Eduarda de Castro Lachimia - RA: 24055202-2
*   Matheus Costa E Silva - RA: 24000729-2
