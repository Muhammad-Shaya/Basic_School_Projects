//BUBBLESORT
/*
 - Prendo l'ultimo numero, lo confronto con quello prima e se è minore allora li scambio di posto e se è maggiore li lascio cosi
 - Ripeto la stessa cosa con gli altri numero nel vettore andando man mano indietro
 - Una volta che avrò finito il primo giro, ne faccio un altro con lo stesso procedimento però senza contare il numero che si trova in posizione v[0] (n - 1)
 - Dopo il secondo giro, ne faccio un altro (n - 2) e continuo cosi finchè il vettore non è ordinato
 - Se non ci sono stati scambi nei N passaggi del primo giro allora fermo l'algoritmo perchè è gia ordinato
 */

public class BubbleSort {
    /**
     * Algoritmo di Bubble sort
     * @param v --> vettore da ordinare
     * @param inizio --> primo elemento del vettore da ordinare
     * @param fine --> secondo elemento del vettore da ordinare
     */
    private static void bubbleSort(int []v, int inizio, int fine){
        boolean scambiato; //Per tener traccia se ci sono stati scambi durante l'iterazione, inoltre, è una condizione di uscita dal ciclo quando l'array è gia ordinato
        do {
            scambiato = false;
            for (int i = fine; i > inizio; i--) { //Dalla fine all'inizio
                if (v[i] < v[i - 1]) { //Se l'elemento corrente è minore di quello precedente, li scambio
                    int temp = v[i];
                    v[i] = v[i - 1];
                    v[i - 1] = temp;
                    scambiato = true; //Ce stato uno scambio
                }
            }
            //Aumento l'inizio di un elemento visto che l'elemento più piccolo è ora all'inizio
            inizio++;
        } while (scambiato);
    }

    public static void main(String[] args) {
        //Dichiarazioni
        final int SIZE_V = 10;
        final boolean DEBUG = true;
        int []v;
        v = new int[SIZE_V];

        //Riempio il vettore
        for (int i = 0; i < SIZE_V; i++){
            v[i] = (int)(Math.random() * 100);
        }

        //Stampa del vettore disordinato
        if (DEBUG) {
            System.out.println("Vettore disordinato:");
            for (int i = 0; i < SIZE_V; i++) {
                System.out.print(v[i] + " ");
            }
        }
        System.out.println();
        System.out.println();

        //Misura della velocità di esecuzione del programma
        long inizio = System.nanoTime();
        bubbleSort(v, 0, SIZE_V - 1);
        long fine = System.nanoTime();
        float durata = (fine - inizio) / 1000000;
        System.out.println("Il tempo impiegato dal SelectionSort per ordinare " + SIZE_V + " elementi è di " + durata + " ms.");
        System.out.println();

        //Stampa del vettore ordinato
        if (DEBUG) {
            System.out.println("Vettore ordinato:");
            for (int i = 0; i < SIZE_V; i++) {
                System.out.print(v[i] + " ");
            }
        }
        System.out.println();
    }
}