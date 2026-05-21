package com.gastei;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class LimiteCategoriaRepository {

  private static final String ARQUIVO = "limites.json";
  private final Gson gson;

  public LimiteCategoriaRepository() {
    this.gson = new GsonBuilder().setPrettyPrinting().create();
  }

  public List<LimiteCategoria> listarTodos() {
    try (FileReader reader = new FileReader(ARQUIVO)) {
      Type tipo = new TypeToken<List<LimiteCategoria>>() {}.getType();
      List<LimiteCategoria> limites = gson.fromJson(reader, tipo);
      return limites != null ? limites : new ArrayList<>();
    } catch (IOException e) {
      return new ArrayList<>();
    }
  }

  public void salvar(List<LimiteCategoria> limites) {
    try (FileWriter writer = new FileWriter(ARQUIVO)) {
      gson.toJson(limites, writer);
    } catch (IOException e) {
      System.err.println("Erro ao salvar limites: " + e.getMessage());
    }
  }

  public void definirLimite(String categoria, double valor) {
    List<LimiteCategoria> limites = listarTodos();
    limites.removeIf(l -> l.getCategoria().equalsIgnoreCase(categoria));
    limites.add(new LimiteCategoria(categoria, valor));
    salvar(limites);
  }

  public void removerLimite(String categoria) {
    List<LimiteCategoria> limites = listarTodos();
    limites.removeIf(l -> l.getCategoria().equalsIgnoreCase(categoria));
    salvar(limites);
  }
}