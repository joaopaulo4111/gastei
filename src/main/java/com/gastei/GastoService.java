package com.gastei;

import java.util.ArrayList;
import java.util.List;

public class GastoService {
    private List<Gasto> gastos = new ArrayList<>();

    public void adicionar(String descricao, double valor, String categoria, String data) {
        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("Descrição não pode ser vazia.");
        }
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor deve ser maior que zero.");
        }
        gastos.add(new Gasto(descricao, valor, categoria, data));
    }

    public List<Gasto> listar() {
        return new ArrayList<>(gastos);
    }

    public double totalGasto() {
        return gastos.stream().mapToDouble(Gasto::getValor).sum();
    }

    public void setGastos(List<Gasto> gastos) {
        this.gastos = gastos;
    }
}