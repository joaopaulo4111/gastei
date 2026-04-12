# 💸 Gastei — Controle de Gastos Pessoais

[![CI](https://github.com/joaopaulo4111/gastei/actions/workflows/ci.yml/badge.svg)](https://github.com/joaopaulo4111/gastei/actions/workflows/ci.yml)
[![Deploy](https://img.shields.io/badge/deploy-GitHub%20Pages-blue)](https://joaopaulo4111.github.io/gastei)

🌐 **Aplicação publicada:** <https://joaopaulo4111.github.io/gastei>

---

## 📋 Problema Real

Muitas pessoas têm dificuldade em controlar seus gastos diários, o que leva a
desequilíbrios financeiros ao final do mês. A falta de registro simples e acessível
contribui para o endividamento e a desorganização financeira.

## 💡 Solução

Aplicação web que permite registrar, listar e totalizar gastos pessoais com suporte
a múltiplas formas de pagamento, parcelamento e persistência na nuvem via Supabase.

## 👥 Público-alvo

Qualquer pessoa que queira controlar gastos de forma rápida e sem complicação,
especialmente jovens e estudantes que estão aprendendo a organizar as finanças.

---

## ✅ Funcionalidades

- Adicionar gasto (descrição, valor, categoria, forma de pagamento)
- Separação por tipo de pagamento: crédito, débito, PIX e dinheiro
- Simulação de parcelamento no crédito
- Fatura mensal do cartão de crédito
- Listar e deletar gastos registrados
- Ver o total gasto
- Cotação do dólar em tempo real
- Persistência na nuvem com Supabase (PostgreSQL)

---

## 🛠️ Tecnologias

### Backend / Persistência
- **Supabase** (PostgreSQL na nuvem)

### Frontend / Interface Web
- HTML5, CSS3, JavaScript (ES6+)

### Backend legado (CLI)
- Java 21
- Maven 3.9+
- Gson 2.10.1
- JUnit Jupiter 5.10.0
- Checkstyle (linting)

### Infraestrutura
- GitHub Actions (CI)
- GitHub Pages (deploy)

---

## ⚙️ Pré-requisitos

Para rodar a interface web localmente, basta um navegador moderno. Para o módulo Java:

- Java 21 instalado
- Maven 3.9+ instalado

---

## 📦 Instalação

```bash
git clone https://github.com/joaopaulo4111/gastei.git
cd gastei
```

Para a interface web, abra `docs/index.html` no navegador ou acesse o deploy:
<https://joaopaulo4111.github.io/gastei>

Para o módulo Java (CLI):

```bash
mvn compile
```

## ▶️ Como executar (CLI)

```bash
mvn exec:java "-Dexec.mainClass=com.gastei.Main"
```

## 🧪 Rodar os testes

```bash
mvn test
```

## 🔍 Rodar o lint (Checkstyle)

```bash
mvn checkstyle:check
```

---

## 📸 Evidências de Funcionamento

### ▶️ Aplicação rodando

![Aplicação rodando](img.png)

### ✅ Testes passando

![Testes passando](img_1.png)

---

## 👥 Integrantes

| Nome | GitHub |
|------|--------|
| João Paulo Castro dos Santos | [@joaopaulo4111](https://github.com/joaopaulo4111) |
| Lucas Gabriel Castro dos Santos | [@lucas-castro5](https://github.com/lucas-castro5) |

---

## 📌 Versão

1.1.0

## 🔗 Repositório

<https://github.com/joaopaulo4111/gastei>

## 📄 Licença

Distribuído sob a licença MIT. Veja [LICENSE](LICENSE) para mais detalhes.
