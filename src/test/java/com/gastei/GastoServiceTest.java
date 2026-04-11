package com.gastei;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GastoServiceTest {
    private GastoService service;

    @BeforeEach
    void setUp() {
        service = new GastoService();
    }

    @Test
    void deveAdicionarGastoComSucesso() {
        service.adicionar("Almoço", 25.0, "Alimentação", "2024-01-01");
        assertEquals(1, service.listar().size());
        assertEquals("Almoço", service.listar().get(0).getDescricao());
    }

    @Test
    void deveRejeitarValorNegativo() {
        assertThrows(IllegalArgumentException.class, () ->
                service.adicionar("Teste", -10.0, "Outros", "2024-01-01")
        );
    }

    @Test
    void deveRejeitarDescricaoVazia() {
        assertThrows(IllegalArgumentException.class, () ->
                service.adicionar("", 50.0, "Outros", "2024-01-01")
        );
    }

    @Test
    void deveCalcularTotalCorretamente() {
        service.adicionar("Item 1", 30.0, "A", "2024-01-01");
        service.adicionar("Item 2", 20.0, "B", "2024-01-01");
        assertEquals(50.0, service.totalGasto(), 0.001);
    }

    @Test
    void deveRetornarZeroQuandoSemGastos() {
        assertEquals(0.0, service.totalGasto(), 0.001);
    }
}