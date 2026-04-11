package com.gastei;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CotacaoServiceIntegrationTest {

    @Test
    void deveRetornarCotacaoValida() {
        CotacaoService service = new CotacaoService();
        String resultado = service.buscarCotacaoDolar();

        assertNotNull(resultado);
        assertFalse(resultado.isBlank());
        assertTrue(
                resultado.startsWith("USD") || resultado.equals("Cotação indisponível no momento."),
                "Resposta inesperada: " + resultado
        );
    }

    @Test
    void deveRetornarMensagemDeErroComUrlInvalida() {
        CotacaoService service = new CotacaoService("https://url-invalida-que-nao-existe.xyz");
        String resultado = service.buscarCotacaoDolar();

        assertEquals("Cotação indisponível no momento.", resultado);
    }
}