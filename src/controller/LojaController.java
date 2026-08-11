package controller;

import model.CategoriaItem;
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

	public String cadastrarCliente(String cpf, String nome) {
		return sistema.cadastrarCliente(cpf, nome);
	}

	public String adicionarAoCarrinho(String cpf, String codigoItem, int qtd) {
		return sistema.adicionarAoCarrinho(cpf, codigoItem, qtd);
	}

	public String removerDoCarrinho(String cpf, String codigoItem) {
		return sistema.removerDoCarrinho(cpf, codigoItem);
	}

	public String finalizarCompra(String cpf, String cupom) {
		return sistema.finalizarCompra(cpf, cupom);
	}

	public String listarItens() {
		return sistema.listarItens();
	}

	public String listarClientes() {
		return sistema.listarClientes();
	}
}
