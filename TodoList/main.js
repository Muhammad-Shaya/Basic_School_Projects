const btnGen = document.getElementById("btnGen")
const btnClear = document.getElementById("btnClear")
const btnClearSelected = document.getElementById("btnClearSelected")
const btnCestino = document.getElementById("btnCestino")
let edtTesto = document.getElementById("edtTesto")
let conta = 1
let tableList = document.getElementById("tableList")

function clearList(){
    let tableRows = document.querySelectorAll("tr");
    let cestinoHTML = localStorage.getItem("cestinoHTML") //ottengo contenuto cestino del localstorage
    if (cestinoHTML == null || cestinoHTML == undefined){
        cestinoHTML = ""  //inizializzo una stringa vuota (se il cestino è vuoto)
    }
    for(let i = 1; i < tableRows.length; i++){
        cestinoHTML += tableRows[i].outerHTML //prendo l'HTML della riga e lo aggiungo al cestino
        tableRows[i].remove()
    }
    localStorage.setItem("cestinoHTML", cestinoHTML)
    edtTesto.value = "" //ripulisco il campo di input
    edtTesto.focus() //riposiziono il cursore all'interno
    conta = 1
    //salvo lo stato della tabella nel localStorage e anche il contatore
    localStorage.setItem("Lista", tableList.innerHTML)
    localStorage.setItem("Conta", conta)
} 

btnClearSelected.onclick = function(){
    let checkbox = document.querySelectorAll("input[type='checkbox']")
    for (let i = 0; i < checkbox.length; i++){
        if (checkbox[i].checked){
            let row = checkbox[i].parentNode.parentNode  //prendo la riga in base al checkbox selezionato 
            row.remove()
            //aggiorno il contatore visto che la riga viene rimossa 
            let rows = document.querySelectorAll("tr")
            for (let j = 1; j < rows.length; j++){
                let celle = rows[j].getElementsByTagName("td")
                celle[0].textContent = j
            }
            conta--
        }
    }
    localStorage.setItem("Lista", tableList.innerHTML)
    localStorage.setItem("Conta", conta)
}

btnGen.onclick = function(){
    const tableRow = document.createElement("tr")
    let tableData1 = document.createElement("td")
    let tableData2 = document.createElement("td")
    let tableData3 = document.createElement("td")
    let tableData4 = document.createElement("td")
    let tableData5 = document.createElement("td")
    let value = edtTesto.value 
    if (value != ""){
        let data = new Date()
        tableData1.innerHTML = conta
        tableRow.appendChild(tableData1)
        tableData2.innerHTML = data.toLocaleDateString()
        tableRow.appendChild(tableData2)
        tableData3.innerHTML = data.toLocaleTimeString()
        tableRow.appendChild(tableData3)
        tableData4.innerHTML = value
        tableRow.appendChild(tableData4)
        tableData5.innerHTML = '<input type="checkbox" id='+conta+'>'
        tableRow.appendChild(tableData5)

        tableList.appendChild(tableRow) 
        edtTesto.value = ""
        conta++
        edtTesto.focus() 
        localStorage.setItem("Lista", tableList.innerHTML) //per salvare i dati
        localStorage.setItem("Conta", conta)
    }
} 

btnClear.onclick = function(){
    if (confirm("Eliminare tutti gli elementi della lista?")){
        clearList()
    }  
}

btnCestino.onclick = function(){
    window.open("cestino.html", "_blank") //apro pagina cestino in nuova finestra
}

//Main code

if (localStorage.getItem("Lista") != ""){
    tableList.innerHTML = localStorage.getItem("Lista") //carico la lista
}

if (localStorage.getItem("Conta") != ""){
    conta = parseInt(localStorage.getItem("Conta"))
}else{
    conta = 1
}