import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Giochiamo a 7 e mezzo.");
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        boolean continua = true;
        String[] seme = { "Denari", "Coppe", "Spade", "Bastoni" };
        double budget = 100;

        while (continua) {

            if (budget > 0) {
                System.out.println("Quanto vorresti puntare? il tuo budget è di " + budget);
                double puntata = sc.nextDouble();
                sc.nextLine();
                if (puntata <= budget) {
                    boolean condizioneGiocatore = true;
                    boolean[][] controllo = new boolean[10][4];

                    // inizializzo i punteggi
                    double punteggioGiocatore = 0;
                    double punteggioPC = 0;

                    boolean giocatoreSballato = false;

                    // turno giocatore
                    while (condizioneGiocatore && punteggioGiocatore < 7.5) {
                        int indiceSemeGiocatore;
                        int cartaGiocatore;
                        do {
                            indiceSemeGiocatore = random.nextInt(seme.length);
                            cartaGiocatore = random.nextInt(10) + 1;
                        } while (controllo[cartaGiocatore - 1][indiceSemeGiocatore]);

                        String semeGiocatore = seme[indiceSemeGiocatore];

                        System.out.println("Il giocatore ha pescato il " + cartaGiocatore + " di " + semeGiocatore);

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
                            giocatoreSballato = true;
                            condizioneGiocatore = false;
                        }

                    }

                    // Turno del banco (se il giocatore non ha sballato)
                    if (!giocatoreSballato) {

                        System.out.println("Turno del banco:");

                        while (punteggioPC < 7.5 && punteggioPC < punteggioGiocatore) {
                            int indiceSemePC;
                            int cartaPC;
                            do {
                                indiceSemePC = random.nextInt(seme.length);
                                cartaPC = random.nextInt(10) + 1;
                            } while (controllo[cartaPC - 1][indiceSemePC]);

                            String semePC = seme[indiceSemePC];

                            System.out.println("Il PC ha pescato " + cartaPC + " di " + semePC);

                            if (cartaPC >= 8) {
                                punteggioPC += 0.5;
                            } else {
                                punteggioPC += cartaPC;
                            }

                            System.out.println("Il punteggio del PC è: " + punteggioPC);

                            if (punteggioPC > 7.5) {
                                System.out.println("Il PC ha sballato!");
                                break;
                            } else if (punteggioPC == 7.5) {
                                System.out.println("Il PC ha fatto 7 e mezzo!");
                                break;
                            } else if (punteggioPC > punteggioGiocatore && punteggioPC < 7.5) {
                                System.out.println("Il PC ha deciso di fermarsi");
                            }

                        }

                        // verifichiamo chi ha vinto la partita
                        if ((punteggioPC <= 7.5 && punteggioGiocatore <= 7.5) || punteggioPC == punteggioGiocatore) {

                            if (punteggioGiocatore > punteggioPC) {
                                System.out.println("Il giocatore ha vinto la partita");
                                budget += puntata;

                            } else if (punteggioGiocatore == punteggioPC) {
                                System.out.println("Pareggio");
                            } else {
                                System.out.println("Il PC ha vinto");
                                budget -= puntata;
                            }

                        } else if (punteggioGiocatore > 7.5) {
                            System.out.println("Il pc ha vinto la partita");
                            budget -= puntata;

                        } else {
                            System.out.println("Il giocatore ha vinto la partita");
                            budget += puntata;
                        }

                        // nuova partita
                        System.out.println("Vuoi giocare ancora?: (s/n)");
                        String scelta_rigiocare = sc.nextLine();
                        if (scelta_rigiocare.equalsIgnoreCase("n")) {
                            System.out.println("Grazie per aver utilizzato il gioco");
                            continua = false;
                        }

                    }
                } else {
                    System.out.println("La tua puntata è maggiore del tuo saldo");
                }

            } else {
                System.out.println("Non hai abbastanza soldi");
                continua = false;
            }

        }
        sc.close();
    }
}
