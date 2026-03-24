# Projeto Banco sem Spring

Sistema bancário desenvolvido em **Java puro**, sem uso de frameworks, com foco em orientação a objetos, modelagem de domínio e regras de negócio bancárias.

## Sobre o projeto

Este projeto simula operações bancárias comuns, como abertura de agência, criação de contas, depósito, saque, transferência entre contas, geração de extrato e execução de pagamentos agendados.

A proposta é praticar fundamentos importantes de desenvolvimento backend com Java, priorizando organização em pacotes, encapsulamento, herança, polimorfismo e tratamento de exceções.

## Funcionalidades

- Criação de banco e agências
- Associação de gerente à agência 
- Abertura de contas bancárias
- Suporte a diferentes tipos de conta, como `StandardAccount` e `GoldAccount` 
- Depósitos e saques 
- Transferências entre contas
- Geração de extrato bancário 
- Pagamentos agendados com parcelamento
- Ordenação de contas por saldo e por nome do titular, conforme a estrutura atual do projeto 

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
