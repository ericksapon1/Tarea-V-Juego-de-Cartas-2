import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Juego {
    private List<Jugador> jugadores;
    private List<Cartas> cartas;
    private int numeroRondas;

    public Juego(int numeroJugadores, int numeroRondas) {
        this.jugadores = crearJugadores(numeroJugadores);
        this.numeroRondas = numeroRondas;
    }

    private List<Jugador> crearJugadores(int numeroJugadores) {
        List<Jugador> jugadores = new ArrayList<>();
        for (int i = 0; i < numeroJugadores; i++) {
            jugadores.add(new Jugador("Jugador" + (i + 1), 50)); // Nombres de jugadores empezando en 1
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
        Collections.shuffle(cartas);
    }

    private void repartirCartas() {
        for (Jugador jugador : jugadores) {
            for (int i = 0; i < 5; i++) {
                jugador.agregarCarta(cartas.remove(0));
            }
        }
    }

    private void limpiarcartas() {
        for (Jugador jugador : jugadores) {
            jugador.borrarCartas();
        }
    }

    public void jugar(){
        for (int ronda = 0; ronda < numeroRondas; ronda++){
            System.out.println("Ronda: " + (ronda + 1));
            this.cartas = crearBaraja();
            barajar();
            repartirCartas();
            mostarCartas();
            jugarCarta();
        }
        determinarGanadorFinal();
    }

    private void mostarCartas(){
        for (Jugador jugador: jugadores){
            System.out.println(jugador.getNombre() + ": " + jugador.getCartas());
        }
    }

    private void jugarCarta(){
        Jugador ganadorDeRonda = null;
        int maxValorCarta = -1;

        for(Jugador jugador : jugadores){
            Cartas cartaJugadora = jugador.jugarCarta();
            int valorCarta = cartaJugadora.getValorNumerico();
            System.out.println("Jugador " + jugador.getNombre() + " juega la carta " + cartaJugadora);

            if(valorCarta > maxValorCarta){
                maxValorCarta = valorCarta;
                ganadorDeRonda = jugador;
            }
        }

        if (ganadorDeRonda != null) {
            ganadorDeRonda.sumarPuntos(1);
            System.out.println("Ganador de la ronda: " + ganadorDeRonda.getNombre());
        }
    }

    private void determinarGanadorFinal() {
        Jugador ganadorFinal = null;
        int maxPunteo = -1;

        for (Jugador jugador : jugadores) {
            if (jugador.getPunteo() > maxPunteo) {
                maxPunteo = jugador.getPunteo();
                ganadorFinal = jugador;
            }
        }

        if (ganadorFinal != null) {
            System.out.println("El ganador del juego es: " + ganadorFinal.getNombre() + " con " + maxPunteo + " puntos.");
        } else {
            System.out.println("No hay un ganador claro del juego.");
        }
    }
}
