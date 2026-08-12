package model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LojaSistema {
	private Map<String, ItemEstoque> inventario;
	private Map<String, Cliente> clientes;

	public LojaSistema() {
		this.inventario = new LinkedHashMap<>();
		this.clientes = new LinkedHashMap<>();
	}

	public String adicionarJogoDigital(String codigo, String nome, double precoBase, int quantidadeEstoque,
			CategoriaItem categoria, double tamanhoDownload, String chaveAtivacao) {

		try {
			JogoDigital jogo = new JogoDigital(codigo, nome, precoBase, quantidadeEstoque, categoria, tamanhoDownload,
					chaveAtivacao);

			registrarItem(jogo);

			return "Jogo digital \"" + nome + "\" adicionado com sucesso.";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	public String adicionarColecionavelFisico(String codigo, String nome, double precoBase, int quantidadeEstoque,
			CategoriaItem categoria, double peso, String dimensoes, String estadoConservacao) {

		try {
			ColecionavelFisico colecionavel = new ColecionavelFisico(codigo, nome, precoBase, quantidadeEstoque,
					categoria, peso, dimensoes, estadoConservacao);

			registrarItem(colecionavel);

			return "Colecionável físico \"" + nome + "\" adicionado com sucesso.";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	private void registrarItem(ItemEstoque item) throws Exception {
		if (inventario.containsKey(item.getCodigo())) {
			throw new Exception("Já existe um item cadastrado com o código: " + item.getCodigo());
		}
		inventario.put(item.getCodigo(), item);
	}

	public String adicionarEstoque(String codigo, int quantidade) {
		try {
			ItemEstoque item = buscarItem(codigo);

			item.adicionarEstoque(quantidade);

			return "Estoque do item \"" + item.getNome() + "\" atualizado com sucesso. Novo saldo: "
					+ item.getQuantidadeEstoque();
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	public String cadastrarCliente(String cpf, String nome) {
		try {
			if (clientes.containsKey(cpf)) {
				return "Já existe um cliente cadastrado com o CPF: " + cpf;
			}

			Cliente cliente = new Cliente(cpf, nome);
			clientes.put(cliente.getCpf(), cliente);

			return "Cliente cadastrado com sucesso!";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	public ItemEstoque buscarItem(String codigo) throws Exception {
		ItemEstoque item = inventario.get(codigo);

		if (item == null) {
			throw new Exception("Item não encontrado: o código " + codigo + " não existe no inventário.");
		}
		return item;
	}

	public Cliente buscarCliente(String cpf) throws Exception {
		Cliente cliente = clientes.get(cpf);

		if (cliente == null) {
			throw new Exception("Cliente com CPF " + cpf + " não cadastrado no sistema.");
		}
		return cliente;
	}

	public String adicionarAoCarrinho(String cpf, String codigoItem, int quantidade) {
		try {
			Cliente cliente = buscarCliente(cpf);
			ItemEstoque item = buscarItem(codigoItem);

			cliente.getCarrinho().adicionarItem(item, quantidade);

			return "Item \"" + item.getNome() + "\" adicionado ao carrinho com sucesso.";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	public String removerDoCarrinho(String cpf, String codigoItem) {
		try {
			Cliente cliente = buscarCliente(cpf);
			ItemEstoque item = buscarItem(codigoItem);

			if (!cliente.getCarrinho().getItensNoCarrinho().containsKey(item)) {
				return "O item \"" + item.getNome() + "\" não está no carrinho.";
			}

			cliente.getCarrinho().removerItem(item);

			return "Item \"" + item.getNome() + "\" removido do carrinho com sucesso.";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	public String finalizarCompra(String cpf, String cupom) {
		try {
			Cliente cliente = buscarCliente(cpf);
			CarrinhoDeCompras carrinho = cliente.getCarrinho();

			carrinho.setEstrategiaDesconto(obterEstrategiaDesconto(cupom));

			double valorFinal = carrinho.calcularTotal();

			String recibo = "Total: R$ " + valorFinal + "\nItens comprados:\n" + carrinho.toString();

			processarVenda(carrinho);

			cliente.adicionarAoHistorico(recibo);

			return "Compra finalizada com sucesso! Total: R$ " + valorFinal;
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	private CalculoDesconto obterEstrategiaDesconto(String cupom) throws Exception {
		if (cupom == null) {
			return null;
		}

		if (cupom.equals("FIX020")) {
			return new DescontoCupomFixo(20.0);
		}

		if (cupom.equals("PROMO10")) {
			return new DescontoPorcentagem(10.0);
		}

		throw new Exception("Cupom inválido: " + cupom);
	}

	private void processarVenda(CarrinhoDeCompras carrinho) throws Exception {
		Map<ItemEstoque, Integer> itensNoCarrinho = carrinho.getItensNoCarrinho();

		for (Map.Entry<ItemEstoque, Integer> entry : itensNoCarrinho.entrySet()) {
			ItemEstoque item = entry.getKey();
			int quantidadeSolicitada = entry.getValue();

			if (quantidadeSolicitada > item.getQuantidadeEstoque()) {
				throw new Exception("Estoque insuficiente para o item: " + item.getNome());
			}else {
				item.reduzirEstoque(quantidadeSolicitada);
			}
			carrinho.limparCarrinho();
		}

		carrinho.limparCarrinho();
	}

	public String listarItens() {
		if (inventario.isEmpty()) {
			return "Nenhum item cadastrado.";
		}

		String info = "";
		int tamanho = inventario.size();
		for (ItemEstoque item : inventario.values()) {
			info += item;
			tamanho--;

			if (tamanho > 0) {
				info += "\n\n";
			}
		}
		return info;

	}

	public String listarClientes() {
		if (clientes.isEmpty()) {
			return "Nenhum cliente cadastrado.";
		}

		String info = "";
		int tamanho = clientes.size();
		for (Cliente cliente : clientes.values()) {
			info += cliente;
			tamanho--;
			if (tamanho > 0) {
				info += "\n\n";
			}
		}
		return info;
	}

	public String listarHistoricoCompras(String cpf) {
		try {
			Cliente cliente = buscarCliente(cpf);

			List<String> historico = cliente.getHistoricoDeCompras();

			if (historico.isEmpty()) {
				return "Nenhuma compra registrada para o cliente " + cliente.getNome() + ".";
			}

			String info = "Histórico de Compras - " + cliente.getNome() + ":\n";

			for (int i = 0; i < historico.size(); i++) {
				info += "\n--- Compra " + (i + 1) + " ---\n";
				info += historico.get(i) + "\n";
			}

			return info;
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}
