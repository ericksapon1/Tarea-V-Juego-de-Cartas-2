import java.util.Scanner;

public class JuegoDeCartas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Menú de selección de juego
        System.out.println("Seleccione el juego que desea jugar:");
        System.out.println("1. Carta más alta");
        System.out.println("2. BlackJack");
        int seleccion = sc.nextInt();

        System.out.println("Ingrese el número de jugadores");
        int n = sc.nextInt();

        // Seleccionar el juego
        if (seleccion == 1) {
            System.out.println("Ingrese el número de partidas a jugar");
            int p = sc.nextInt();
            Juego juego = new Juego(n, p);
            juego.jugar();
        } else if (seleccion == 2) {
            BlackJack blackjack = new BlackJack(n);
            blackjack.jugar();
        } else {
            System.out.println("Opción no válida.");
        }
    }
}
