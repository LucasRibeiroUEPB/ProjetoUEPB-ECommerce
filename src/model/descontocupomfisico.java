package model;

public class descontocupomfisico implements Calculodesconto {

    private double valorDesconto;

    public descontocupomfisico(double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    @Override
    public double aplicarDesconto(double valorTotal) {
        double resultado = valorTotal - valorDesconto;
        return Math.max(resultado, 0.0);
    }
}