package com.gastei;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GastoService service = new GastoService();
        GastoRepository repository = new GastoRepository();
        service.setGastos(repository.carregar());

        Scanner scanner = new Scanner(System.in);
        System.out.println("=== 💸 GASTEI - Controle de Gastos Pessoais ===");

        boolean rodando = true;
        while (rodando) {
            System.out.println("\n1. Adicionar gasto");
            System.out.println("2. Listar gastos");
            System.out.println("3. Ver total gasto");
            System.out.println("4. Sair");
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
                case "4" -> rodando = false;
                default -> System.out.println("Opção inválida.");
            }
        }
        System.out.println("Até logo!");
    }
}