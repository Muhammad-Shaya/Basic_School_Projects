public class Lampadina {
    private int potenza;
    private Color colore;
    private int intensita;
    private boolean accesa;
    private String nome;
    private int x, y;
    private Ellipse e;
    private Text t;

    //private Rectangle r;

    public Lampadina(){
        this.potenza = 10;
        this.intensita = 100;
        this.x = (int)(Math.random() * 400);
        this.y = (int)(Math.random() * 400);
        e = new Ellipse(this.x, this.y, potenza, potenza);
        /*
        r = new Rectangle(this.x + e.getWidth()/2 - 5, this.y + e.getHeight(), 10, 20);
        r.setColor(Color.BLACK);
        */
        t = new Text(this.x, this.y + 30 , this.nome);
    }

    public Lampadina(int potenza, Color colore, String nome){
        this.setPotenza(potenza);
        this.accesa = true;
        this.colore = colore;
        this.nome = nome;
        this.intensita = 100;
        this.x = (int)(Math.random() * 400);
        this.y = (int)(Math.random() * 400);
        e = new Ellipse(this.x, this.y, this.potenza, this.potenza);
        /*
        r = new Rectangle(this.x + e.getWidth()/2 - 5, this.y + e.getHeight(), 10, 20);
        r.setColor(Color.BLACK);
        */
        t = new Text(this.x, this.y + 30 , this.nome);
    }

    public String getNomeColore() {
        if (this.colore.equals(Color.RED))
            return "rosso";
        else if (this.colore.equals(Color.GREEN))
            return "verde";
        else if (this.colore.equals(Color.BLUE))
            return "blu";
        else if (this.colore.equals(Color.BLACK))
            return "nero";
        else if (this.colore.equals(Color.PINK))
            return "rosa";
        else if (this.colore.equals(Color.ORANGE))
            return "arancione";
        else if (this.colore.equals(Color.CYAN))
            return "ciano";
        else if (this.colore.equals(Color.MAGENTA))
            return "magenta";
        else if (this.colore.equals(Color.GRAY))
            return "grigio";
        else
            return "giallo";
    }

    public int getIntensita(){
        return this.intensita;
    }

    public void setIntensita(int intensita) {
        this.intensita = intensita;
        if (this.intensita > 100 || this.intensita < 1)
            this.intensita = 50;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPosizione(int x, int y){
        e.translate(x - this.x, y - this.y);
        t.translate(x - this.x, y - this.y);
        //r.translate(x - this.x, y - this.y);
        this.x = x;
        this.y = y;
    }

    public String getNome() {
        return this.nome;
    }

    public void setAccesa(boolean accesa) {
        this.accesa = accesa;
    }

    public int getPotenza() {
        return this.potenza;
    }

    public void setPotenza(int potenza){
        this.potenza = potenza;
        if (this.potenza > 100 || this.potenza < 1)
            this.potenza = 20;
    }

    public void setColore(Color colore) {
        this.colore = colore;
    }

    @Override
    public String toString(){
        if (this.accesa) {
            return "La lampadina " + this.nome + " ha una potenza di " + this.potenza + ", è di colore " + this.getNomeColore() + ", ha l'intensità di " + this.intensita + "%, si trova in posizione (" +
                    getX() + ", " + getY() + ") e attualmente è accesa.";
        }else {
            return "La lampadina " + this.nome + " ha una potenza di " + this.potenza + ", è di colore " + this.getNomeColore() + ", ha l'intensità di " + this.intensita + "%, si trova in posizione (" +
                    getX() + ", " + getY() + ") e attualmente è spenta.";
        }
    }
    public void disegna(){
        if (this.accesa)
            this.e.setColor(this.colore);
        else
            this.e.setColor(Color.WHITE);
        this.e.fill();
        //this.r.fill();
        this.t.draw();
    }
}