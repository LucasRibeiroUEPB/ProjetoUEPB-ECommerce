package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exception.Validador;

public class CarrinhoDeCompras {

	private List<ItemEstoque> itens;
	private Map<ItemEstoque, Integer> quantidades;
	private CalculoDesconto estrategiaDesconto;

	public CarrinhoDeCompras() {
		this.itens = new ArrayList<>();
		this.quantidades = new HashMap<>();
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

		if (itens.contains(item)) {
			quantidades.put(item, quantidades.get(item) + quantidade);
		} else {
			itens.add(item);
			quantidades.put(item, quantidade);
		}
	}

	public void removerItem(ItemEstoque item) {
		itens.remove(item);
		quantidades.remove(item);
	}

	private double calcularSubtotal() {
		double subtotal = 0.0;
		for (ItemEstoque item : itens) {
			subtotal += item.calcularPrecoFinal() * quantidades.get(item);
		}
		return subtotal;
	}

	public double calcularTotal() {
		double subtotal = calcularSubtotal();
		return (estrategiaDesconto == null) ? subtotal : estrategiaDesconto.aplicarDesconto(subtotal);
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

	@Override
	public String toString() {
		if (itens.isEmpty()) {
			return "  (vazio)";
		}
		String info = "";
		boolean primeiro = true;
		for (ItemEstoque item : itens) {
			if (!primeiro) {
				info += "\n";
			}
			info += "  " + item.getNome() + " x" + quantidades.get(item);
			primeiro = false;
		}
		return info;
	}

}