package com.example.calcolatrice;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CalculatorController {
    @FXML
    private TextField myTextField;
    @FXML
    private Label numeriSalvati;

    private String numero_corrente = "";
    private String primo_numero = "";
    private String tipo_operazione;

    /**
     * Metodo per mostrare un alert personalizzato
     * @param input indica il tipo di messaggio che viene generato nei diversi casi in cui la funzione viene chiamata
     */
    private void alert (String input){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText(null);
        alert.setContentText(input);
        alert.showAndWait();
    }

    /**
     * Metodo per inizializzare l'operazione da fare
     * @param tipo_operazione indica il tipo di operazione che si andrà a effettuare
     */
    public void inizializzaOperazione(String tipo_operazione){
        //Controllo se è già presente un numero corrente e un primo numero, in caso affermativo eseguo l'operazione corrente e aggiorno il risultato
        if (!numero_corrente.isEmpty()) {
            if (!primo_numero.isEmpty()) {
                onUgualeButtonClick();   //Esegue l'operazione precedente nel caso sia già presente
            } else {
                //Imposto il primo numero e aggiorno l'interfaccia
                primo_numero = numero_corrente;
                numero_corrente = "";
                numeriSalvati.setText(primo_numero + " " + tipo_operazione);
            }
            this.tipo_operazione = tipo_operazione;
        } else {
            alert("Inserisci un numero prima di fare un operazione.");
        }
    }

    @FXML
    protected void onSommaButtonClick() {
        inizializzaOperazione("+");
    }

    @FXML
    protected void onSottrazioneButtonClick() {
        inizializzaOperazione("-");
    }

    @FXML
    protected void onMoltiplicazioneButtonClick() {
        inizializzaOperazione("*");
    }

    @FXML
    protected void onDivisioneButtonClick() {
        inizializzaOperazione("/");
    }

    @FXML
    protected void onEsponenteButtonClick() {
        inizializzaOperazione("^");
    }

    @FXML
    protected void onRadiceButtonClick() {
        inizializzaOperazione("√");
    }

    //Metodo per eseguire l'operazione quando viene premuto il bottone '='
    @FXML
    protected void onUgualeButtonClick() {
        //Verifico se ci sia un primo numero
        if (!primo_numero.isEmpty()) {
            double primoNumero = Double.parseDouble(primo_numero);
            double secondoNumero;
            double esponenteRadice = Double.parseDouble(primo_numero);

            //Verifico se ci sia un numero corrente, senno imposto dei valori predefiniti
            if (!numero_corrente.isEmpty()) {
                secondoNumero = Double.parseDouble(numero_corrente);
            } else {
                if (tipo_operazione.equals("+") || tipo_operazione.equals("-")) {
                    secondoNumero = 0;
                    numero_corrente = "0";
                } else {
                    secondoNumero = 1;
                    numero_corrente = "1";
                }
            }

            switch (tipo_operazione) {
                case "+" -> {
                    double risultato = primoNumero + secondoNumero;
                    aggiornaRisultato(risultato);
                }
                case "-" -> {
                    double risultato = primoNumero - secondoNumero;
                    aggiornaRisultato(risultato);
                }
                case "/" -> {
                    double risultato = primoNumero / secondoNumero;
                    if (Double.isNaN(risultato)) {
                        myTextField.setText("NaN");
                        numeriSalvati.setText(primo_numero + " " + tipo_operazione + " " + numero_corrente + " = " + "NaN");
                    }else if (Double.isInfinite(risultato)){
                        myTextField.setText("Infinity");
                        numeriSalvati.setText(primo_numero + " " + tipo_operazione + " " + numero_corrente + " = " + "Infinity");
                    }else{
                        aggiornaRisultato(risultato);
                    }
                }
                case "*" -> {
                    double risultato = primoNumero * secondoNumero;
                    aggiornaRisultato(risultato);
                }
                case "^" -> {
                    double risultato = Math.pow(primoNumero, secondoNumero);
                    if (primoNumero == 0 && secondoNumero == 0){
                        myTextField.setText("Indefinito");
                        numeriSalvati.setText(primo_numero + " " + tipo_operazione + " " + numero_corrente + " = " + "Indefinito");
                        alert("Errore");
                    }else {
                        aggiornaRisultato(risultato);
                    }
                }
                case "√" -> {
                    if (secondoNumero != 0){
                        double risultato = Math.pow(secondoNumero, 1.0 / esponenteRadice);
                        aggiornaRisultato(risultato);
                    } else {
                        myTextField.setText("NaN");
                        numeriSalvati.setText(primo_numero + " " + tipo_operazione + " " + numero_corrente + " = " + "NaN");
                        alert("Impossibile calcolare la radice di 0.");
                    }
                }
            }
        } else {
            alert("Inserisci un numero prima di fare un operazione.");
        }
    }

    /**
     * Metodo per aggiornare l'interfaccia della calcolatrice e il risultato di un operazione
     * @param risultato indica il risultato dell'operazione effettuata precedentemente
     */
    public void aggiornaRisultato(double risultato) {
        numeriSalvati.setText(primo_numero + " " + tipo_operazione + " " + numero_corrente + " = " + risultato);

        //Resetto il primo numero e imposto il risultato come numero corrente
        primo_numero = "";
        numero_corrente = String.valueOf(risultato);
        risultato = Math.round(risultato * 1000.0) / 1000.0; //Arrotondo alla terza cifra decimale attraverso il metodo round
        myTextField.setText(String.valueOf(risultato));
    }

    /**
     * Metodo per aggiungere un numero all'input corrente
     * @param n indica il numero da aggiungere
     */
    public void aggiungiNumero(String n){
        numero_corrente += n;
        myTextField.setText(numero_corrente);
    }

    //Metodo per resettare tutto
    @FXML
    protected void onResetButtonClick() {
        primo_numero = "";
        numero_corrente = "";
        myTextField.setText("");
        numeriSalvati.setText("");
    }

    //Metodo per aggiungere un punto decimale (se non è gia presente)
    @FXML
    protected void onPuntoButtonClick() {
        if (!numero_corrente.contains(".")) {
            if (numero_corrente.isEmpty())
                numero_corrente = "0.";
            else
                numero_corrente += ".";
            myTextField.setText(numero_corrente);
        }
    }

    //Metodo per calcolare la frazione di un numero (bottone: 1/x)
    @FXML
    protected void onFrazioneButtonClick() {
        if(!numero_corrente.isEmpty()){
            double numero = Double.parseDouble(numero_corrente);
            if (numero != 0){
                double ris = 1.0 / numero;
                numeriSalvati.setText("1 / " + numero + " = " + ris);
                primo_numero = "";
                numero_corrente = String.valueOf(ris);
                ris = Math.round(ris * 1000.0) / 1000.0;
                myTextField.setText(String.valueOf(ris));
            }else {
                numeriSalvati.setText("1 / " + numero + " = " + "Infinity");
                primo_numero = "";
                myTextField.setText("Infinity");
            }
        }else {
            alert("Inserisci prima un numero.");
        }
    }

    //Metodo per eliminare l'ultimo carattere dell'input corrente
    @FXML
    protected void onDeleteButtonClick() {
        if (!numero_corrente.isEmpty()) {
            numero_corrente = numero_corrente.substring(0, numero_corrente.length() - 1);
            myTextField.setText(numero_corrente);
        }
    }

    @FXML
    protected void on0ButtonClick() {
        aggiungiNumero("0");
    }

    @FXML
    protected void on1ButtonClick() {
        aggiungiNumero("1");
    }

    @FXML
    protected void on2ButtonClick() {
        aggiungiNumero("2");
    }

    @FXML
    protected void on3ButtonClick() {
        aggiungiNumero("3");
    }

    @FXML
    protected void on4ButtonClick() {
        aggiungiNumero("4");
    }

    @FXML
    protected void on5ButtonClick() {
        aggiungiNumero("5");
    }

    @FXML
    protected void on6ButtonClick() {
        aggiungiNumero("6");
    }

    @FXML
    protected void on7ButtonClick() {
        aggiungiNumero("7");
    }

    @FXML
    protected void on8ButtonClick() {
        aggiungiNumero("8");
    }

    @FXML
    protected void on9ButtonClick() {
        aggiungiNumero("9");
    }
}