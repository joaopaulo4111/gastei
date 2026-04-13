# 💸 Gastei — Controle de Gastos Pessoais

![CI](https://github.com/joaopaulo4111/gastei/actions/workflows/ci.yml/badge.svg)
[![Deploy](https://img.shields.io/badge/deploy-GitHub%20Pages-blue)](https://joaopaulo4111.github.io/gastei)

🌐 **Aplicação publicada:** https://joaopaulo4111.github.io/gastei

## 📋 Problema Real
Muitas pessoas têm dificuldade em controlar seus gastos diários, o que leva a
desequilíbrios financeiros ao final do mês. A falta de registro simples e acessível
contribui para o endividamento e a desorganização financeira.

## 💡 Solução
Aplicação web com interface moderna que permite registrar, categorizar e totalizar
gastos pessoais por forma de pagamento, com suporte a parcelamento no crédito e
visualização da fatura mensal. Os dados são salvos em banco de dados na nuvem (Supabase).

## 👥 Público-alvo
Qualquer pessoa que queira controlar gastos de forma rápida e sem complicação,
especialmente jovens e estudantes que estão aprendendo a organizar as finanças.

## ✅ Funcionalidades
- Adicionar gastos com descrição, valor e categoria
- Separação por forma de pagamento: Crédito, Débito, PIX e Dinheiro
- Parcelamento no crédito com lançamento automático mês a mês
- Visualização da fatura mensal do cartão de crédito
- Resumo dos gastos por tipo de pagamento
- Cotação do dólar em tempo real via AwesomeAPI
- Persistência de dados em banco de dados PostgreSQL na nuvem (Supabase)
- Interface web moderna e responsiva

## 🛠️ Tecnologias
- Java 21
- Maven 3.9+
- Gson 2.10.1
- JUnit Jupiter 5.10.0
- Checkstyle (linting)
- GitHub Actions (CI)
- Supabase (PostgreSQL na nuvem)
- HTML / CSS / JavaScript (interface web)
- AwesomeAPI (cotação de moedas)
- GitHub Pages (deploy)

## ⚙️ Pré-requisitos
- Java 21 instalado
- Maven 3.9+ instalado

## 📦 Instalação

```bash
git clone https://github.com/joaopaulo4111/gastei.git
cd gastei
mvn compile
```

## ▶️ Como executar (CLI)

```bash
mvn exec:java "-Dexec.mainClass=com.gastei.Main"
```

## 🌐 Como usar (Interface Web)

Acesse diretamente pelo navegador:
👉 https://joaopaulo4111.github.io/gastei

## 🧪 Rodar os testes

```bash
mvn test
```

## 🔍 Rodar o lint (Checkstyle)

```bash
mvn checkstyle:check
```

## 🗄️ Banco de Dados
O projeto utiliza **Supabase** (PostgreSQL na nuvem) para persistência dos dados.
A tabela `gastos` armazena descrição, valor, categoria, tipo de pagamento,
parcelas e mês de referência.

## 👥 Integrantes
| Nome | GitHub |
|------|-------|
| João Paulo Castro dos Santos | [@joaopaulo4111](https://github.com/joaopaulo4111) |
| Lucas Gabriel Castro dos Santos | [@lucas-castro5](https://github.com/lucas-castro5) |

## 📌 Versão
1.0.1

## 🔗 Repositório
https://github.com/joaopaulo4111/gastei