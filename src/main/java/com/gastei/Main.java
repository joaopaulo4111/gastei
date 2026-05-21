package com.gastei;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GastoService service = new GastoService();
        GastoRepository repository = new GastoRepository();
        service.setGastos(repository.carregar());

        LimiteCategoriaRepository limiteRepository = new LimiteCategoriaRepository();
        LimiteCategoriaService limiteService = new LimiteCategoriaService(limiteRepository, repository);

        Scanner scanner = new Scanner(System.in);
        System.out.println("=== 💸 GASTEI - Controle de Gastos Pessoais ===");

        CotacaoService cotacaoService = new CotacaoService();
        System.out.println("Buscando cotação do dólar...");
        System.out.println("💵 Cotação atual: " + cotacaoService.buscarCotacaoDolar());

        boolean rodando = true;
        while (rodando) {
            System.out.println("\n1. Adicionar gasto");
            System.out.println("2. Listar gastos");
            System.out.println("3. Ver total gasto");
            System.out.println("4. Gerenciar limites por categoria");
            System.out.println("5. Sair");
            System.out.print("Escolha: ");
            String opcao = scanner.nextLine().trim();

            switch (opcao) {
                case "1" -> {
                    System.out.print("Descrição: ");
                    String desc = scanner.nextLine();
                    System.out.print("Valor (ex: 25.90): ");
                    double valor;
                    try {
                        valor = Double.parseDouble(scanner.nextLine().replace(",", "."));
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Valor inválido.");
                        break;
                    }
                    System.out.print("Categoria (ex: Alimentação, Transporte): ");
                    String cat = scanner.nextLine();
                    String data = LocalDate.now().toString();
                    try {
                        service.adicionar(desc, valor, cat, data);
                        repository.salvar(service.listar());
                        System.out.println("✅ Gasto adicionado!");

                        // Alerta de limite após adicionar
                        String alerta = limiteService.verificarAlerta(cat);
                        if ("CRITICO".equals(alerta)) {
                            System.out.println("🔴 ATENÇÃO: Limite da categoria '" + cat + "' foi atingido!");
                        } else if ("AVISO".equals(alerta)) {
                            System.out.println("🟡 AVISO: Você já usou 80% do limite de '" + cat + "'.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("❌ " + e.getMessage());
                    }
                }
                case "2" -> {
                    List<Gasto> lista = service.listar();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhum gasto registrado.");
                    } else {
                        lista.forEach(System.out::println);
                    }
                }
                case "3" -> System.out.printf("💰 Total gasto: R$ %.2f%n", service.totalGasto());
                case "4" -> {
                    System.out.println("\n=== 🎯 LIMITES POR CATEGORIA ===");
                    System.out.println("1. Definir/atualizar limite");
                    System.out.println("2. Remover limite");
                    System.out.println("3. Ver resumo de alertas");
                    System.out.println("4. Voltar");
                    System.out.print("Escolha: ");
                    String sub = scanner.nextLine().trim();
                    switch (sub) {
                        case "1" -> {
                            System.out.print("Categoria: ");
                            String lCat = scanner.nextLine();
                            System.out.print("Limite mensal (R$): ");
                            try {
                                double lVal = Double.parseDouble(scanner.nextLine().replace(",", "."));
                                limiteService.definirLimite(lCat, lVal);
                                System.out.println("✅ Limite salvo para '" + lCat + "'.");
                            } catch (NumberFormatException e) {
                                System.out.println("❌ Valor inválido.");
                            } catch (IllegalArgumentException e) {
                                System.out.println("❌ " + e.getMessage());
                            }
                        }
                        case "2" -> {
                            System.out.print("Categoria: ");
                            String lCat = scanner.nextLine();
                            limiteService.removerLimite(lCat);
                            System.out.println("✅ Limite removido.");
                        }
                        case "3" -> limiteService.exibirResumoAlertas();
                        default -> { }
                    }
                }
                case "5" -> rodando = false;
                default -> System.out.println("Opção inválida.");
            }
        }
        System.out.println("Até logo!");
    }
}