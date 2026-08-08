package model;

public class JogoDigital extends ItemEstoque {
	private double tamanho;
	private String chaveAtivacao;
	
	public JogoDigital(String codigo, String nome, double precoBase, CategoriaItem categoria, double tamanho,
			String chaveAtivacao) {
		super(codigo, nome, precoBase, categoria);
		this.tamanho = tamanho;
		this.chaveAtivacao = chaveAtivacao;
	}

	@Override
	public double calculaPrecoFinal() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	
}
