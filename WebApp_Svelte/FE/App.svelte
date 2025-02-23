<script>
    //Dichiarazione variabili
    let nome = $state()
    let cognome = $state()
    let dataNascita = $state()
    let sesso = $state()
    let responseMessage = $state()
    let utenti = $state([])
    let skills = $state([])
    let isError = $state(false)

    //Funzione per inviare il form al server
    async function sendForm(event) {
        event.preventDefault()
        try {
            //Esecuzione di una richiesta POST al server
            const res = await fetch("http://localhost:3080/dati", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                //Corpo della richiesta
                body: JSON.stringify({nome, cognome, dataNascita, sesso, skills})
            });

            const data = await res.json() //Converto la risposta in formato JSON
            console.log(data) 

            if (res.ok) {
                responseMessage = data.message
                isError = false
                console.log(responseMessage)
                utenti = [...utenti, data.user] //Aggiunngo nuovo utente nell'elenco utenti
            } else {
                responseMessage = data.error
                console.log(responseMessage)
                isError = true
            }
        } catch (error) {
            responseMessage = "Errore - Connessione"
            console.log(responseMessage)
            isError = true
        }
    }
</script>

<h1>Form - Creazione Utente</h1>
<!--Form per raccogliere i dati dell'utente-->
<form onsubmit={sendForm}>
    <div>
        <label for="nome">Nome:</label>
        <input type="text" id="nome" bind:value={nome} placeholder="Inserisci nome" required>
    </div>
    <br>
    <div>
        <label for="cognome">Cognome:</label>
        <input type="text" id="cognome" bind:value={cognome} placeholder="Inserisci cognome" required>
    </div>
    <br>
    <div>
        <label for="dataNascita">Data di nascita:</label>
        <input type="date" id="dataNascita" bind:value={dataNascita} required>
    </div>
    <div>
        <p>Sesso:</p>
        <input type="radio" id="maschio" bind:group={sesso} value="Maschio">
        <label for="maschio">Maschio</label>
        <input type="radio" id="femmina" bind:group={sesso} value="Femmina">
        <label for="femmina">Femmina</label>
        <input type="radio" id="altro" bind:group={sesso} value="Altro">
        <label for="altro">Altro</label>
    </div>
    <div>
        <p>Skills:</p>
        <input type="checkbox" id="html" bind:group={skills} value="HTML">
        <label for="html">HTML</label>
        <input type="checkbox" id="css" bind:group={skills} value="CSS">
        <label for="css">CSS</label>
        <input type="checkbox" id="js" bind:group={skills} value="JavaScript">
        <label for="js">JavaScript</label>
    </div>
    <br>
    <button type="submit">Invia</button>
</form>

<!--Messaggio di successo o errore dopo l'invio dei dati-->
<h2 class={isError ? "error" : "success"}>{responseMessage}</h2>

<!--Tabella per visualizzare gli utenti registrati-->
<h1>Utenti Registrati</h1>
<table border="3">
    <thead>
        <tr>
            <th>Nome</th>
            <th>Cognome</th>
            <th>Data di Nascita</th>
            <th>Sesso</th>
            <th>Skills</th>
        </tr>
    </thead>
    <tbody>
        {#each utenti as utente}
            <tr>
                <td>{utente.nome}</td>
                <td>{utente.cognome}</td>
                <td>{utente.dataNascita}</td>
                <td>{utente.sesso}</td>
                <td>{utente.skills.join(", ")}</td> 
            </tr>
        {/each}
    </tbody>
</table>

<!--Custom CSS-->
<style>
    body {
    background-image: url('sfondo_es.jpg');
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
    height: 100vh;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    margin: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
}

h1 {
    color: #0056b3;
    font-family: 'Dancing Script', cursive;
    font-weight: 700;
    text-align: center;
    margin-bottom: 3rem;
    font-size: 3.5rem;
    letter-spacing: 2px;
    font-style: italic;
}

.container {
    max-width: 500px;
    margin: 0 auto;
    padding: 20px;
    background-color: rgba(255, 255, 255, 0.8);
    border-radius: 10px;
    box-shadow: 0 6px 20px rgba(0, 0, 0, 0.1);
}

.form-container {
    padding: 2.5rem;
    background-color: #fff;
    border-radius: 10px;
    box-shadow: 0 6px 20px rgba(0, 0, 0, 0.1);
}

.form-label {
    font-weight: 600;
    color: black;
}

.form-control {
    border-radius: 8px;
    box-shadow: 0 3px 10px rgba(0, 0, 0, 0.1);
    transition: box-shadow 0.3s ease;
    width: 100%;
    padding: 10px;
    margin: 10px 0;
}

.form-control:focus {
    box-shadow: 0 3px 15px rgba(0, 123, 255, 0.4);
    border-color: #0d6efd;
}

button[type="submit"] {
    background-color: #0d6efd;
    color: #fff;
    font-weight: bold;
    border: none;
    width: 100%;
    padding: 0.75rem;
    border-radius: 8px;
    box-shadow: 0 4px 10px rgba(13, 110, 253, 0.3);
    transition: all 0.3s ease;
}

button[type="submit"]:hover {
    background-color: #0056b3;
    box-shadow: 0 6px 15px rgba(13, 110, 253, 0.5);
}

.output {
    background-color: #e9f7ef;
    border-left: 5px solid #28a745;
    padding: 1rem;
    border-radius: 8px;
    margin-top: 1.5rem;
    font-weight: 600;
    color: #155724;
    box-shadow: 0 3px 10px rgba(0, 0, 0, 0.1);
    font-family: 'Crimson Pro', serif;
}

input[type="radio"], input[type="checkbox"] {
    margin-right: 5px;
}

input[type="radio"]:checked + label, input[type="checkbox"]:checked + label {
    font-weight: bold;
    color: #0d6efd;
}

table {
    width: 100%;
    margin-top: 1rem;
    border-collapse: collapse;
}

th, td {
    padding: 0.75rem;
    text-align: center;
    border: 1px solid #ddd;
}

th {
    background-color: #f8f9fa;
    font-weight: bold;
}

tr:hover {
    background-color: #f1f1f1;
    cursor: pointer;
}

.table-container {
    margin-top: 3rem;
    max-width: 800px;
    width: 100%;
    background-color: #fff;
    padding: 2rem;
    border-radius: 10px;
    box-shadow: 0 6px 20px rgba(0, 0, 0, 0.1);
}

.table-container table {
    width: 100%;
    border-radius: 8px;
    overflow: hidden;
}

.table-container td, .table-container th {
    border: none;
}

.table-container tr:nth-child(even) {
    background-color: #f9f9f9;
}

.table-container tr:hover {
    background-color: #eef2f5;
}

.error {
        color: red;
        font-weight: bold;
    }
    .success {
        color: green;
        font-weight: bold;
    }
    
</style>