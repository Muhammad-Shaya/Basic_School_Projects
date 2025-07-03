import java.util.Scanner;
public class Sistema {
    private Lampadina []lampadine;
    private int n_lampadine;
    private int max_lampadine;
    private Picture sfondo;
    private Stanza salaPranzo = new Stanza("pranzo", 120, 200);
    private Stanza cucina = new Stanza("cucinotto", 100, 425);
    private Stanza ingresso = new Stanza("ingresso", 210, 470);
    private Stanza bagno = new Stanza("bagno", 320, 200);
    private Stanza camera = new Stanza("camera", 520, 200);
    private Stanza balcone = new Stanza("balcone", 470, 28);


    public Sistema(Picture p){
        this.max_lampadine = 100;
        this.lampadine = new Lampadina[this.max_lampadine];
        this.n_lampadine = 0;
        this.sfondo = p;
    }

    public Sistema(int max_lampadine){
        this.max_lampadine = max_lampadine;
        if (this.max_lampadine <= 1 || this.max_lampadine > 1000)
            this.max_lampadine = 100;
        this.lampadine = new Lampadina[this.max_lampadine];
        this.n_lampadine = 0;
    }

    //Metodi per trovare la coordinate X e Y di una stanza
    public int trovaXStanza(String nomeStanza) {
        if (nomeStanza.equals(salaPranzo.getNome()))
            return salaPranzo.getX();
        else if (nomeStanza.equals(cucina.getNome()))
            return cucina.getX();
        else if (nomeStanza.equals(ingresso.getNome()))
            return ingresso.getX();
        else if (nomeStanza.equals(bagno.getNome()))
            return bagno.getX();
        else if (nomeStanza.equals(camera.getNome()))
            return camera.getX();
        else if (nomeStanza.equals(balcone.getNome()))
            return balcone.getX();
        return -1;
    }

    public int trovaYStanza(String nomeStanza) {
        if (nomeStanza.equals(salaPranzo.getNome()))
            return salaPranzo.getY();
        else if (nomeStanza.equals(cucina.getNome()))
            return cucina.getY();
        else if (nomeStanza.equals(ingresso.getNome()))
            return ingresso.getY();
        else if (nomeStanza.equals(bagno.getNome()))
            return bagno.getY();
        else if (nomeStanza.equals(camera.getNome()))
            return camera.getY();
        else if (nomeStanza.equals(balcone.getNome()))
            return balcone.getY();
        return -1;
    }

    public void creaLampadinaInStanza(String nomeStanza) {
        int x = trovaXStanza(nomeStanza);
        int y = trovaYStanza(nomeStanza);

        if (x != -1 && y != -1) {
            String nome;
            int potenza;
            Color colore;

            Scanner in = new Scanner(System.in);
            System.out.print("Scegli il nome della lampadina: ");
            nome = in.next();
            System.out.print("Inserisci la potenza: ");
            potenza = in.nextInt();
            System.out.print("Inserisci il colore della lampadina: ");
            String nomeColore = in.next();
            colore = ottieniColore(nomeColore);
            Lampadina temp = new Lampadina(potenza, colore, nome);
            aggiungiLampadina(temp, x, y);
        } else {
            System.out.println("Stanza non trovata.");
        }
    }

    public boolean aggiungiLampadina(Lampadina l, int x, int y){
        if (this.n_lampadine == this.max_lampadine) {
            return false;
        }
        l.setPosizione(x, y);
        this.lampadine[this.n_lampadine] = l;
        this.n_lampadine++;
        return true;
    }

    public Color ottieniColore(String colore) {
        switch (colore.toLowerCase()) {
            case "rosso":
                return Color.RED;
            case "verde":
                return Color.GREEN;
            case "blu":
                return Color.BLUE;
            case "nero":
                return Color.BLACK;
            case "rosa":
                return Color.PINK;
            case "arancione":
                return Color.ORANGE;
            case "ciano":
                return Color.CYAN;
            case "magenta":
                return Color.MAGENTA;
            case "grigio":
                return Color.GRAY;
            default:
                return Color.YELLOW;
        }
    }

    public Lampadina cerca(String nome){
        for (int i = 0; i < this.n_lampadine; i++) {
            Lampadina l = this.lampadine[i];
            String nomeLampadina = l.getNome();
            if (nomeLampadina.equals(nome))
                return l;
        }
        return null;
    }

    public void spegniTutte(){
        for (int i = 0; i < this.n_lampadine; i++){
            this.lampadine[i].setAccesa(false);
        }
    }

    @Override
    public String toString(){
        String s;
        s = "Attualmente nel sistema ci sono " + this.n_lampadine + " lampadine: " + "\n";
        for (int i = 0; i < this.n_lampadine; i++) {
            s += this.lampadine[i].toString() + "\n";
        }
        return s;
    }

    public void disegna(){
        this.sfondo.draw();
        for (int i = 0; i < this.n_lampadine; i++) {
            this.lampadine[i].disegna();
        }
        Canvas.getInstance().repaint();
    }
}