# Aplicativo de Gerenciamento Financeiro Pessoal

## Descrição

Um aplicativo de gerenciamento financeiro pessoal desenvolvido em Java, utilizando a biblioteca Swing para a interface gráfica e a IDE NetBeans. O objetivo é fornecer uma ferramenta simples e intuitiva para o controle de receitas e despesas.

## Funcionalidades

*   **Cadastro de Receitas:** Permite registrar novas fontes de renda, especificando a descrição, valor e data do recebimento.
*   **Cadastro de Despesas:** Possibilita o registro de despesas, categorizando-as (ex: moradia, alimentação, transporte), com descrição, valor e data.
*   **Listagem de Transações:** Exibe uma lista detalhada de todas as receitas e despesas cadastradas, ordenadas por data ou categoria.

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
│    ├── Main.java                  # Ponto de entrada do sistema
├── resources/                      # Recursos estáticos
│   └── images/                     # Imagens
├── test/                           # Pasta de testes
├── .gitignore                      # Informa ao git quais pastas e arquivos ignorar
├── README.md                       # Documentação
├── database.sqlite                 # Banco de dados local (SQLite)
├── nbactions.xml                   # Arquivo de configuração do NetBeans
└── pom.xml                         # Arquivo de configuração do Maven
```

## Execução

1. Clone o repositório do projeto:
```bash
git clone https://github.com/dariomatias-dev/financial_management.git
```

2 - Compile o projeto para um arquivo `jar`:
```bash
mvn clean install
mvn exec:java -Dexec.mainClass="br.edu.ifpb.aluguel_carros.App"
```

3 - Use o seguinte comando para executar a aplicação:
```bash
java -jar target/financial_management-1.0-SNAPSHOT-jar-with-dependencies.jar
```

## Interface Gráfica

A interface gráfica da aplicação foi desenvolvida utilizando Java Swing, uma biblioteca para criação de interfaces de usuário. O Swing permite a construção de componentes gráficos como botões, campos de texto, tabelas e menus, proporcionando uma experiência fluida e intuitiva para o usuário. Para facilitar o desenvolvimento, a IDE NetBeans foi utilizada, oferecendo ferramentas visuais e suporte a recursos como arrastar e soltar, o que acelera o processo de criação e organização da interface. Com isso, a aplicação garante uma interação eficiente e agradável para o usuário final, com componentes altamente configuráveis e flexíveis.

## Arquitetura

O projeto adota o padrão arquitetural **MVC (Model-View-Controller)**, que promove a separação de responsabilidades, facilitando a manutenção e testabilidade do código, ficando da seguinte maneira:

- **Model**: Responsável pela representação dos dados. Localizadas no diretório `models`, e com prefixo `Model`.
- **View**: Responsável pela apresentação dos dados ao usuário e interação com o sistema através de componentes gráficos (ex: janelas, botões, campos de texto).  As telas da aplicação são construídas utilizando a biblioteca Swing e estão localizadas no diretório `views`, com prefixo `View`.
- **Controller**: Atua como intermediário entre a View e o Model. Recebe as ações que devem ser executadas, como criação, atualização, remoção, acessar tela e dentre outras. Estão presentes nas classes `*Controller` dentro dos pacotes de visualização (e.g. `views/budgets`, `views/expenses`, `views/revenues`), recebendo o prefixo `Controller`.
