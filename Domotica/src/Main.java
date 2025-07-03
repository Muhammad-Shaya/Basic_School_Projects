import java.util.Scanner;

/*
 - Scegliere il colore di una lampadina
 - Modificare il colore di una lampadina
 - Accendere e spegnere una lampadina
 - Spegnere tutte le lampadine
 - Posizionare la lampadina in base alla stanza data in input
 */

public class Main {
    private static void menu(){
        System.out.println("-MENU:");
        System.out.println("1) Inserisci una nuova lampadina");
        System.out.println("2) Modifica l'intensità di una lampadina");
        System.out.println("3) Accendere una lampadina");
        System.out.println("4) Spegnere una lampadina");
        System.out.println("5) Spegnere tutte le lampadine");
        System.out.println("6) Modifica il colore di una lampadina");
        System.out.println("0) Esci" + "\n");
    }

    public static void main(String[] args) {
        Picture planimetria = new Picture();
        planimetria.load("pianta.png");
        planimetria.draw();
        Sistema sistema = new Sistema(planimetria);
        int scelta;
        menu();
        System.out.print("Scegli cosa fare: ");
        Scanner in = new Scanner(System.in);
        scelta = in.nextInt();
        while (scelta != 0){
            switch (scelta){
                case 1:
                    String nomeStanza;
                    System.out.print("Inserisci la stanza in cui vuoi creare la lampadina: ");
                    nomeStanza = in.next();
                    sistema.creaLampadinaInStanza(nomeStanza);
                    break;
                case 2:
                    String nomeLampadinaIntensita;
                    System.out.print("Inserisci il nome della lampadina di cui vuoi cambiare l'intensità: ");
                    nomeLampadinaIntensita = in.next();
                    Lampadina cercata;
                    cercata = sistema.cerca(nomeLampadinaIntensita);
                    if (cercata == null){
                        System.out.println("Lampadina non trovata.");
                    }else {
                        System.out.print("Inserisci una nuova intensità (attualmente è al " + cercata.getIntensita() + "%): ");
                        int intensita;
                        intensita = in.nextInt();
                        cercata.setIntensita(intensita);
                    }
                    break;
                case 3:
                    String nomeLamapadinaAccendi;
                    System.out.print("Inserisci il nome della lampadina da accendere: ");
                    nomeLamapadinaAccendi = in.next();
                    Lampadina lampAccendi;
                    lampAccendi = sistema.cerca(nomeLamapadinaAccendi);
                    if (lampAccendi == null){
                        System.out.println("Lampadina non trovata.");
                    }else {
                        lampAccendi.setAccesa(true);
                        System.out.println("Lampadina accesa.");
                    }
                    break;
                case 4:
                    String nomeLampadinaSpegni;
                    System.out.print("Inserisci il nome della lampadina da spegnere: ");
                    nomeLampadinaSpegni = in.next();
                    Lampadina lampSpegni;
                    lampSpegni = sistema.cerca(nomeLampadinaSpegni);
                    if (lampSpegni == null){
                        System.out.println("Lampadina non trovata.");
                    }else {
                        lampSpegni.setAccesa(false);
                        System.out.println("Lampadina spenta.");
                    }
                    break;
                case 5:
                    sistema.spegniTutte();
                    System.out.println("Tutte le lampadine sono spente.");
                    break;
                case 6:
                    String nomeLampColore;
                    System.out.print("Inserisci il nome della lampadina di cui vuoi cambiare il colore: ");
                    nomeLampColore = in.next();
                    Lampadina cercataColore;
                    cercataColore = sistema.cerca(nomeLampColore);
                    if (cercataColore == null){
                        System.out.println("Lampadina non trovata.");
                    }else {
                        System.out.print("Inserisci un nuovo colore (attualmente è " + cercataColore.getNomeColore() + "): ");
                        String col;
                        col = in.next();
                        cercataColore.setColore(sistema.ottieniColore(col));
                    }
                    break;
            }
            System.out.println(sistema);
            sistema.disegna();
            System.out.print("Scegli cosa fare: ");
            scelta = in.nextInt();
        }
        Canvas.getInstance().stop();
    }
}