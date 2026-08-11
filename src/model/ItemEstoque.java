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

		Validador.verificarVazio(codigo, "Codigo invalido");

		Validador.verificarVazio(nome, "Nome invalido");

		Validador.verificarNegativo(precoBase, "Preço inválido");

		Validador.verificarNegativo(quantidadeEstoque, "Quantidade inválida");

		if (categoria == null) {
			throw new Exception("Categoria invalida");
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

	@Override
	public String toString() {
		return "Codigo: " + codigo + "\n" + "Nome: " + nome + "\n" + "Categoria: " + categoria + "\n" + "Preco base: "
				+ precoBase + "\n" + "Preco final: " + calcularPrecoFinal() + "\n" + "Quantidade em estoque: "
				+ quantidadeEstoque;
	}

}
