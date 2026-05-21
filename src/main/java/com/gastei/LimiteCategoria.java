package com.gastei;

public class LimiteCategoria {

  private String categoria;
  private double limite;

  public LimiteCategoria(String categoria, double limite) {
    this.categoria = categoria;
    this.limite = limite;
  }

  public String getCategoria() {
    return categoria;
  }

  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }

  public double getLimite() {
    return limite;
  }

  public void setLimite(double limite) {
    this.limite = limite;
  }

  @Override
  public String toString() {
    return "LimiteCategoria{"
        + "categoria='" + categoria + '\''
        + ", limite=" + limite
        + '}';
  }
}