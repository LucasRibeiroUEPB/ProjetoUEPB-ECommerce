package controller;

import model.CategoriaItem;
import model.Cliente;
import model.LojaSistema;

public class LojaController {

	private LojaSistema sistema;

	public LojaController() {
		sistema = new LojaSistema();
	}

	public String adicionarJogoDigital(String codigo, String nome, double precoBase, int quantidadeEstoque,
			CategoriaItem categoria, double tamanhoDownload, String chaveAtivacao) {
		return sistema.adicionarJogoDigital(codigo, nome, precoBase, quantidadeEstoque, categoria, tamanhoDownload,
				chaveAtivacao);
	}

	public String adicionarColecionavelFisico(String codigo, String nome, double precoBase, int quantidadeEstoque,
			CategoriaItem categoria, double peso, String dimensoes, String estadoConservacao) {
		return sistema.adicionarColecionavelFisico(codigo, nome, precoBase, quantidadeEstoque, categoria, peso,
				dimensoes, estadoConservacao);
	}

	public void cadastrarCliente(Cliente cliente) {
		sistema.cadastrarCliente(cliente);
	}

	public String adicionarAoCarrinho(String cpf, String codigoItem, int qtd) {
		return sistema.adicionarAoCarrinho(cpf, codigoItem, qtd);
	}

	public String finalizarCompra(String cpf, String cupom) {
		return sistema.finalizarCompra(cpf, cupom);
	}
}
