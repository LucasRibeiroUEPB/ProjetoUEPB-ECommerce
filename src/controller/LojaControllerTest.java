package controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import model.CategoriaItem;

public class LojaControllerTest {

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
	public void testAdicionarJogoDigitalValido() {
		LojaController controlador = new LojaController();

		String resultado = controlador.adicionarJogoDigital("JD01", "Elden Ring", 250.0, 10, CategoriaItem.JOGO, 45.0,
				"CHAVE123");

		assertEquals("Jogo digital \"Elden Ring\" adicionado com sucesso.", resultado);
	}

	@Test
	public void testAdicionarColecionavelFisicoValido() {
		LojaController controlador = new LojaController();

		String resultado = controlador.adicionarColecionavelFisico("CF01", "Funko Pop", 89.9, 5,
				CategoriaItem.ACESSORIO, 0.3, "10x10x15cm", "Novo");

		assertEquals("Colecionável físico \"Funko Pop\" adicionado com sucesso.", resultado);
	}

	@Test
	public void testAdicionarAoCarrinhoComClienteInexistente() {
		LojaController controlador = new LojaController();
		controlador.adicionarJogoDigital("JD01", "Elden Ring", 250.0, 10, CategoriaItem.JOGO, 45.0, "CHAVE123");

		String resultado = controlador.adicionarAoCarrinho("11111111111", "JD01", 1);

		assertEquals("Cliente com CPF 11111111111 não cadastrado no sistema.", resultado);
	}

	@Test
	public void testAdicionarAoCarrinhoComItemInexistente() throws Exception {
		LojaController controlador = new LojaController();
		controlador.cadastrarCliente("11111111111", "João");

		String resultado = controlador.adicionarAoCarrinho("11111111111", "COD_INEXISTENTE", 1);

		assertEquals("Item não encontrado: o código COD_INEXISTENTE não existe no inventário.", resultado);
	}

	@Test
	public void testAdicionarAoCarrinhoComSucesso() throws Exception {
		LojaController controlador = new LojaController();
		controlador.cadastrarCliente("11111111111", "João");
		controlador.adicionarJogoDigital("JD01", "Elden Ring", 250.0, 10, CategoriaItem.JOGO, 45.0, "CHAVE123");

		String resultado = controlador.adicionarAoCarrinho("11111111111", "JD01", 2);

		assertEquals("Item \"Elden Ring\" adicionado ao carrinho com sucesso.", resultado);
	}

	@Test
	public void testFinalizarCompraComClienteInexistente() {
		LojaController controlador = new LojaController();

		String resultado = controlador.finalizarCompra("11111111111", null);

		assertEquals("Cliente com CPF 11111111111 não cadastrado no sistema.", resultado);
	}

	@Test
	public void testFinalizarCompraSemCupom() throws Exception {
		LojaController controlador = new LojaController();
		controlador.cadastrarCliente("11111111111", "João");
		controlador.adicionarJogoDigital("JD01", "Elden Ring", 100.0, 10, CategoriaItem.JOGO, 45.0, "CHAVE123");
		controlador.adicionarAoCarrinho("11111111111", "JD01", 2);

		String resultado = controlador.finalizarCompra("11111111111", null);

		assertEquals("Compra finalizada com sucesso! Total: R$ 200.0", resultado);
	}

	@Test
	public void testFinalizarCompraComCupomFixo() throws Exception {
		LojaController controlador = new LojaController();
		controlador.cadastrarCliente("11111111111", "João");
		controlador.adicionarJogoDigital("JD01", "Elden Ring", 100.0, 10, CategoriaItem.JOGO, 45.0, "CHAVE123");
		controlador.adicionarAoCarrinho("11111111111", "JD01", 2);

		String resultado = controlador.finalizarCompra("11111111111", "FIX020");

		assertEquals("Compra finalizada com sucesso! Total: R$ 180.0", resultado);
	}

	@Test
	public void testFinalizarCompraComCupomPorcentagem() throws Exception {
		LojaController controlador = new LojaController();
		controlador.cadastrarCliente("11111111111", "João");
		controlador.adicionarJogoDigital("JD01", "Elden Ring", 100.0, 10, CategoriaItem.JOGO, 45.0, "CHAVE123");
		controlador.adicionarAoCarrinho("11111111111", "JD01", 2);

		String resultado = controlador.finalizarCompra("11111111111", "PROMO10");

		assertEquals("Compra finalizada com sucesso! Total: R$ 180.0", resultado);
	}

	@Test
	public void testFinalizarCompraComCupomInvalidoRetornaErro() throws Exception {
		LojaController controlador = new LojaController();
		controlador.cadastrarCliente("11111111111", "João");
		controlador.adicionarJogoDigital("JD01", "Elden Ring", 100.0, 10, CategoriaItem.JOGO, 45.0, "CHAVE123");
		controlador.adicionarAoCarrinho("11111111111", "JD01", 2);

		String resultado = controlador.finalizarCompra("11111111111", "CUPOM_QUE_NAO_EXISTE");

		assertEquals("Cupom inválido: CUPOM_QUE_NAO_EXISTE", resultado);
	}

