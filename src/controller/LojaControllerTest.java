package controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import model.CategoriaItem;

public class LojaControllerTest {

	@Test
	public void testAdicionarJogoDigitalComSucesso() {
		LojaController controlador = new LojaController();
		String resultado = controlador.adicionarJogoDigital("JD01", "Elden Ring", 250.0, 10, CategoriaItem.JOGO, 45.0,
				"CHAVE123");
		assertEquals("Jogo digital \"Elden Ring\" adicionado com sucesso.", resultado);
	}

	@Test
	public void testAdicionarJogoDigitalComCodigoVazio() {
		LojaController controlador = new LojaController();
		String resultado = controlador.adicionarJogoDigital("", "Elden Ring", 250.0, 10, CategoriaItem.JOGO, 45.0,
				"CHAVE123");
		assertEquals("Codigo invalido", resultado);
	}

	@Test
	public void testAdicionarJogoDigitalComNomeVazio() {
		LojaController controlador = new LojaController();
		String resultado = controlador.adicionarJogoDigital("JD01", "", 250.0, 10, CategoriaItem.JOGO, 45.0,
				"CHAVE123");
		assertEquals("Nome invalido", resultado);
	}

	@Test
	public void testAdicionarJogoDigitalComPrecoNegativo() {
		LojaController controlador = new LojaController();
		String resultado = controlador.adicionarJogoDigital("JD01", "Elden Ring", -10.0, 10, CategoriaItem.JOGO, 45.0,
				"CHAVE123");
		assertEquals("Preço inválido", resultado);
	}

	@Test
	public void testAdicionarJogoDigitalComQuantidadeNegativa() {
		LojaController controlador = new LojaController();
		String resultado = controlador.adicionarJogoDigital("JD01", "Elden Ring", 250.0, -1, CategoriaItem.JOGO, 45.0,
				"CHAVE123");
		assertEquals("Quantidade inválida", resultado);
	}

	@Test
	public void testAdicionarJogoDigitalComCategoriaNula() {
		LojaController controlador = new LojaController();
		String resultado = controlador.adicionarJogoDigital("JD01", "Elden Ring", 250.0, 10, null, 45.0, "CHAVE123");
		assertEquals("Categoria invalida", resultado);
	}

	@Test
	public void testAdicionarJogoDigitalComTamanhoDownloadZero() {
		LojaController controlador = new LojaController();
		String resultado = controlador.adicionarJogoDigital("JD01", "Elden Ring", 250.0, 10, CategoriaItem.JOGO, 0.0,
				"CHAVE123");
		assertEquals("Tamanho do download esta menor ou igual a zero", resultado);
	}

	@Test
	public void testAdicionarJogoDigitalComChaveAtivacaoVazia() {
		LojaController controlador = new LojaController();
		String resultado = controlador.adicionarJogoDigital("JD01", "Elden Ring", 250.0, 10, CategoriaItem.JOGO, 45.0,
				"");
		assertEquals("Chave de ativacao invalida", resultado);
	}

	@Test
	public void testAdicionarColecionavelFisicoComSucesso() {
		LojaController controlador = new LojaController();
		String resultado = controlador.adicionarColecionavelFisico("CF01", "Funko Pop", 89.9, 5,
				CategoriaItem.ACESSORIO, 0.3, "10x10x15cm", "Novo");
		assertEquals("Colecionável físico \"Funko Pop\" adicionado com sucesso.", resultado);
	}

	@Test
	public void testAdicionarColecionavelFisicoComPesoZero() {
		LojaController controlador = new LojaController();
		String resultado = controlador.adicionarColecionavelFisico("CF01", "Funko Pop", 89.9, 5,
				CategoriaItem.ACESSORIO, 0.0, "10x10x15cm", "Novo");
		assertEquals("Peso esta menor ou igual a zero", resultado);
	}

	@Test
	public void testAdicionarColecionavelFisicoComDimensoesVazias() {
		LojaController controlador = new LojaController();
		String resultado = controlador.adicionarColecionavelFisico("CF01", "Funko Pop", 89.9, 5,
				CategoriaItem.ACESSORIO, 0.3, "", "Novo");
		assertEquals("Dimensoes invalidas", resultado);
	}

	@Test
	public void testAdicionarColecionavelFisicoComEstadoConservacaoVazio() {
		LojaController controlador = new LojaController();
		String resultado = controlador.adicionarColecionavelFisico("CF01", "Funko Pop", 89.9, 5,
				CategoriaItem.ACESSORIO, 0.3, "10x10x15cm", "");
		assertEquals("Estado de conservacao invalido", resultado);
	}

