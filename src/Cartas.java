public class Cartas {
    private String valor; // Valor que tiene la carta, As, 2, 3, 4, etc.
    private String simbolo; // Corazones, Espadas, Trebol y Diamante

    // Método Constructor
    public Cartas(String valor, String simbolo) {
        this.valor = valor;
        this.simbolo = simbolo;
    }

    // Obtiene el valor de la carta
    public String getValor() {
        return valor;
    }

    // Obtiene el símbolo de la carta
    public String getSimbolo() {
        return simbolo;
    }

    // Obtiene el valor numérico de la carta
    public int getValorNumerico() {
        switch (valor) {
            case "As": // Valor numérico devuelto para el As
                return 11;
            case "J": // Valor numérico devuelto para J
            case "Q": // Valor numérico devuelto para Q
            case "K": // Valor numérico devuelto para K
                return 10;
            default: // Valor numérico devuelto para las cartas que tienen número
                return Integer.parseInt(valor);
        }
    }
    @Override
    public String toString() {
        return valor + " de " + simbolo;
    }
}
