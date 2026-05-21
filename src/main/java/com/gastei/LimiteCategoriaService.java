package com.gastei;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LimiteCategoriaService {

  public static final int ALERTA_CRITICO = 100;
  public static final int ALERTA_AVISO = 80;

  private final LimiteCategoriaRepository limiteRepository;
  private final GastoRepository gastoRepository;

  public LimiteCategoriaService(
      LimiteCategoriaRepository limiteRepository,
      GastoRepository gastoRepository) {
    this.limiteRepository = limiteRepository;
    this.gastoRepository = gastoRepository;
  }

  public void definirLimite(String categoria, double valor) {
    if (valor <= 0) {
      throw new IllegalArgumentException("O limite deve ser maior que zero.");
    }
    limiteRepository.definirLimite(categoria, valor);
  }

  public void removerLimite(String categoria) {
    limiteRepository.removerLimite(categoria);
  }

  public List<LimiteCategoria> listarLimites() {
    return limiteRepository.listarTodos();
  }

  /**
   * Calcula o percentual gasto em relação ao limite definido para uma categoria.
   *
   * @param categoria nome da categoria
   * @return percentual de 0 a N (pode ultrapassar 100)
   */
  public double calcularPercentual(String categoria) {
    List<LimiteCategoria> limites = limiteRepository.listarTodos();
    double limite = limites.stream()
        .filter(l -> l.getCategoria().equalsIgnoreCase(categoria))
        .mapToDouble(LimiteCategoria::getLimite)
        .findFirst()
        .orElse(0);

    if (limite <= 0) {
      return 0;
    }

    double totalGasto = gastoRepository.listarTodos().stream()
        .filter(g -> g.getCategoria().equalsIgnoreCase(categoria))
        .mapToDouble(Gasto::getValor)
        .sum();

    return (totalGasto / limite) * 100;
  }

  /**
   * Retorna o nível de alerta para uma categoria.
   *
   * @param categoria nome da categoria
   * @return "CRITICO" se >= 100%, "AVISO" se >= 80%, "OK" caso contrário, "SEM_LIMITE" se não há limite
   */
  public String verificarAlerta(String categoria) {
    List<LimiteCategoria> limites = limiteRepository.listarTodos();
    boolean temLimite = limites.stream()
        .anyMatch(l -> l.getCategoria().equalsIgnoreCase(categoria));

    if (!temLimite) {
      return "SEM_LIMITE";
    }

    double percentual = calcularPercentual(categoria);
    if (percentual >= ALERTA_CRITICO) {
      return "CRITICO";
    } else if (percentual >= ALERTA_AVISO) {
      return "AVISO";
    }
    return "OK";
  }

  /**
   * Exibe no console um resumo de todos os limites com alertas.
   */
  public void exibirResumoAlertas() {
    List<LimiteCategoria> limites = limiteRepository.listarTodos();

    if (limites.isEmpty()) {
      System.out.println("Nenhum limite definido.");
      return;
    }

    Map<String, Double> gastosPorCategoria = gastoRepository.listarTodos().stream()
        .collect(Collectors.groupingBy(
            Gasto::getCategoria,
            Collectors.summingDouble(Gasto::getValor)
        ));

    System.out.println("\n=== LIMITES POR CATEGORIA ===");
    for (LimiteCategoria lc : limites) {
      double gasto = gastosPorCategoria.getOrDefault(lc.getCategoria(), 0.0);
      double percentual = (gasto / lc.getLimite()) * 100;
      String alerta = verificarAlerta(lc.getCategoria());
      String barra = gerarBarra(percentual);

      System.out.printf(
          "%-20s R$ %8.2f / R$ %8.2f  %s  [%s] %s%n",
          lc.getCategoria(),
          gasto,
          lc.getLimite(),
          barra,
          formatarPercentual(percentual),
          formatarAlerta(alerta)
      );
    }
    System.out.println();
  }

  private String gerarBarra(double percentual) {
    int preenchimento = (int) Math.min(percentual / 10, 10);
    StringBuilder barra = new StringBuilder("[");
    for (int i = 0; i < 10; i++) {
      barra.append(i < preenchimento ? "█" : "░");
    }
    barra.append("]");
    return barra.toString();
  }

  private String formatarPercentual(double percentual) {
    return String.format("%5.1f%%", Math.min(percentual, 999.9));
  }

  private String formatarAlerta(String alerta) {
    return switch (alerta) {
      case "CRITICO" -> "🔴 LIMITE ATINGIDO";
      case "AVISO"   -> "🟡 ATENÇÃO: 80% atingido";
      default        -> "🟢 OK";
    };
  }
}