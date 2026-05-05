# MicroChefs - Sistema de Restaurante com Microserviços

Este projeto modela um sistema de restaurante usando uma arquitetura de microserviços, com comunicação assíncrona via RabbitMQ.

## 📌 Divisão da Equipe

* **Felipe (Usuário + Infra)**
  * Responsável pelo serviço de usuários (cadastro, autenticação, etc.)
  * Gerenciamento do Eureka Server / servidor principal
  * Consumo de eventos (ex: pedido finalizado) para atualizar histórico do usuário

* **Caike (Produtos + Front)**
  * Serviço de produtos (catálogo de itens)
  * Desenvolvimento do front-end (Angular)
  * Comunicação com backend via REST

* **Elcio (Pedidos + Mensageria + Cozinha)**
  * Serviço de pedidos (criação e gerenciamento)
  * Integração com RabbitMQ (producer)
  * Serviço de cozinha (consumer), responsável por processar pedidos
  * Definição das filas, exchanges e padrão das mensagens

---

## 🔄 Fluxo de Mensageria

1. Cliente faz um pedido pelo front.
2. O pedido vai para o serviço de pedidos (REST).
3. Ao criar o pedido, ele publica um evento no RabbitMQ (`pedido.criado`).
4. O serviço da cozinha consome esse evento e começa o preparo.
5. Quando finaliza, publica outro evento (`pedido.finalizado`).
6. O serviço de usuário consome esse evento e atualiza o histórico.

---

## 🎯 Objetivos do Trabalho

* Mostrar comunicação assíncrona real entre serviços.
* Evitar acoplamento direto entre eles.
* Aplicar na prática o uso do RabbitMQ.
* Atender os requisitos do trabalho (produtor, consumidor, fluxo, etc.).

---

## ⚙️ Tecnologias

* **Backend:** Java + Spring Boot
* **Mensageria:** RabbitMQ
* **Infra:** Docker & Eureka Server
* **Banco de Dados:** PostgreSQL
* **Frontend:** Angular

---

## 🚀 Como Rodar o Ambiente Completo

### 1. Clonando o projeto completo

```bash
git clone --recurse-submodules git@github.com:Kreucyu/MicroChefs.git
```

### 2. Subindo a Infraestrutura (Docker)

Toda a infraestrutura (Databases, RabbitMQ, Eureka) e os microserviços Java estão dockerizados. Na raiz do projeto, execute:

```bash
docker-compose up -d --build
```

### 3. Rodando o Serviço de Usuários (.NET)

O `Usuario-Service` é desenvolvido em .NET e deve ser executado manualmente para testes locais:

```bash
cd Usuario-Service
dotnet run
```

### 4. Rodando o Frontend (Angular)

```bash
cd Frontend
npm install
npm start
```

---

## 🔗 Endpoints Úteis

* **Frontend:** `http://localhost:4200`
* **Eureka Dashboard:** `http://localhost:8761`
* **RabbitMQ Management:** `http://localhost:15672` (guest/guest)
* **Produto Service:** `http://localhost:8081`
* **Pedido Service:** `http://localhost:8090`
* **Usuario Service:** `http://localhost:7201`

---

## 🛠️ Novas Funcionalidades (Recentes)

* **Página de Registro:** Agora é possível criar novos usuários diretamente pelo front.
* **Acompanhamento de Pedido:** Tela de tracking real-time para ver o status do pedido (Pago -> Preparando -> Pronto).
* **CORS Habilitado:** Todos os serviços agora aceitam requisições do frontend local.
* **Auto-Queue Creation:** O RabbitMQ cria as filas necessárias (`cozinha-queue`, `pedido-queue`, etc.) automaticamente na subida dos serviços.

Caso já tenha clonado sem submodules:

```bash
git submodule update --init --recursive
```