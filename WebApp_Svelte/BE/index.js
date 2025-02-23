/**
 * Importazioni
 * Express --> Framework per creare il server
 * Cors --> Middleware per gestire le richieste
 * bodyParser --> Middleware per elaborare dati JSON delle richieste
 */
const express = require('express')
const cors = require('cors')
const bodyParser = require('body-parser')

/**
 * Creazione di un'istanza dell'app Express
 * PORT 3080 --> Porta sulla quale il server è in ascolto
 */
const app = express()
const PORT = 3080

//Configurazione dei middleware
app.use(cors())
app.use(bodyParser.json())
app.use(express.urlencoded({extended:true}))

//Array per salvare i dati inseriti dagli utenti
let utenti = []

//Rotta principale - Restituisce un messaggio di benvenuto
app.get('/', (req,res)=>{
    res.send("Benvenuto. Server - Raccolta dati")
})

//Rotta per ottenere tutti i dati inseriti dagli utenti
app.get('/dati', (req,res)=>{
    //Mi ristituisce la lista degli utenti in formato JSON
    res.json(utenti) 
})

//Rotta per salvare i dati provenienti dal form
app.post('/dati', (req,res)=>{
    /**
     * Prendo i dati dalla richiesta
     * Controllo che tutti i campi siano presenti
     * Controllo se l'utente esiste già nell'elenco
     * Creo un nuove oggetto utente con i dati ricevuti
     * Aggiungo il nuovo utente all'array
     */
   const {nome, cognome, dataNascita, sesso, skills} = req.body

   if(!nome || !cognome || !dataNascita || !sesso || !skills){
       return res.status(400).json({error: "Errore - Dati Mancanti"})
   }

   const utenteEsistente = utenti.find(utente => utente.nome === nome && utente.cognome === cognome && utente.dataNascita === dataNascita)
    if (utenteEsistente) {
        return res.status(400).json({ error: "Errore - L'utente è già registrato" })
    }

   const newUtente = {nome, cognome, dataNascita, sesso, skills}
   utenti.push(newUtente)

   res.json({message: `Successo - Utente ${nome} ${cognome} registrato`, user: newUtente})
})

//Avvio del server sulla porta specificata
app.listen(PORT, ()=>{
    console.log(`Server is running on port ${PORT}`)
})