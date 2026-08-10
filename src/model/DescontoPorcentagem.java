package model;

public class DescontoPorcentagem implements CalculoDesconto {

	private double porcentagem;

	public DescontoPorcentagem(double porcentagem) {
		this.porcentagem = porcentagem;
	}

	@Override
	public double aplicarDesconto(double valorTotal) {
		return valorTotal - (valorTotal * (porcentagem / 100.0));
	}
}