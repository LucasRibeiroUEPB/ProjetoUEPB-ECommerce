package model;

public class Descontoporporcentagem implements Calculodesconto {

    private double porcentagem;

    public Descontoporporcentagem(double porcentagem) {
        this.porcentagem = porcentagem;
    }

    @Override
    public double aplicarDesconto(double valorTotal) {
        return valorTotal - (valorTotal * (porcentagem / 100.0));
    }
}