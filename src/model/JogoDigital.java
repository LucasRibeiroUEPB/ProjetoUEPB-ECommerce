package model;

import exception.Validador;

public class JogoDigital extends ItemEstoque {
	private double tamanhoDownload;
	private String chaveAtivacao;

	public JogoDigital(String codigo, String nome, double precoBase, int quantidadeEstoque, CategoriaItem categoria,
			double tamanhoDownload, String chaveAtivacao) throws Exception {

		super(codigo, nome, precoBase, quantidadeEstoque, categoria);

		Validador.verificarZero(tamanhoDownload, "tamanho do download esta menor ou igual a zero");

		Validador.verificarVazio(chaveAtivacao, "chave de ativacao esta invalida");

		this.tamanhoDownload = tamanhoDownload;
		this.chaveAtivacao = chaveAtivacao;
	}

	@Override
	public double calcularPrecoFinal() {
		return getPrecoBase();
	}

}
