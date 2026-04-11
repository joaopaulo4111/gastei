package com.gastei;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GastoRepository {
    private static final String ARQUIVO = "gastos.json";
    private final Gson gson = new Gson();

    public void salvar(List<Gasto> gastos) {
        try (Writer writer = new FileWriter(ARQUIVO)) {
            gson.toJson(gastos, writer);
        } catch (IOException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }

    public List<Gasto> carregar() {
        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists()) return new ArrayList<>();
        try (Reader reader = new FileReader(arquivo)) {
            Type tipo = new TypeToken<List<Gasto>>(){}.getType();
            List<Gasto> resultado = gson.fromJson(reader, tipo);
            return resultado != null ? resultado : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}