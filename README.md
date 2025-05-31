# Gerenciador de Tarefas

- Disciplina: Engenharia de Software - Arquitetura e Padrões
- Prof: Cassia Pereira Nino
- Curso: Ciência da Computação
- Faculdade: Universidade do Vale do Rio dos Sinos - UNISINOS
- Grupo: **Quimera**

## Integrantes

- Patrícia Nagel
- Clara Burghardt
- Cássio Braga

## Objetivo

O objetivo deste trabalho é que os alunos projetem, implementem e documentem uma API RESTful 
utilizando boas práticas de Arquitetura de Software.

O projeto deve ser realizado em grupos de até 3 pessoas e contemplar aspectos como modularização, 
padrões arquiteturais, testes automatizados e documentação adequada.

## Descrição do Trabalho

Os grupos deverão desenvolver uma API para um sistema de gestão de tarefas colaborativas, permitindo 
que usuários criem, editem, atribuam e concluam tarefas. A API deve seguir uma arquitetura bem 
definida, garantindo boas práticas de desacoplamento e modularização. A escolha por um padrão de 
arquitetura fica à cargo do grupo.

Além da implementação, é necessário entregar uma documentação técnica detalhando as decisões 
arquiteturais e um conjunto de testes automatizados para garantir a confiabilidade da API. 

## Requisitos Funcionais

A API deve expor os seguintes endpoints:

**_Usuários_**

- POST /users  
  - Criar um novo usuário.  
- GET /users/{id}
  - Obter informações de um usuário específico.  
- PUT /users/{id}
  - Atualizar informações do usuário.  
- DELETE /users/{id}
  - Remover um usuário (soft delete recomendado).
 
**_Tarefas_**

- POST /tasks
  - Criar uma nova tarefa.  
- GET /tasks/{id}
  - Obter detalhes de uma tarefa.  
- GET /tasks?assignedTo={userId}
  - Listar todas as tarefas atribuídas a um usuário.  
- PUT /tasks/{id}
  - Atualizar informações da tarefa (título, descrição, status).  
- DELETE /tasks/{id}
  - Remover uma tarefa. 

**_Autenticação_**

- POST /auth/login  
  - Login de usuários, retornando um token (por exemplo: JWT) para autenticação nas demais requisições.  
- POST /auth/logout
  - Logout do usuário.
 
