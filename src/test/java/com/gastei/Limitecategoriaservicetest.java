package com.gastei;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LimiteCategoriaServiceTest {

  private LimiteCategoriaService service;
  private FakeLimiteCategoriaRepository limiteRepo;
  private FakeGastoRepository gastoRepo;

  @BeforeEach
  void setUp() {
    limiteRepo = new FakeLimiteCategoriaRepository();
    gastoRepo = new FakeGastoRepository();
    service = new LimiteCategoriaService(limiteRepo, gastoRepo);
  }

  @Test
  @DisplayName("Deve lançar exceção ao definir limite com valor zero ou negativo")
  void definirLimiteInvalido() {
    assertThrows(IllegalArgumentException.class,
        () -> service.definirLimite("Alimentação", 0));
    assertThrows(IllegalArgumentException.class,
        () -> service.definirLimite("Alimentação", -100));
  }

  @Test
  @DisplayName("Deve retornar OK quando gasto está abaixo de 80% do limite")
  void alertaOkAbaixo80() {
    limiteRepo.setLimites(List.of(new LimiteCategoria("Alimentação", 1000.0)));
    gastoRepo.setGastos(List.of(
        new Gasto("Mercado", 700.0, "Alimentação", "2026-04-01")
    ));

    assertEquals("OK", service.verificarAlerta("Alimentação"));
  }

  @Test
  @DisplayName("Deve retornar AVISO quando gasto atinge ou ultrapassa 80% do limite")
  void alertaAvisoAos80Porcento() {
    limiteRepo.setLimites(List.of(new LimiteCategoria("Alimentação", 1000.0)));
    gastoRepo.setGastos(List.of(
        new Gasto("Mercado", 800.0, "Alimentação", "2026-04-01")
    ));

    assertEquals("AVISO", service.verificarAlerta("Alimentação"));
  }

  @Test
  @DisplayName("Deve retornar CRITICO quando gasto atinge 100% do limite")
  void alertaCriticoAos100Porcento() {
    limiteRepo.setLimites(List.of(new LimiteCategoria("Alimentação", 1000.0)));
    gastoRepo.setGastos(List.of(
        new Gasto("Mercado", 500.0, "Alimentação", "2026-04-01"),
        new Gasto("Restaurante", 500.0, "Alimentação", "2026-04-02")
    ));

    assertEquals("CRITICO", service.verificarAlerta("Alimentação"));
  }

  @Test
  @DisplayName("Deve retornar CRITICO quando gasto ultrapassa 100% do limite")
  void alertaCriticoAcimaDe100Porcento() {
    limiteRepo.setLimites(List.of(new LimiteCategoria("Eletrônico", 500.0)));
    gastoRepo.setGastos(List.of(
        new Gasto("Notebook", 1500.0, "Eletrônico", "2026-04-01")
    ));

    assertEquals("CRITICO", service.verificarAlerta("Eletrônico"));
  }

  @Test
  @DisplayName("Deve retornar SEM_LIMITE para categoria sem limite definido")
  void semLimiteDefinido() {
    limiteRepo.setLimites(Collections.emptyList());
    gastoRepo.setGastos(List.of(
        new Gasto("Passagem", 50.0, "Transporte", "2026-04-01")
    ));

    assertEquals("SEM_LIMITE", service.verificarAlerta("Transporte"));
  }

  @Test
  @DisplayName("Deve calcular percentual corretamente")
  void calcularPercentualCorreto() {
    limiteRepo.setLimites(List.of(new LimiteCategoria("Lazer", 200.0)));
    gastoRepo.setGastos(List.of(
        new Gasto("Cinema", 50.0, "Lazer", "2026-04-01"),
        new Gasto("Show", 50.0, "Lazer", "2026-04-02")
    ));

    double percentual = service.calcularPercentual("Lazer");
    assertEquals(50.0, percentual, 0.01);
  }

  @Test
  @DisplayName("Deve retornar zero para categoria sem gastos")
  void percentualZeroSemGastos() {
    limiteRepo.setLimites(List.of(new LimiteCategoria("Viagem", 3000.0)));
    gastoRepo.setGastos(Collections.emptyList());

    assertEquals(0.0, service.calcularPercentual("Viagem"), 0.01);
  }

  // ── Fakes ────────────────────────────────────────────────────────────────

  static class FakeLimiteCategoriaRepository extends LimiteCategoriaRepository {

    private List<LimiteCategoria> limites = Collections.emptyList();

    void setLimites(List<LimiteCategoria> limites) {
      this.limites = limites;
    }

    @Override
    public List<LimiteCategoria> listarTodos() {
      return limites;
    }

    @Override
    public void salvar(List<LimiteCategoria> limites) {
      this.limites = limites;
    }

    @Override
    public void definirLimite(String categoria, double valor) {
      java.util.List<LimiteCategoria> lista = new java.util.ArrayList<>(limites);
      lista.removeIf(l -> l.getCategoria().equalsIgnoreCase(categoria));
      lista.add(new LimiteCategoria(categoria, valor));
      this.limites = lista;
    }
  }

  static class FakeGastoRepository extends GastoRepository {

    private List<Gasto> gastos = Collections.emptyList();

    void setGastos(List<Gasto> gastos) {
      this.gastos = gastos;
    }

    @Override
    public List<Gasto> listarTodos() {
      return gastos;
    }
  }
}