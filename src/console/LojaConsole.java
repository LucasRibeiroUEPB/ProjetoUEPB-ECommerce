package console;

import java.util.Scanner;

import controller.LojaController;
import model.CategoriaItem;

public class LojaConsole {

	private Scanner scanner;
	private LojaController controlador;

	public LojaConsole() {
		scanner = new Scanner(System.in);
		controlador = new LojaController();
	}

	public void iniciar() {
		int opcao;

		do {
			exibirMenu();
			opcao = lerInteiro("Opcao: ");

			executarOpcao(opcao);

			if (opcao != 0) {
				System.out.println();
			}

		} while (opcao != 0);

		scanner.close();
	}

	private void exibirMenu() {
		System.out.println("==================================");
		System.out.println("    LOJA DIGITAL E COLECIONAVEIS");
		System.out.println("==================================");
		System.out.println("1 - Cadastrar item");
		System.out.println("2 - Cadastrar cliente");
		System.out.println("3 - Adicionar item ao carrinho");
		System.out.println("4 - Finalizar compra");
		System.out.println("5 - Listar itens em estoque");
		System.out.println("6 - Listar clientes");
		System.out.println("7 - Remover item do carrinho");
		System.out.println("8 - Adicionar estoque a um item");
		System.out.println("9 - Listar historico de compras");
		System.out.println("0 - Sair");
		System.out.println();
	}

	private void executarOpcao(int opcao) {
		switch (opcao) {
		case 1:
			cadastrarItem();
			break;

		case 2:
			cadastrarCliente();
			break;

		case 3:
			adicionarAoCarrinho();
			break;

		case 4:
			finalizarCompra();
			break;

		case 5:
			listarItens();
			break;

		case 6:
			listarClientes();
			break;

		case 7:
			removerDoCarrinho();
			break;

		case 8:
			adicionarEstoque();
			break;

		case 9:
			listarHistoricoCompras();
			break;
		case 0:
			System.out.println("Programa encerrado.");
			break;

		default:
			System.out.println("Opcao invalida.");
		}
	}

	private int lerInteiro(String mensagem) {
		while (true) {
			System.out.print(mensagem);
			String entrada = scanner.nextLine();

			try {
				return Integer.parseInt(entrada);

			} catch (NumberFormatException e) {
				System.out.println("Digite um numero inteiro valido.");
			}
		}
	}

	private double lerDouble(String mensagem) {
		while (true) {
			System.out.print(mensagem);
			String entrada = scanner.nextLine();

			try {
				return Double.parseDouble(entrada);

			} catch (NumberFormatException e) {
				System.out.println("Digite um numero valido.");
			}
		}
	}

	private String lerTexto(String mensagem) {
		System.out.print(mensagem);
		return scanner.nextLine();
	}

	private void cadastrarItem() {
		System.out.println("TIPO DE ITEM\n");
		System.out.println("1 - Jogo Digital");
		System.out.println("2 - Colecionavel Fisico");
		System.out.println("0 - Voltar");
		int opcao = lerInteiro("Tipo:");

		switch (opcao) {
		case 1: {
			String codigo = lerTexto("Codigo:");
			String nome = lerTexto("Nome:");
			double precoBase = lerDouble("Preco base:");
			int quantidadeEstoque = lerInteiro("Quantidade em estoque:");
			CategoriaItem categoria = lerCategoria();
			double tamanhoDownload = lerDouble("Tamanho do download (GB):");
			String chaveAtivacao = lerTexto("Chave de ativacao:");

			String resultado = controlador.adicionarJogoDigital(codigo, nome, precoBase, quantidadeEstoque, categoria,
					tamanhoDownload, chaveAtivacao);
			System.out.println(resultado);
			break;
		}
		case 2: {
			String codigo = lerTexto("Codigo:");
			String nome = lerTexto("Nome:");
			double precoBase = lerDouble("Preco base:");
			int quantidadeEstoque = lerInteiro("Quantidade em estoque:");
			CategoriaItem categoria = lerCategoria();
			double peso = lerDouble("Peso (g):");
			String dimensoes = lerTexto("Dimensoes:");
			String estadoConservacao = lerTexto("Estado de conservacao:");

			String resultado = controlador.adicionarColecionavelFisico(codigo, nome, precoBase, quantidadeEstoque,
					categoria, peso, dimensoes, estadoConservacao);
			System.out.println(resultado);
			break;
		}
		default:
			break;
		}
	}

	private CategoriaItem lerCategoria() {
		System.out.println("CATEGORIA\n");
		System.out.println("1 - Jogo");
		System.out.println("2 - Boardgame");
		System.out.println("3 - Acessorio");
		System.out.println("4 - Academico");
		int opcao = lerInteiro("Categoria:");

		switch (opcao) {
		case 1:
			return CategoriaItem.JOGO;
		case 2:
			return CategoriaItem.BOARDGAME;
		case 3:
			return CategoriaItem.ACESSORIO;
		case 4:
			return CategoriaItem.ACADEMICO;
		default:
			return null;
		}
	}

	private void cadastrarCliente() {
		String cpf = lerTexto("CPF:");
		String nome = lerTexto("Nome:");

		String resultado = controlador.cadastrarCliente(cpf, nome);
		System.out.println(resultado);
	}

	private void adicionarAoCarrinho() {
		String cpf = lerTexto("CPF do cliente:");
		String codigoItem = lerTexto("Codigo do item:");
		int quantidade = lerInteiro("Quantidade:");

		String resultado = controlador.adicionarAoCarrinho(cpf, codigoItem, quantidade);
		System.out.println(resultado);
	}

	private void removerDoCarrinho() {
		String cpf = lerTexto("CPF do cliente: ");
		String codigoItem = lerTexto("Codigo do item: ");

		String resultado = controlador.removerDoCarrinho(cpf, codigoItem);
		System.out.println(resultado);
	}

	private void finalizarCompra() {
		String cpf = lerTexto("CPF do cliente:");
		String cupom = lerTexto("Cupom (deixe em branco se nao tiver):");

		if (cupom.trim().isEmpty()) {
			cupom = null;
		}

		String resultado = controlador.finalizarCompra(cpf, cupom);
		System.out.println(resultado);
	}

	private void listarItens() {
		System.out.println(controlador.listarItens());
	}

	private void listarClientes() {
		System.out.println(controlador.listarClientes());
	}

	private void adicionarEstoque() {
		String codigo = lerTexto("Codigo do item: ");
		int quantidade = lerInteiro("Quantidade a adicionar: ");

		String resultado = controlador.adicionarEstoque(codigo, quantidade);
		System.out.println(resultado);
	}

	private void listarHistoricoCompras() {
		System.out.println("\n--- HISTORICO DE COMPRAS ---");
		String cpf = lerTexto("CPF do cliente: ");

		String resultado = controlador.listarHistoricoCompras(cpf);
		System.out.println(resultado);
	}
}