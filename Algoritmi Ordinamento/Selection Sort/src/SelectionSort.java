//ALGORITMI DI ORDINAMENTO
/*
 - Ci sono varie tecniche di ordinamento dei numeri (in questo caso interi)
 - In questi algoritmi, lo scopo è quello di ordinare i numeri in un vettore in modo crescente (dal più piccolo al più grande)
 */

//SELECTIONSORT
/*
 - Cercare il numero più piccolo, metterlo nella prima posizione dell'attuale primo che andrà nella posizione ricoperta precedentemente dall'elemento più piccolo,
 - Ripeto il procedimento senza considerare il primo numero (n - 1) e poi si continua cosi (e ogni volta decremento di 1 --> (n - 2) (n - 3) ecc)
 - L'ordinamento può essere fatto dal più piccolo al più grande e viceversa (in questo caso in ordine crescente)
 */

//FUNZIONI
/*
 - In java le funzioni si fanno prima del main
 - Math.random() è come il rand() ma il numero che viene inserito dopo con la * indica fino a dove arrivare (il numero fino a dove arrivi è escluso)
 - La funzione di prima è usata solo con i double e per usare un altro tipo di variabile, lo si mette tra le parentesi prima della funzione Math.random()

 - La funzione System.nanoTime() mi dice quanti nano secondi sono passati dall'inizio del programma fino alla sua fine
 - Questa funzione viene usata due volte: una all'inizio del programma (dichiarando una variabile 'tempo_inizio') e una alla fine del programma (tempo_fine)
 - Per calcolare la durata di quanto ci ha messo in totale si fa 'tempo_inizio' - 'tempo_fine' e ti da il risultato in nanosecondi (se lo vuoi in ms basta dividere tutto per 1000000
 */

public class SelectionSort {
    /**
     * Algoritmo di Selection sort
     * @param v --> vettore da ordinare
     * @param inizio --> primo elemento del vettore da ordinare
     * @param fine --> secondo elemento del vettore da ordinare
     */
    private static void selectionSort(int []v, int inizio, int fine){
        for (int i = inizio; i < fine; i++){               //Controllo ogni elemento dell'array e suppongo che l'elemento attuale sia il minimo
            int PosMin = i;
            for (int j = i + 1; j <= fine; j++){           //Inizio dall'elemento dopo
                if (v[j] < v[PosMin])                      //Verifico se l'elemento attuale è minore dell'elemento che si trova con indice PosMin
                    PosMin = j;                            //Aggiorno PosMin con l'indice dell'elemento più piccolo
            }

            //Scambio l'elemento attuale con quello più piccolo trovato
            int temp = v[i];
            v[i] = v[PosMin];
            v[PosMin] = temp;
        }
    }

    public static void main(String[] args) {
        //Dichiarazioni
        final int SIZE_V = 10;                             //'final' sta per const in c++
        final boolean DEBUG = true;                        //Se metti false, non ti stampa nulla
        int []v;                                           //Dichiaro il nome del vettore
        v = new int[SIZE_V];                               //Dichiaro il vero vettore

        //Riempio il vettore
        for (int i = 0; i < SIZE_V; i++){
            v[i] = (int)(Math.random() * 100);             //Riempe l'array di numeri casuali fino a 100 escluso
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
        long inizio = System.nanoTime();                           //Dice quanti nanosecondi sono passati dall'inizio dell'esecuzione del programma fino alla sua fine, (lo si poteva mettere anche all'inizio del main)
        selectionSort(v, 0, SIZE_V - 1);                //Mi parti da 0 e mi arrivi fino a 9
        long fine = System.nanoTime();
        float durata = (fine - inizio) / 1000000;                 //Durata in millisecondi
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