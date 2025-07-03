//INSERTIONSORT
/*
 - Confronto il secondo con il primo e se è minore lo scambio
 - Confronto il terzo con il secondo e poi con il primo
 - Confronto il quarto con il terzo, poi con il secondo e poi con il primo ecc
 */

public class InsertionSort {
    /**
     * Algoritmo di Insertion Sort
     * @param v      --> vettore da ordinare
     * @param inizio --> primo elemento del vettore da ordinare
     * @param fine   --> secondo elemento del vettore da ordinare
     */
    private static void insertionSort(int[] v, int inizio, int fine) {
        for (int i = inizio + 1; i <= fine; i++) {
            int n_corrente = v[i]; //Salvo il valore del numero corrente in sta variabile
            int j = i - 1; //indice dell'elemento precedente all'elemento corrente
            //Sposto elementi maggiori del numero corrente verso destra finchè non trova la pos giusta per inserire il numero corrente
            while (j >= inizio && v[j] > n_corrente) {
                v[j + 1] = v[j]; //Sposto elemento precedente verso destra per far spazio all'elemento corrente
                j--; //Passo al numero precedente
            }
            v[j + 1] = n_corrente; //Inserisco il numero corrente nella posizione coretta
        }
    }

    public static void main(String[] args) {
        //Dichiarazioni
        final int SIZE_V = 5;
        final boolean DEBUG = true;
        int[] v;
        v = new int[SIZE_V];

        //Riempio il vettore
        for (int i = 0; i < SIZE_V; i++) {
            v[i] = (int) (Math.random() * 100);
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
        insertionSort(v, 0, SIZE_V - 1);
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