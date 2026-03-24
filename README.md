# Projeto Banco sem Spring

Sistema bancário desenvolvido em **Java puro**, sem uso de frameworks, com foco em orientação a objetos, modelagem de domínio e regras de negócio bancárias [page:1][page:2].

## Sobre o projeto

Este projeto simula operações bancárias comuns, como abertura de agência, criação de contas, depósito, saque, transferência entre contas, geração de extrato e execução de pagamentos agendados [page:3].

A proposta é praticar fundamentos importantes de desenvolvimento backend com Java, priorizando organização em pacotes, encapsulamento, herança, polimorfismo e tratamento de exceções [page:1][page:3].

## Funcionalidades

- Criação de banco e agências [page:3]
- Associação de gerente à agência [page:3]
- Abertura de contas bancárias [page:3]
- Suporte a diferentes tipos de conta, como `StandardAccount` e `GoldAccount` [page:3]
- Depósitos e saques [page:1][page:3]
- Transferências entre contas [page:1][page:3]
- Geração de extrato bancário [page:1][page:3]
- Pagamentos agendados com parcelamento [page:3]
- Ordenação de contas por saldo e por nome do titular, conforme a estrutura atual do projeto [page:3]

## Estrutura do projeto

```text
src/main/java/org/thales
├── enums
├── exceptions
├── model
│   ├── accounts
│   ├── employees
│   └── holders
├── payments
└── Main.java
