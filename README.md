# LiterAlura

Projeto para gerenciar livros e seus autores, desenvolvido com Spring Boot e JPA. Este projeto permite inserir dados de livros e autores, consultar livros, listar autores, e listar autores vivos em um determinado ano.

## Funcionalidades

- Inserir dados de livros e autores.
- Consultar livros.
- Listar autores.
- Listar autores vivos em determinado ano.

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.3.1
- Spring Data JPA
- Hibernate
- PostgreSQL

## Pré-requisitos

- Java 17
- Maven
- PostgreSQL

## Configuração do Banco de Dados

1. Crie um banco de dados no PostgreSQL.
2. Atualize as configurações no arquivo `application.properties` com as credenciais do banco de dados.

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
