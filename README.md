<div align="center">

# Wallet Manager Backend

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-green)
![JWT](https://img.shields.io/badge/Auth-JWT-blue)
![Database](https://img.shields.io/badge/H2-Database-lightgrey)

API REST desenvolvida com Spring Boot para gerenciamento financeiro pessoal.

</div>

---

# Wallet Manager Backend

Sistema backend para controle financeiro pessoal com autenticação, categorias e transações.

---

# Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Security
- JWT (Authentication & Authorization)
- Spring Data JPA / Hibernate
- H2 Database (desenvolvimento)
- Maven
- Lombok

---

# Funcionalidades

## Autenticação
- Registro de usuário
- Login com JWT
- Controle de roles (USER / ADMIN)

## Usuários
- Criar usuário
- Buscar usuário por ID
- Listar usuários (ADMIN)
- Atualizar usuário
- Soft delete

## Categorias
- Criar categorias
- Listar categorias
- Atualizar categorias
- Deletar categorias
- Controle de acesso por dono ou ADMIN

## Transações
- Criar transações
- Listar transações
- Atualizar transações
- Soft delete
- Resumo por período (start/end)
- Relacionamento com usuário e categoria

---

# Segurança

- Spring Security
- JWT Authentication
- Controle de roles
- Controle de acesso por proprietário (ownership)

---

# Endpoints principais

## Auth
- POST /auth/register
- POST /auth/login

## Users
- GET /users/{id}
- GET /users
- POST /users
- PUT /users/{id}
- PATCH /users/{id}/toggle

## Categories
- GET /categories
- POST /categories
- PUT /categories/{id}
- DELETE /categories/{id}

## Transactions
- GET /transactions
- POST /transactions
- PUT /transactions/{id}
- PATCH /transactions/{id}/toggle
- GET /transactions/summary

---

# Como executar o projeto

## Pré-requisitos
- Java 17 instalado
---

## Clonar o repositório

```bash
git clone https://github.com/IgorDv5/WalletManager-Backend.git
```
## Rodar o projeto

### Windows
```bash
mvnw.cmd spring-boot:run
```

### Linux / Mac
```bash
./mvnw spring-boot:run
```

