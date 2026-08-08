package model;

import exception.Validador;

public abstract class ItemEstoque {
	private String codigo;
	private String nome;
	private double precoBase;
	private int quantidadeEstoque;
	private CategoriaItem categoria;

	public ItemEstoque(String codigo, String nome, double precoBase, int quantidadeEstoque, CategoriaItem categoria)
			throws Exception {
		
		Validador.verificarVazio(codigo, "codigo esta invalido");

		Validador.verificarVazio(nome, "nome esta invalido");

		Validador.verificarNegativo(precoBase, "preco esta negativo");

		Validador.verificarNegativo(quantidadeEstoque, "estoque esta negativo");

		if (categoria == null) {
			throw new Exception("categoria esta invalida");
		}

		this.codigo = codigo;
		this.nome = nome;
		this.precoBase = precoBase;
		this.quantidadeEstoque = quantidadeEstoque;
		this.categoria = categoria;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public double getPrecoBase() {
		return precoBase;
	}

	public CategoriaItem getCategoria() {
		return categoria;
	}

	public int getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public abstract double calcularPrecoFinal();

	public void reduzirEstoque(int qtd) throws Exception {

		if (qtd > quantidadeEstoque) {
			throw new Exception("Estoque insuficiente para o item: " + nome);
		}

		quantidadeEstoque -= qtd;
	}

}
