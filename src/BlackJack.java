import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BlackJack {
    private List<Jugador> jugadores;
    private List<Cartas> baraja;

    public BlackJack(int numeroJugadores) {
        this.jugadores = crearJugadores(numeroJugadores);
    }

    private List<Jugador> crearJugadores(int numeroJugadores) {
        List<Jugador> jugadores = new ArrayList<>();
        for (int i = 0; i < numeroJugadores; i++) {
            jugadores.add(new Jugador("Jugador" + (i + 1), 50));
        }
        return jugadores;
    }

    public List<Cartas> crearBaraja() {
        List<Cartas> baraja = new ArrayList<>();
        String[] simbolos = {"Corazón", "Diamante", "Trébol", "Espadas"};
        String[] valores = {"As", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        for (String simbolo : simbolos) {
            for (String valor : valores) {
                Cartas carta = new Cartas(valor, simbolo);
                baraja.add(carta);
            }
        }
        return baraja;
    }

    private void barajar() {
        Collections.shuffle(baraja);
    }

    private void repartirCartas() {
        for (Jugador jugador : jugadores) {
            jugador.agregarCarta(baraja.remove(0));
            jugador.agregarCarta(baraja.remove(0));
        }
    }

    public void jugar() {
        Scanner sc = new Scanner(System.in);
        this.baraja = crearBaraja();
        barajar();
        repartirCartas();

        for (Jugador jugador : jugadores) {
            boolean seguirJugando = true;

            while (seguirJugando) {
                System.out.println(jugador.getNombre() + ", tus cartas son: " + jugador.getCartas());
                System.out.println("Tu puntuación actual es: " + calcularPuntuacion(jugador));

                if (calcularPuntuacion(jugador) == 21) {
                    System.out.println("¡BlackJack! ¡Ganaste!");
                    seguirJugando = false;
                    break;
                }

                System.out.println("¿Deseas pedir otra carta? (si/no)");
                String respuesta = sc.next();

                if (respuesta.equalsIgnoreCase("si")) {
                    jugador.agregarCarta(baraja.remove(0));
                    int puntuacion = calcularPuntuacion(jugador);

                    if (puntuacion > 21) {
                        System.out.println("Te has pasado de 21. ¡Perdiste!");
                        seguirJugando = false;
                    }
                } else {
                    seguirJugando = false;
                }
            }
        }

        determinarGanador();
    }

    private int calcularPuntuacion(Jugador jugador) {
        int total = 0;
        int ases = 0;

        for (Cartas carta : jugador.getCartas()) {
            int valor = carta.getValorNumerico();
            if (valor == 11) {
                ases++;
            }
            total += valor;
        }

        // Ajuste para los Ases
        while (total > 21 && ases > 0) {
            total -= 10;
            ases--;
        }

        return total;
    }

    private void determinarGanador() {
        Jugador ganador = null;
        int mejorPuntuacion = 0;

        for (Jugador jugador : jugadores) {
            int puntuacion = calcularPuntuacion(jugador);
            if (puntuacion > mejorPuntuacion && puntuacion <= 21) {
                mejorPuntuacion = puntuacion;
                ganador = jugador;
            }
        }

        if (ganador != null) {
            System.out.println("El ganador es " + ganador.getNombre() + " con una puntuación de " + mejorPuntuacion + "!");
        } else {
            System.out.println("Nadie ganó esta vez.");
        }
    }
}