	@Test
	public void testNaoRegistrarItemComCodigoDuplicado() {
		LojaController controlador = new LojaController();
		controlador.adicionarJogoDigital("JD01", "Elden Ring", 250.0, 10, CategoriaItem.JOGO, 45.0, "CHAVE123");
		String resultadoSegundo = controlador.adicionarColecionavelFisico("JD01", "Funko Pop", 89.9, 5,
				CategoriaItem.ACESSORIO, 0.3, "10x10x15cm", "Novo");

		assertEquals("Já existe um item cadastrado com o código: JD01", resultadoSegundo);
	}

	@Test
	public void testAdicionarEstoqueComSucesso() {
		LojaController controlador = new LojaController();
		controlador.adicionarJogoDigital("JD01", "Elden Ring", 250.0, 10, CategoriaItem.JOGO, 45.0, "CHAVE123");

		String resultado = controlador.adicionarEstoque("JD01", 5);
		assertEquals("Estoque do item \"Elden Ring\" atualizado com sucesso. Novo saldo: 15", resultado);
	}

	@Test
	public void testAdicionarEstoqueZeroOUNegativo() {
		LojaController controlador = new LojaController();
		controlador.adicionarJogoDigital("JD01", "Elden Ring", 250.0, 10, CategoriaItem.JOGO, 45.0, "CHAVE123");

		String resultado = controlador.adicionarEstoque("JD01", 0);
		assertEquals("A quantidade para adicionar ao estoque deve ser maior que zero", resultado);
	}

	@Test
	public void testAdicionarEstoqueItemInexistente() {
		LojaController controlador = new LojaController();
		String resultado = controlador.adicionarEstoque("COD_FANTASMA", 5);
		assertEquals("Item não encontrado: o código COD_FANTASMA não existe no inventário.", resultado);
	}

	@Test
	public void testCadastrarClienteComSucesso() {
		LojaController controlador = new LojaController();
		String resultado = controlador.cadastrarCliente("11111111111", "João");
		assertEquals("Cliente cadastrado com sucesso!", resultado);
	}

	@Test
	public void testCadastrarClienteCpfVazio() {
		LojaController controlador = new LojaController();
		String resultado = controlador.cadastrarCliente("", "João");
		assertEquals("CPF inválido", resultado);
	}

	@Test
	public void testCadastrarClienteNomeVazio() {
		LojaController controlador = new LojaController();
		String resultado = controlador.cadastrarCliente("11111111111", "");
		assertEquals("Nome inválido", resultado);
	}

	@Test
	public void testCadastrarClienteCpfDuplicado() {
		LojaController controlador = new LojaController();
		controlador.cadastrarCliente("11111111111", "João");
		String resultado = controlador.cadastrarCliente("11111111111", "Maria");
		assertEquals("Já existe um cliente cadastrado com o CPF: 11111111111", resultado);
	}

	@Test
	public void testAdicionarAoCarrinhoComSucesso() {
		LojaController controlador = new LojaController();
		controlador.cadastrarCliente("11111111111", "João");
		controlador.adicionarJogoDigital("JD01", "Elden Ring", 250.0, 10, CategoriaItem.JOGO, 45.0, "CHAVE123");

		String resultado = controlador.adicionarAoCarrinho("11111111111", "JD01", 2);
		assertEquals("Item \"Elden Ring\" adicionado ao carrinho com sucesso.", resultado);
	}

	@Test
	public void testAdicionarAoCarrinhoClienteInexistente() {
		LojaController controlador = new LojaController();
		controlador.adicionarJogoDigital("JD01", "Elden Ring", 250.0, 10, CategoriaItem.JOGO, 45.0, "CHAVE123");

		String resultado = controlador.adicionarAoCarrinho("11111111111", "JD01", 1);
		assertEquals("Cliente com CPF 11111111111 não cadastrado no sistema.", resultado);
	}

	@Test
	public void testAdicionarAoCarrinhoItemInexistente() {
		LojaController controlador = new LojaController();
		controlador.cadastrarCliente("11111111111", "João");

		String resultado = controlador.adicionarAoCarrinho("11111111111", "COD_INEXISTENTE", 1);
		assertEquals("Item não encontrado: o código COD_INEXISTENTE não existe no inventário.", resultado);
	}

