# <center> Desafio - Programa de Estágio Além do Sistema

## 📁 Estrutura do Projeto

````
    AlemDoSistema/
│-- .idea/               
│-- .mvn/                
│-- src/
│   │-- main/
│   │   │-- java/
│   │   │   │-- br.com.alemdosistema.alemdosistema/
│   │   │       │-- controller/
│   │   │       │-- dto/
│   │   │       │-- enums/
│   │   │       │-- mapper/
│   │   │       │-- model/
│   │   │       │-- repository/
│   │   │       │-- service/
│   │   │       │-- AlemDoSistemaApplication.java
│   │   │-- resources/
│   │       │-- static/
│   │       │   │-- css/
│   │       │   │-- img/
│   │       │   │-- js/
│   │       │-- templates/
│   │       │   │-- fragment/
│   │       │   │   │-- footer.html
│   │       │   │   │-- header.html
│   │       │   │-- editContactModal.html
│   │       │   │-- editModal.html
│   │       │   │-- index.html
│   │       │   │-- modal.html
│   │       │   │-- profile.html
│   │       │-- application.properties
│-- pom.xml               
│-- README.md            
│-- .gitignore       
````
- Este projeto foi desenvolvido e estruturado de acordo com o padrão arquitetural MVC (Model-View-Controller).

### Diretórios principais:

- src/main/java/br.com.alemdosistema.alemdosistema/: Contém todo o código-fonte do backend, organizado por camadas.

- src/main/resources/: Contém os arquivos de configuração, templates HTML, estilos CSS e scripts JavaScript.

### Camadas do Backend:

- controller/: Contém os controladores responsáveis por lidar com as requisições HTTP e redirecionar as respostas para as views.

- dto/: Define os objetos de transferência de dados, usados para comunicar informações entre diferentes camadas.

- enums/: Contém definições de enums usados na aplicação.

- mapper/: Implementa conversões entre entidades do banco e DTOs.

- model/: Define as entidades do sistema, representando tabelas do banco de dados.

- repository/: Contém as interfaces responsáveis pela comunicação com o banco de dados usando Spring Data JPA.

- service/: Implementa a lógica de negócio da aplicação.

### Front-end (Thymeleaf + Recursos Estáticos):

- resources/templates/: Contém os arquivos HTML organizados em páginas e fragmentos reutilizáveis.

- resources/static/css/: Estilos CSS utilizados na aplicação.

- resources/static/js/: Scripts JavaScript auxiliares.

- resources/static/img/: Recursos visuais da aplicação.

### Arquivos de Configuração:

- application.properties: Configuração do banco de dados, servidor e outros parâmetros do Spring Boot.

- pom.xml: Gerenciamento das dependências e configurações do Maven.

## 📦 Dependências

- Jakarta Validation API (3.0.2)

- Spring Boot Starter Data JPA

- Spring Boot Starter Thymeleaf

- Spring Boot Starter Web

- Spring Boot DevTools

- MapStruct (1.5.5.Final)

- PostgreSQL Driver

- Lombok

- Spring Boot Starter Test

## ⚙️ Configurações

1. Instale o Java 21 ou superior.

2. Configure o PostgreSQL com um banco de dados adequado.

3. Configure o application.properties:

````
spring.application.name=AlemDoSistema

spring.datasource.url=jdbc:postgresql://localhost:5432/comercio-sa
spring.datasource.username=
spring.datasource.password=
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update
````

4. Compile e execute com:
````
mvn spring-boot:run
````

## ▶️ Instruções de Uso

1. Inicie o backend

- Via terminal:

````
mvn spring-boot:run
````
- Via IntelliJ IDEA:

  - Abra o projeto no IntelliJ IDEA.

  - Navegue até a classe AlemDoSistemaApplication.

  - Clique com o botão direito e selecione Run AlemDoSistemaApplication.

  - O servidor será iniciado na porta 8080.

2. Acesse a aplicação

   - Abra um navegador e digite o seguinte endereço:
````
http://localhost:8080/
````

3. Funcionalidades disponíveis

- Página inicial: Exibe a listagem de clientes cadastrados, permitindo a busca por nome ou CPF. Também oferece botões para adicionar novos clientes, acessar perfis e excluir cadastros existentes.

- Cadastro de clientes: Permite o registro de novos clientes com todos os dados necessários.

- Busca por Nome ou CPF: Filtra clientes cadastrados com base no nome ou CPF digitado.

- Perfil do cliente: Exibe todas as informações cadastradas do cliente, além de opções para edição e gerenciamento de contatos.

- Excluir cliente: Remove permanentemente um cliente do sistema.

- Adicionar contato: Associa um novo contato ao cliente.

- Editar contato: Modifica os dados de um contato já existente.

- Editar cliente: Permite a atualização completa dos dados do cliente cadastrado.

4. Encerrando o servidor

- Para interromper a execução no terminal, pressione CTRL + C.

- No IntelliJ IDEA, clique no botão Stop na barra de ferramentas.
