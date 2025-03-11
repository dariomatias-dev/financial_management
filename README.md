# Aplicativo de Gerenciamento Financeiro Pessoal

## Descrição

Um aplicativo de gerenciamento financeiro pessoal desenvolvido em Java, utilizando a biblioteca Swing para a interface gráfica e a IDE NetBeans. O objetivo é fornecer uma ferramenta simples e intuitiva para o controle de receitas e despesas.

## Funcionalidades

*   **Receitas:** Permite registrar fontes de renda.
*   **Despesas:** Possibilita o registro de despesas.
*   **Visão Financeira:** Exibe as receitas e despesas organizadas por períodos, para facilitar o acompanhamento financeiro.
*   **Orçamentos:** Auxilia no planejamento de gastos, permitindo a criação e o monitoramento de orçamentos para diferentes áreas ou projetos.

## Tecnologias Utilizadas

* [Java](https://www.java.com/pt-BR/): Linguagem da aplicação
* Swing: Interface gráfica
* [NetBeans](https://netbeans.apache.org/front/main/index.html): IDE de desenvolvimento

## Estrutura

O projeto utiliza o padrão MVC para estruturação das pastas e arquivos, organizados da seguinte forma:

```
financial_management/               # Nome do projeto
├── .idea/                          # Arquivos de configuração
├── lib/                            # Bibliotecas
├── screenshots/                    # Screenshots da aplicação
├── src/                            # Código-fonte
│ └── main/java/org/cafe/           # Pacote base do código-fonte
│    ├── core/                      # Arquivos de configuração
│    │ └── formatters/              # Funções de formatação de dados
│    ├── database/                  # Camada de acesso ao banco de dados
│    │ └── controllers/             # Controladores das entidades do banco de dados
│    ├── models/                    # Classes de modelo que representam as entidades
│    ├── utils/                     # Funções utilitárias
│    ├── views/                     # Componentes da interface gráfica (GUI)
│    │ └── components/              # Componentes da tela
│    └── Main.java                  # Ponto de entrada do sistema
├── resources/                      # Recursos estáticos
│   └── images/                     # Imagens
├── test/                           # Pasta de testes
├── .gitignore                      # Informa ao git quais pastas e arquivos ignorar
├── README.md                       # Documentação
├── database.sqlite                 # Banco de dados local (SQLite)
├── nbactions.xml                   # Arquivo de configuração do NetBeans
└── pom.xml                         # Arquivo de configuração do Maven
```

## Arquitetura

O projeto adota o padrão arquitetural **MVC (Model-View-Controller)**, que promove a separação de responsabilidades, facilitando a manutenção e testabilidade do código, ficando da seguinte maneira:

- **Model**: Responsável pela representação dos dados. Localizadas no diretório `models`, e com prefixo `Model`.
- **View**: Responsável pela apresentação dos dados ao usuário e interação com o sistema através de componentes gráficos (ex: janelas, botões, campos de texto).  As telas da aplicação são construídas utilizando a biblioteca Swing e estão localizadas no diretório `views`, com prefixo `View`.
- **Controller**: Atua como intermediário entre a View e o Model. Recebe as ações que devem ser executadas, como criação, atualização, remoção, acessar tela e dentre outras. Estão presentes nas classes `*Controller` dentro dos pacotes de visualização (e.g. `views/budgets`, `views/expenses`, `views/revenues`), recebendo o prefixo `Controller`.

## Execução

1. Clone o repositório do projeto:
```bash
git clone https://github.com/dariomatias-dev/financial_management.git
```

2 - Compile o projeto para um arquivo `jar`:
```bash
mvn clean package
```

3 - Use o seguinte comando para executar a aplicação:
```bash
java -jar target/financial_management-1.0-SNAPSHOT-jar-with-dependencies.jar
```

## Interface Gráfica

A interface gráfica da aplicação foi desenvolvida utilizando Java Swing, uma biblioteca para criação de interfaces de usuário. O Swing permite a construção de componentes gráficos como botões, campos de texto, tabelas e menus, proporcionando uma experiência fluida e intuitiva para o usuário. Para facilitar o desenvolvimento, a IDE NetBeans foi utilizada, oferecendo ferramentas visuais e suporte a recursos como arrastar e soltar, o que acelera o processo de criação e organização da interface. Com isso, a aplicação garante uma interação eficiente e agradável para o usuário final, com componentes altamente configuráveis e flexíveis.

### Telas

- **Tela Principal:**

![Main View](screenshots/main_view_screenshot.png)

- **Tela de Receitas:**

    Filtros: Texto, valor mínimo e máximo, e período.

![Revenues View](screenshots/revenues_view_screenshot.png)

Criar Receita:

![Manager Revenue View](screenshots/manager_revenue_view_screenshot.png)

- **Tela de Despesas:**

    Filtros: Texto, valor mínimo e máximo, e período.

![Expenses](screenshots/expenses_view_screenshot.png)

Criar Despesa:

![Manager Expense View](screenshots/manager_expense_view_screenshot.png)

- **Tela de Visão Financeira:**

![Financial Overview View](screenshots/financial_overview_view_screenshot.png)

- **Tela de Orçamentos:**

    Filtros: Texto, status, valor mínimo e máximo, e data inicial e final.

![Budgets View](screenshots/budgets_view_screenshot.png)

Criar Orçamento:

![Manager Budget View](screenshots/manager_budget_view_screenshot.png)

- **Tela de Orçamento:**

![Budget View](screenshots/budget_view_1_screenshot.png)

![Budget View](screenshots/budget_view_2_screenshot.png)

Criar Item de Orçamento:

![Manager Budget Item View](screenshots/manager_budget_item_view_screenshot.png)

<br/>

### Diálogos

- **Tabela está vazia (mesmo aviso para remoção):**

![Empty Expenses View](screenshots/empty_revenues_view_screenshot.png)

![No Records Alert Dialog](screenshots/no_records_alert_dialog_screenshot.png)

- **Confirmar remoção de registro:**

![Removal Confirmation Alert Dialog](screenshots/removal_confirmation_alert_dialog_screenshot.png)

- **Nenhum registro foi selecionado (mesmo aviso para remoção):**

![No Record Selected Revenues View](screenshots/revenues_view_screenshot.png)

![No Records Alert Dialog](screenshots/no_records_alert_dialog_screenshot.png)

- **Algum ou todos os campos não foram preenchidos:**

![Empty Manager Revenue View](screenshots/empty_manager_revenue_view_screenshot.png)

![Fill All Fields Error Dialog](screenshots/fill_all_fields_error_dialog_screenshot.png)

- **Inserido um valor númerico inválido, como caracteres não númericos:**

![Invalid Value Manager Revenue View](screenshots/invalid_value_manager_revenue_view_screenshot.png)

![Value Number Valid Error Dialog](screenshots/value_number_valid_error_dialog_screenshot.png)

- **Data inicial posterior a data final:**

![Initial Date After The Final Manager Budget View](screenshots/initial_date_after_the_final_manager_budget_view_screenshot.png)

![Initial Date After The Final Error Dialog](screenshots/initial_date_after_the_final_error_dialog_screenshot.png)

## Autores

Projeto desenvolvido por [Dário Matias Sales](https://github.com/dariomatias-dev), [Dario Arthur Moura Ramos](https://github.com/Dario-Arthur) e [Arthur dos Santos Lima](https://github.com/Fastusx).
