package model;

import exception.Validador;

public class Cliente {
	private String cpf;
	private String nome;
	private CarrinhoDeCompras carrinho;

	public Cliente(String cpf, String nome) throws Exception {

		Validador.verificarVazio(cpf, "CPF inválido");
		Validador.verificarVazio(nome, "Nome inválido");
		this.cpf = cpf;
		this.nome = nome;
		this.carrinho = new CarrinhoDeCompras();
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