	@Test
	public void testAdicionarAoCarrinhoQuantidadeZero() {
		LojaController controlador = new LojaController();
		controlador.cadastrarCliente("11111111111", "João");
		controlador.adicionarJogoDigital("JD01", "Elden Ring", 250.0, 10, CategoriaItem.JOGO, 45.0, "CHAVE123");

		String resultado = controlador.adicionarAoCarrinho("11111111111", "JD01", 0);
		assertEquals("Quantidade deve ser maior que zero", resultado);
	}

	@Test
	public void testAdicionarAoCarrinhoEstoqueInsuficiente() {
		LojaController controlador = new LojaController();
		controlador.cadastrarCliente("11111111111", "João");
		controlador.adicionarJogoDigital("JD01", "Elden Ring", 250.0, 2, CategoriaItem.JOGO, 45.0, "CHAVE123");

		String resultado = controlador.adicionarAoCarrinho("11111111111", "JD01", 3);
		assertEquals("Estoque insuficiente. O item Elden Ring possui apenas 2 unidades disponiveis.", resultado);
	}

	@Test
	public void testAdicionarAoCarrinhoAcumuloEstoqueInsuficiente() {
		LojaController controlador = new LojaController();
		controlador.cadastrarCliente("11111111111", "João");
		controlador.adicionarJogoDigital("JD01", "Elden Ring", 250.0, 5, CategoriaItem.JOGO, 45.0, "CHAVE123");

		controlador.adicionarAoCarrinho("11111111111", "JD01", 3);
		String resultado = controlador.adicionarAoCarrinho("11111111111", "JD01", 3);

		assertEquals("Estoque insuficiente. O item Elden Ring possui apenas 5 unidades disponiveis.", resultado);
	}

	@Test
	public void testRemoverDoCarrinhoComSucesso() {
		LojaController controlador = new LojaController();
		controlador.cadastrarCliente("11111111111", "João");
		controlador.adicionarJogoDigital("JD01", "Elden Ring", 250.0, 10, CategoriaItem.JOGO, 45.0, "CHAVE123");
		controlador.adicionarAoCarrinho("11111111111", "JD01", 1);

		String resultado = controlador.removerDoCarrinho("11111111111", "JD01");
		assertEquals("Item \"Elden Ring\" removido do carrinho com sucesso.", resultado);
	}

	@Test
	public void testRemoverDoCarrinhoItemNaoEstaNoCarrinho() {
		LojaController controlador = new LojaController();
		controlador.cadastrarCliente("11111111111", "João");
		controlador.adicionarJogoDigital("JD01", "Elden Ring", 250.0, 10, CategoriaItem.JOGO, 45.0, "CHAVE123");

		String resultado = controlador.removerDoCarrinho("11111111111", "JD01");
		assertEquals("O item \"Elden Ring\" não está no carrinho.", resultado);
	}

	@Test
	public void testFinalizarCompraSemCupom() {
		LojaController controlador = new LojaController();
		controlador.cadastrarCliente("11111111111", "João");
		controlador.adicionarJogoDigital("JD01", "Elden Ring", 100.0, 10, CategoriaItem.JOGO, 45.0, "CHAVE123");
		controlador.adicionarAoCarrinho("11111111111", "JD01", 2);

		String resultado = controlador.finalizarCompra("11111111111", null);
		assertEquals("Compra finalizada com sucesso! Total: R$ 200.0", resultado);
	}

	@Test
	public void testFinalizarCompraComCupomFixo() {
		LojaController controlador = new LojaController();
		controlador.cadastrarCliente("11111111111", "João");
		controlador.adicionarJogoDigital("JD01", "Elden Ring", 100.0, 10, CategoriaItem.JOGO, 45.0, "CHAVE123");
		controlador.adicionarAoCarrinho("11111111111", "JD01", 2);

		String resultado = controlador.finalizarCompra("11111111111", "FIX020");
		assertEquals("Compra finalizada com sucesso! Total: R$ 180.0", resultado);
	}

	@Test
	public void testFinalizarCompraComCupomPorcentagem() {
		LojaController controlador = new LojaController();
		controlador.cadastrarCliente("11111111111", "João");
		controlador.adicionarJogoDigital("JD01", "Elden Ring", 100.0, 10, CategoriaItem.JOGO, 45.0, "CHAVE123");
		controlador.adicionarAoCarrinho("11111111111", "JD01", 2);

		String resultado = controlador.finalizarCompra("11111111111", "PROMO10");
		assertEquals("Compra finalizada com sucesso! Total: R$ 180.0", resultado);
	}