	@Test
	public void testNaoRegistrarItemComCodigoDuplicado() {
		LojaController controlador = new LojaController();

		String primeiroCadastro = controlador.adicionarJogoDigital("JD01", "Elden Ring", 250.0, 10, CategoriaItem.JOGO,
				45.0, "CHAVE123");
		String segundoCadastro = controlador.adicionarColecionavelFisico("JD01", "Funko Pop", 89.9, 5,
				CategoriaItem.ACESSORIO, 0.3, "10x10x15cm", "Novo");

		assertEquals("Jogo digital \"Elden Ring\" adicionado com sucesso.", primeiroCadastro);
		assertEquals("Já existe um item cadastrado com o código: JD01", segundoCadastro);
	}

	@Test
	public void testFinalizarCompraReduzEstoqueELimpaCarrinho() throws Exception {
		LojaController controlador = new LojaController();
		controlador.cadastrarCliente("11111111111", "João");
		controlador.adicionarJogoDigital("JD01", "Elden Ring", 100.0, 10, CategoriaItem.JOGO, 45.0, "CHAVE123");
		controlador.adicionarAoCarrinho("11111111111", "JD01", 2);

		controlador.finalizarCompra("11111111111", null);

		String resultado = controlador.adicionarAoCarrinho("11111111111", "JD01", 2);
		assertTrue(resultado.contains("sucesso"));
	}

	@Test
	public void testListarItensSemCadastros() {
		LojaController controlador = new LojaController();

		assertEquals("Nenhum item cadastrado.", controlador.listarItens());
	}

	@Test
	public void testListarItensComUmCadastro() {
		LojaController controlador = new LojaController();
		controlador.adicionarJogoDigital("JD01", "Elden Ring", 250.0, 10, CategoriaItem.JOGO, 45.0, "CHAVE123");

		String esperado = "Codigo: JD01\nNome: Elden Ring\nCategoria: JOGO\nPreco base: 250.0\n"
				+ "Preco final: 250.0\nQuantidade em estoque: 10\nTamanho do download: 45.0GB\n"
				+ "Chave de ativacao: CHAVE123";

		assertEquals(esperado, controlador.listarItens());
	}

	@Test
	public void testListarClientesSemCadastros() {
		LojaController controlador = new LojaController();

		assertEquals("Nenhum cliente cadastrado.", controlador.listarClientes());
	}

	@Test
	public void testListarClientesComCarrinhoVazio() throws Exception {
		LojaController controlador = new LojaController();
		controlador.cadastrarCliente("11111111111", "João");

		String esperado = "CPF: 11111111111\nNome: João\nCarrinho:\n  (vazio)";

		assertEquals(esperado, controlador.listarClientes());
	}

	@Test
	public void testListarClientesComItemNoCarrinho() throws Exception {
		LojaController controlador = new LojaController();
		controlador.cadastrarCliente("11111111111", "João");
		controlador.adicionarJogoDigital("JD01", "Elden Ring", 250.0, 10, CategoriaItem.JOGO, 45.0, "CHAVE123");
		controlador.adicionarAoCarrinho("11111111111", "JD01", 2);

		String esperado = "CPF: 11111111111\nNome: João\nCarrinho:\n  Elden Ring x2";

		assertEquals(esperado, controlador.listarClientes());
	}

	@Test
	public void testRemoverDoCarrinhoComSucesso() throws Exception {
		LojaController controlador = new LojaController();
		controlador.cadastrarCliente("11111111111", "João");
		controlador.adicionarJogoDigital("JD01", "Elden Ring", 250.0, 10, CategoriaItem.JOGO, 45.0, "CHAVE123");

		controlador.adicionarAoCarrinho("11111111111", "JD01", 1);

		String resultado = controlador.removerDoCarrinho("11111111111", "JD01");

		assertEquals("Item \"Elden Ring\" removido do carrinho com sucesso.", resultado);
	}

	@Test
	public void testRemoverDoCarrinhoItemNaoEstaNoCarrinho() throws Exception {
		LojaController controlador = new LojaController();
		controlador.cadastrarCliente("11111111111", "João");
		controlador.adicionarJogoDigital("JD01", "Elden Ring", 250.0, 10, CategoriaItem.JOGO, 45.0, "CHAVE123");

		String resultado = controlador.removerDoCarrinho("11111111111", "JD01");

		assertEquals("O item \"Elden Ring\" não está no carrinho.", resultado);
	}

	@Test
	public void testRemoverDoCarrinhoComClienteInexistente() {
		LojaController controlador = new LojaController();
		controlador.adicionarJogoDigital("JD01", "Elden Ring", 250.0, 10, CategoriaItem.JOGO, 45.0, "CHAVE123");

		String resultado = controlador.removerDoCarrinho("99999999999", "JD01");

		assertEquals("Cliente com CPF 99999999999 não cadastrado no sistema.", resultado);
	}

	@Test
	public void testRemoverDoCarrinhoComItemInexistente() {
		LojaController controlador = new LojaController();
		controlador.cadastrarCliente("11111111111", "João");

		String resultado = controlador.removerDoCarrinho("11111111111", "COD_INEXISTENTE");

		assertEquals("Item não encontrado: o código COD_INEXISTENTE não existe no inventário.", resultado);
	}
}
