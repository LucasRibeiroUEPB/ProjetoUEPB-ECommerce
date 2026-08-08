package model;

public abstract class ItemEstoque {
	private String codigo;
	private String nome;
	private double precoBase;
	private CategoriaItem categoria;
	
	public ItemEstoque(String codigo, String nome, double precoBase, CategoriaItem categoria) {
		this.codigo = codigo;
		this.nome = nome;
		this.precoBase = precoBase;
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
	public abstract double calculaPrecoFinal();
	
	
	
	

}