	@Test
	public void testFinalizarCompraCupomInvalido() {
		LojaController controlador = new LojaController();
		controlador.cadastrarCliente("11111111111", "João");
		controlador.adicionarJogoDigital("JD01", "Elden Ring", 100.0, 10, CategoriaItem.JOGO, 45.0, "CHAVE123");
		controlador.adicionarAoCarrinho("11111111111", "JD01", 2);

		String resultado = controlador.finalizarCompra("11111111111", "CUPOM_FALSO");
		assertEquals("Cupom inválido: CUPOM_FALSO", resultado);
	}

	@Test
	public void testFinalizarCompraConflitoEstoqueDuploCliente() {
		LojaController controlador = new LojaController();
		controlador.cadastrarCliente("111", "Cliente A");
		controlador.cadastrarCliente("222", "Cliente B");

		controlador.adicionarJogoDigital("JD01", "Elden Ring", 100.0, 1, CategoriaItem.JOGO, 45.0, "CHAVE123");

		controlador.adicionarAoCarrinho("111", "JD01", 1);
		controlador.adicionarAoCarrinho("222", "JD01", 1);

		String resultadoA = controlador.finalizarCompra("111", null);
		assertEquals("Compra finalizada com sucesso! Total: R$ 100.0", resultadoA);

		String resultadoB = controlador.finalizarCompra("222", null);
		assertEquals("Estoque insuficiente para o item: Elden Ring", resultadoB);
	}

	@Test
	public void testListarHistoricoComprasVazio() {
		LojaController controlador = new LojaController();
		controlador.cadastrarCliente("11111111111", "João");

		String resultado = controlador.listarHistoricoCompras("11111111111");
		assertEquals("Nenhuma compra registrada para o cliente João.", resultado);
	}

	@Test
	public void testListarHistoricoComprasAposVenda() {
		LojaController controlador = new LojaController();
		controlador.cadastrarCliente("11111111111", "João");
		controlador.adicionarJogoDigital("JD01", "Elden Ring", 250.0, 10, CategoriaItem.JOGO, 45.0, "CHAVE123");
		controlador.adicionarAoCarrinho("11111111111", "JD01", 2);

		controlador.finalizarCompra("11111111111", null);

		String resultado = controlador.listarHistoricoCompras("11111111111");
		assertTrue(resultado.contains("Histórico de Compras - João:"));
		assertTrue(resultado.contains("Total: R$ 500.0"));
		assertTrue(resultado.contains("Elden Ring x2"));
	}

	@Test
	public void testListarHistoricoComprasClienteInexistente() {
		LojaController controlador = new LojaController();
		String resultado = controlador.listarHistoricoCompras("99999999999");
		assertEquals("Cliente com CPF 99999999999 não cadastrado no sistema.", resultado);
	}

	@Test
	public void testListarItensVazio() {
		LojaController controlador = new LojaController();
		assertEquals("Nenhum item cadastrado.", controlador.listarItens());
	}

	@Test
	public void testListarItensPreenchido() {
		LojaController controlador = new LojaController();
		controlador.adicionarJogoDigital("JD01", "Elden Ring", 250.0, 10, CategoriaItem.JOGO, 45.0, "CHAVE123");

		String resultado = controlador.listarItens();
		assertTrue(resultado.contains("Codigo: JD01"));
		assertTrue(resultado.contains("Nome: Elden Ring"));
		assertTrue(resultado.contains("Tamanho do download: 45.0GB"));
	}

	@Test
	public void testListarClientesVazio() {
		LojaController controlador = new LojaController();
		assertEquals("Nenhum cliente cadastrado.", controlador.listarClientes());
	}

	@Test
	public void testListarClientesPreenchido() {
		LojaController controlador = new LojaController();
		controlador.cadastrarCliente("11111111111", "João");

		String esperado = "CPF: 11111111111\nNome: João\nCarrinho:\n  (vazio)";
		assertEquals(esperado, controlador.listarClientes());
	}
	
	@Test
	public void testProcessarVenda() {
		LojaController controlador = new LojaController();
		controlador.cadastrarCliente("11111111", "Jose");
		controlador.cadastrarCliente("22222222", "Maria");

		controlador.adicionarJogoDigital("JD01", "Elden Ring", 250.0, 1, CategoriaItem.JOGO, 45.0, "CHAVE123");
		controlador.adicionarAoCarrinho("11111111", "JD01", 1);
		controlador.adicionarAoCarrinho("22222222", "JD01", 1);
		
		 String resultado1 = controlador.finalizarCompra("11111111", null);
		 String resultado2 = controlador.finalizarCompra("22222222", null);
		 
		 assertTrue(resultado1.contains("sucesso"));
		 assertTrue(resultado2.contains("insuficiente"));


	}
}