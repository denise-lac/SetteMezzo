import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Giochiamo a 7 e mezzo.");
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        boolean continua = true;

        while (continua) {
            boolean condizioneGiocatore = true;
            double punteggioGiocatore = 0;

            while (condizioneGiocatore && punteggioGiocatore < 7.5) {
                int cartaGiocatore = random.nextInt(10) + 1;

                System.out.println(cartaGiocatore);

                if (cartaGiocatore >= 8) {
                    punteggioGiocatore += 0.5;
                } else {
                    punteggioGiocatore += cartaGiocatore;
                }

                System.out.println("Il tuo punteggio è: " + punteggioGiocatore);

                if (punteggioGiocatore < 7.5) {
                    System.out.println("Vuoi un'altra carta? (s/n)");
                    String scelta = sc.nextLine();
                    if (scelta.equalsIgnoreCase("n")) {
                        System.out.println("Hai deciso di fermarti.");
                        condizioneGiocatore = false;
                    } else {
                        System.out.println("Ecco un'altra carta");
                    }
                } else if (punteggioGiocatore == 7.5) {
                    System.out.println("Hai fatto 7 e mezzo!");
                    condizioneGiocatore = false;
                } else {
                    System.out.println("Hai sballato!");
                    condizioneGiocatore = false;
                }

            }

            System.out.println("Vuoi giocare ancora?: (s/n)");
            String scelta_rigiocare = sc.nextLine();
            if (scelta_rigiocare.equalsIgnoreCase("s")) {
                System.out.println("Hai scelto di fare un'altra partita");
            } else {
                System.out.println("Grazie per aver utilizzato il gioco");
                continua = false;
            }

        }
        sc.close();
    }
}
