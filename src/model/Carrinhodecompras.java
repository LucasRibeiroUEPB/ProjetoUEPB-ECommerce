package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Carrinhodecompras {

    private List<ItemEstoque> itens;
    private Map<ItemEstoque, Integer> quantidades;

    public Carrinhodecompras() {
        this.itens = new ArrayList<>();
        this.quantidades = new HashMap<>();
    }

    public void adicionarItem(ItemEstoque item, int quantidade) {
        if (!itens.contains(item)) {
            itens.add(item);
            quantidades.put(item, quantidade);
        } else {
            quantidades.put(item, quantidades.get(item) + quantidade);
        }
    }

    public void removerItem(ItemEstoque item) {
        itens.remove(item);
        quantidades.remove(item);
    }

    public double calcularSubtotal() {
        double subtotal = 0.0;
        for (ItemEstoque item : itens) {
            subtotal += item.calculaPrecoFinal() * quantidades.get(item);
        }
        return subtotal;
    }

    public double calcularTotal() {
        return calcularSubtotal();
    }
    public double aplicarDesconto(Calculodesconto desconto) {
        double subtotal = calcularSubtotal();
        return desconto.aplicarDesconto(subtotal);
    }

    public void limparCarrinho() {
        itens.clear();
        quantidades.clear();
    }

    public List<ItemEstoque> getItens() {
        return itens;
    }

    public Map<ItemEstoque, Integer> getQuantidades() {
        return quantidades;
    }
}