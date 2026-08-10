package model;

public class DescontoCupomFixo implements CalculoDesconto {

    private double valorDesconto;

    public DescontoCupomFixo(double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    @Override
    public double aplicarDesconto(double valorTotal) {
        double resultado = valorTotal - valorDesconto;
        return Math.max(resultado, 0.0);
    }
}