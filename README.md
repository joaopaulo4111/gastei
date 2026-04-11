# 💸 Gastei — Controle de Gastos Pessoais

![CI](https://github.com/joaopaulo4111/gastei/actions/workflows/ci.yml/badge.svg)

## 📋 Problema Real
Muitas pessoas têm dificuldade em controlar seus gastos diários, o que leva a 
desequilíbrios financeiros ao final do mês.

## 💡 Solução
Aplicação CLI simples que permite registrar, listar e totalizar gastos pessoais, 
salvando os dados localmente em JSON.

## 👥 Público-alvo
Qualquer pessoa que queira controlar gastos de forma rápida e sem complicação.

## ✅ Funcionalidades
- Adicionar gasto (descrição, valor, categoria)
- Listar todos os gastos
- Ver o total gasto
- Persistência local em arquivo JSON

## 🛠️ Tecnologias
- Java 17
- Maven 3.9+
- Gson 2.10.1
- JUnit Jupiter 5.10.0
- Checkstyle (linting)
- GitHub Actions (CI)

## ▶️ Como executar

```bash
# Clonar o repositório
git clone https://github.com/joaopaulo4111/gastei.git
cd gastei

# Compilar
mvn compile

# Executar
mvn exec:java -Dexec.mainClass="com.gastei.Main"
```

## 🧪 Rodar testes

```bash
mvn test
```

## 🔍 Rodar lint (Checkstyle)

```bash
mvn checkstyle:check
```

## 📦 Versão
1.0.0

## 👤 Autor
João Paulo Castro dos Santos
