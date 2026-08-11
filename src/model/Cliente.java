package model;

import java.util.ArrayList;
import java.util.List;

import exception.Validador;

public class Cliente {
	private String cpf;
	private String nome;
	private CarrinhoDeCompras carrinho;
	private List<String> historicoDeCompras;

	public Cliente(String cpf, String nome) throws Exception {

		Validador.verificarVazio(cpf, "CPF inválido");
		Validador.verificarVazio(nome, "Nome inválido");
		this.cpf = cpf;
		this.nome = nome;
		this.carrinho = new CarrinhoDeCompras();
		historicoDeCompras = new ArrayList<String>();
	}

	public List<String> getHistoricoDeCompras() {
		return historicoDeCompras;
	}

	public void adicionarAoHistorico(String recibo) {
		historicoDeCompras.add(recibo);
	}

	public void setHistoricoDeCompras(List<String> historicoDeCompras) {
		this.historicoDeCompras = historicoDeCompras;
	}

	public String getCpf() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}

	public CarrinhoDeCompras getCarrinho() {
		return carrinho;
	}

	@Override
	public String toString() {
		return "CPF: " + cpf + "\n" + "Nome: " + nome + "\n" + "Carrinho:\n" + carrinho;
	}
}
