<div align="center">

# 💸 Gastei

**Controle de gastos pessoais simples, rápido e sem complicação.**

[![CI](https://github.com/joaopaulo4111/gastei/actions/workflows/ci.yml/badge.svg)](https://github.com/joaopaulo4111/gastei/actions)
[![Deploy](https://img.shields.io/badge/deploy-GitHub%20Pages-0075FF?logo=github)](https://joaopaulo4111.github.io/gastei)
[![Java](https://img.shields.io/badge/Java-21-ED8B00?logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-3.9+-C71A36?logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![Version](https://img.shields.io/badge/versão-1.0.1-brightgreen)](https://github.com/joaopaulo4111/gastei/releases)

🌐 **[Acesse a aplicação →](https://joaopaulo4111.github.io/gastei)**

</div>

---

## 📋 Sobre o projeto

Muitas pessoas têm dificuldade em acompanhar seus gastos do dia a dia, o que leva a desequilíbrios financeiros no fim do mês. O **Gastei** resolve isso com uma interface web moderna e intuitiva que permite registrar, categorizar e visualizar gastos pessoais em segundos — sem cadastros complicados.

Os dados ficam persistidos em banco de dados na nuvem (Supabase/PostgreSQL), acessíveis de qualquer dispositivo.

---

## ✅ Funcionalidades

- **Registro de gastos** com descrição, valor e categoria
- **Múltiplas formas de pagamento:** Crédito, Débito, PIX e Dinheiro
- **Parcelamento no crédito** com lançamento automático mês a mês
- **Fatura mensal** do cartão de crédito
- **Resumo consolidado** de gastos por tipo de pagamento
- **Cotação do dólar em tempo real** via AwesomeAPI
- **Persistência em nuvem** com banco PostgreSQL (Supabase)
- **Interface responsiva** — funciona no celular e no desktop

---

## 🛠️ Tecnologias

| Camada | Tecnologias |
|--------|------------|
| Back-end | Java 21, Maven 3.9+, Gson 2.10.1 |
| Front-end | HTML, CSS, JavaScript |
| Banco de dados | Supabase (PostgreSQL na nuvem) |
| Testes | JUnit Jupiter 5.10.0 |
| Qualidade | Checkstyle (linting) |
| CI/CD | GitHub Actions, GitHub Pages |
| API externa | AwesomeAPI (cotação de moedas) |

---

## ⚙️ Pré-requisitos

- [Java 21](https://www.oracle.com/java/technologies/downloads/)
- [Maven 3.9+](https://maven.apache.org/download.cgi)

---

## 🚀 Como rodar localmente

**1. Clone o repositório**
```bash
git clone https://github.com/joaopaulo4111/gastei.git
cd gastei
```

**2. Compile o projeto**
```bash
mvn compile
```

**3. Execute a aplicação (CLI)**
```bash
mvn exec:java "-Dexec.mainClass=com.gastei.Main"
```

> Prefere usar pelo navegador? Acesse direto em **[joaopaulo4111.github.io/gastei](https://joaopaulo4111.github.io/gastei)** — sem instalar nada.

---

## 🧪 Testes e qualidade

**Rodar os testes:**
```bash
mvn test
```

**Rodar o lint (Checkstyle):**
```bash
mvn checkstyle:check
```

---

## 🗄️ Banco de dados

O projeto utiliza **[Supabase](https://supabase.com/)** (PostgreSQL gerenciado na nuvem) para persistência dos dados. A tabela `gastos` armazena:

| Campo | Descrição |
|-------|-----------|
| `descricao` | Nome/descrição do gasto |
| `valor` | Valor em reais |
| `categoria` | Categoria do gasto |
| `tipo_pagamento` | Crédito, Débito, PIX ou Dinheiro |
| `parcelas` | Número de parcelas (crédito) |
| `mes_referencia` | Mês de competência do lançamento |

---

## 👥 Equipe

| Nome | GitHub |
|------|--------|
| João Paulo Castro dos Santos | [@joaopaulo4111](https://github.com/joaopaulo4111) |
| Lucas Gabriel Castro dos Santos | [@lucas-castro5](https://github.com/lucas-castro5) |

---

## 📄 Licença

Este projeto está sob licença MIT. Consulte o arquivo [LICENSE](LICENSE) para mais detalhes.
