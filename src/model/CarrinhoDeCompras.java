package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exception.Validador;

public class CarrinhoDeCompras {

	private Map<ItemEstoque, Integer> itensNoCarrinho;
	private CalculoDesconto estrategiaDesconto;

	public CarrinhoDeCompras() {
		this.itensNoCarrinho = new HashMap<>();
		this.estrategiaDesconto = null;
	}

	public void setEstrategiaDesconto(CalculoDesconto estrategia) {
		this.estrategiaDesconto = estrategia;
	}

	public void adicionarItem(ItemEstoque item, int quantidade) throws Exception {
		if (item == null) {
			throw new Exception("Item invalido");
		}

		Validador.verificarZero(quantidade, "Quantidade deve ser maior que zero");

		int quantidadeAtualNoCarrinho = 0;
		if (itensNoCarrinho.containsKey(item)) {
			quantidadeAtualNoCarrinho = itensNoCarrinho.get(item);
		}

		int quantidadeDesejada = quantidadeAtualNoCarrinho + quantidade;
		if (quantidadeDesejada > item.getQuantidadeEstoque()) {
			throw new Exception("Estoque insuficiente. O item " + item.getNome() + " possui apenas "
					+ item.getQuantidadeEstoque() + " unidades disponiveis.");
		}

		itensNoCarrinho.put(item, quantidadeDesejada);
	}

	public void removerItem(ItemEstoque item) {
		itensNoCarrinho.remove(item);
	}

	private double calcularSubtotal() {
		double subtotal = 0.0;
		for (ItemEstoque item : itensNoCarrinho.keySet()) {
			subtotal += item.calcularPrecoFinal() * itensNoCarrinho.get(item);
		}
		return subtotal;
	}

	public double calcularTotal() {
		double subtotal = calcularSubtotal();
		return (estrategiaDesconto == null) ? subtotal : estrategiaDesconto.aplicarDesconto(subtotal);
	}

	public void limparCarrinho() {
		itensNoCarrinho.clear();
	}

	public List<ItemEstoque> getItens() {
		return new ArrayList<>(itensNoCarrinho.keySet());
	}

	public Map<ItemEstoque, Integer> getItensNoCarrinho() {
		return itensNoCarrinho;
	}

	@Override
	public String toString() {
		if (itensNoCarrinho.isEmpty()) {
			return "  (vazio)";
		}
		String info = "";
		boolean primeiro = true;

		for (ItemEstoque item : itensNoCarrinho.keySet()) {
			if (!primeiro) {
				info += "\n";
			}
			info += "  " + item.getNome() + " x" + itensNoCarrinho.get(item);
			primeiro = false;
		}
		return info;
	}
}