package model;

import exception.Validador;

public class ColecionavelFisico extends ItemEstoque {

	private double peso;
	private String dimensoes;
	private String estadoConservacao;

	public ColecionavelFisico(String codigo, String nome, double precoBase, int quantidadeEstoque,
			CategoriaItem categoria, double peso, String dimensoes, String estadoConservacao) throws Exception {

		super(codigo, nome, precoBase, quantidadeEstoque, categoria);

		Validador.verificaMenorOuIgualaZero(peso, "Peso esta menor ou igual a zero");

		Validador.verificarVazio(dimensoes, "Dimensoes invalidas");

		Validador.verificarVazio(estadoConservacao, "Estado de conservacao invalido");

		this.peso = peso;
		this.dimensoes = dimensoes;
		this.estadoConservacao = estadoConservacao;
	}

	@Override
	public double calcularPrecoFinal() {
		return getPrecoBase() + calcularFrete();
	}

	private double calcularFrete() {

		if (peso <= 0.5) {
			return 10.00;
		}

		if (peso <= 1.0) {
			return 15.00;
		}

		if (peso <= 3.0) {
			return 25.00;
		}

		return 40.00;
	}

	@Override
	public String toString() {
		return super.toString() + "\n" + "Peso: " + peso + "g\n" + "Dimensoes: " + dimensoes + "\n"
				+ "Estado de conservacao: " + estadoConservacao;
	}

}