package exception;


public class Validador {

    public static void verificaMenorOuIgualaZero(double valor, String mensagem)
            throws Exception  {

        if (valor <= 0) {
            throw new Exception (mensagem);
        }
    }

    public static void verificarNegativo(double valor, String mensagem)
            throws Exception  {

        if (valor < 0) {
            throw new Exception (mensagem);
        }
    }

    public static void verificarVazio(String valor, String mensagem)
            throws Exception  {

        if (valor == null || valor.trim().isEmpty()) {
            throw new Exception (mensagem);
        }
    }
}