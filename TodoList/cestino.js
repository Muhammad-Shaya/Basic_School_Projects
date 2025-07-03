const btnSvuotaCestino = document.getElementById("btnSvuotaCestino")
let tableList = document.getElementById("tableList")
const btnEliminaSelezionato = document.getElementById("btnEliminaSelezionato")

window.onload = function(){ //quando la finestra si carica mi esegue il codice sotto riportato
    //controllo se ci sono elementi nel cestino salvati nel localstorage, in caso affermativo li aggiungo alla tabella del cestino
    let cestinoHTML = localStorage.getItem("cestinoHTML")
    if (cestinoHTML != null && cestinoHTML != undefined){ 
        tableList.innerHTML = cestinoHTML
    }
}

function svuota_cestino(){
    //alert("funziona")    
    localStorage.removeItem("cestinoHTML")
    tableList.innerHTML = "" //svuoto la tabella
}

btnSvuotaCestino.onclick = function(){
    if (confirm("Eliminare definitivamente tutti gli elementi?")){
        svuota_cestino()
    }  
}

btnEliminaSelezionato.onclick = function(){
    let checkbox = document.querySelectorAll("input[type='checkbox']")
    if (checkbox.length == 0){
        alert("Seleziona almeno un elemento da eliminare")
        return
    }

    if (confirm("Eliminare gli elementi selezionati?")) {
        for (let i = 0; i < checkbox.length; i++){
            if (checkbox[i].checked){
                let row = checkbox[i].parentNode.parentNode  
                row.remove()
            }
        }
    }
    localStorage.setItem("cestinoHTML", tableList.innerHTML)
}