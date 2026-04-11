package com.gastei;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CotacaoService {

    private final String apiUrl;

    public CotacaoService() {
        this.apiUrl = "https://economia.awesomeapi.com.br/json/last/USD-BRL";
    }

    public CotacaoService(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String buscarCotacaoDolar() {
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);

            int status = conn.getResponseCode();
            if (status != 200) {
                return "Cotação indisponível no momento.";
            }

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
            );
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            String json = response.toString();
            int index = json.indexOf("\"bid\":\"");
            if (index == -1) return "Cotação indisponível no momento.";
            int start = index + 7;
            int end = json.indexOf("\"", start);
            String valor = json.substring(start, end);
            double cotacao = Double.parseDouble(valor);
            return String.format("USD 1,00 = R$ %.2f", cotacao);

        } catch (Exception e) {
            return "Cotação indisponível no momento.";
        }
    }
}