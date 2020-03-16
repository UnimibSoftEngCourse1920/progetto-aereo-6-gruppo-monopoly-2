
function loadJSON(){
	var dataFile ="http://localhost:8080/progetto-aereo-6/ticketsale/tickets";
	var xhttp;
	var table;

	if(window.XMLHttpRequest){
		xhttp=new XMLHttpRequest();
	}else{
		xhttp=new ActiceXObject("Microsoft.XMLHTTP");
	}
	xhttp.onreadystatechange=function(){

		if(this.readyState==4 && this.status==200){
			var jsonObj=JSON.parse(xhttp.responseText);
			table=creaTabella(jsonObj);
		}		
	}
	
	xhttp.open("GET", dataFile, true);
	xhttp.send();
	}

function creaTabella(jsonObj){
	
	var table="<table><tr><th>CodiceVolo</th>" +
	"<th>Aeroporto di Partenza</th>" +
	"<th>Aeroporto di Arrivo</th>" +
	"<th>orarioP</th>" +
	"<th>Prezzo</th>" +
	"<th>Prenota</th>"+
	"<th>Acquista</th>"+"</tr>";
	
	for(x in jsonObj){
		var item=jsonObj[x];
		var prenotazione="<a href='#login'><input type='button' value='prenota' class='primary'/></a>";
		var acquisto="<a href='#login'><input type='button' value='acquista' onchange='pagamento("+item+")' class='primary'/></a>";
		table += "<tr><td>" +
		item.code +
		"</td><td>" +
		item.departureAirport+
		"</td><td>" +
		item.arrivalAirport+
		"</td><td>" +
		item.departureTime+
		"</td><td>" +
		item.price+
		"</td><td>" +
		prenotazione+
		"</td><td>" +
		acquisto
		"</td></tr></table>";
	}
	document.getElementById("tabellaProposte").innerHTML = table;
}

function pagamento(item){
	console.log(item);
	var dataFile ="http://localhost:8080/progetto-aereo-6/ticketsale/tickets/"+item.code+"/sale";
	var xhttp;
	var table;
	console.log(dataFile);
	if(window.XMLHttpRequest){
		xhttp=new XMLHttpRequest();
	}else{
		xhttp=new ActiceXObject("Microsoft.XMLHTTP");
	}
	xhttp.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			var jsonObj=JSON.parse(xhttp.responseText);
			paga(jsonObj,dataFile);
		}		
	}	
	xhttp.open("GET", dataFile, true);
	xhttp.send();
}

function paga(jsonObj,dataFile){	
}

function ricercaPartenza(airport){
	var dataFile ="http://localhost:8080/progetto-aereo-6/ticketsale/tickets";
	var xhttp;
	var table;
	
	if(window.XMLHttpRequest){
		xhttp=new XMLHttpRequest();
	}else{
		xhttp=new ActiceXObject("Microsoft.XMLHTTP");
	}
	if(airport==""){
		document.getElementById("tabellaRicercaAirport").innerHTML = "";
	    return;
	}
	xhttp.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			var jsonObj=JSON.parse(xhttp.responseText);
			table=ricercaTabella(jsonObj,airport);
		}		
	}	
	xhttp.open("GET", dataFile, true);
	xhttp.send();
}
function ricercaTabella(jsonObj,airport){
	var prenotazione="<a href='#login'><input type='button' value='prenota' class='primary'/></a>";
	var acquisto="<a href='#login'><input type='button' value='acquista' onclick='richiestaAcquisto()' class='primary'/></a>";
	var table="<table><tr><th>CodiceVolo</th>" +
	"<th>Aeroporto di partenza</th>" +
	"<th>Aeroporto di destinazione</th>" +
	"<th>orarioP</th>" +
	"<th>Prezzo</th>" +
	"<th>Prenota</th>"+
	"<th>Acquista</th>"+"</tr>";
	for(x in jsonObj){
		var item=jsonObj[x];
		if( item.departureAirport == airport){
		table += "<tr><td>" +
		item.code +
		"</td><td>" +
		item.departureAirport+
		"</td><td>" +
		item.arrivalAirport+
		"</td><td>" +
		item.departureTime+
		"</td><td>" +
		item.price+
		"</td><td>" +
		prenotazione+
		"</td><td>" +
		acquisto
		"</td></tr></table>";
		}
	}
	document.getElementById("tabellaRicercaAirport").innerHTML = table;
}

function ricercaDestinazione(airport){
	var dataFile ="http://localhost:8080/progetto-aereo-6/ticketsale/tickets";
	var xhttp;
	var table;
	
	if(window.XMLHttpRequest){
		xhttp=new XMLHttpRequest();
	}else{
		xhttp=new ActiceXObject("Microsoft.XMLHTTP");
	}
	
	if(airport==""){
		document.getElementById("tabellaRicercaAirportDestinazione").innerHTML = "";
	    return;
	}
	xhttp.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			var jsonObj=JSON.parse(xhttp.responseText);
			table=ricercaTabellaDestinazione(jsonObj,airport);
		}		
	}	
	xhttp.open("GET", dataFile, true);
	xhttp.send();
}
function ricercaTabellaDestinazione(jsonObj,airport){
	var prenotazione="<a href='#login'><input type='button' value='prenota' class='primary'/></a>";
	var acquisto="<a href='#login'><input type='button' value='acquista' onclick='richiestaAcquisto()' class='primary'/></a>";
	var table="<table><tr><th>CodiceVolo</th>" +
	"<th>Aeroporto di partenza</th>" +
	"<th>Aeroporto di destinazione</th>" +
	"<th>orarioP</th>" +
	"<th>Prezzo</th>" +
	"<th>Prenota</th>"+
	"<th>Acquista</th>"+"</tr>";
	
	for(x in jsonObj){
		var item=jsonObj[x];
		if( item.arrivalAirport == airport){
		table += "<tr><td>" +
		item.code +
		"</td><td>" +
		item.departureAirport+
		"</td><td>" +
		item.arrivalAirport+
		"</td><td>" +
		item.departureTime+
		"</td><td>" +
		item.price+
		"</td><td>" +
		prenotazione+
		"</td><td>" +
		acquisto
		"</td></tr></table>";
		}
	}
	document.getElementById("tabellaRicercaAirportDestinazione").innerHTML = table;	
}
function tabellaCliente(){
	var dataFile ="http://localhost:8080/progetto-aereo-6/ticketsale/tickets";
	var xhttp;
	var tablePrenotazione;
	var tableAcquisto;
	if(window.XMLHttpRequest){
		xhttp=new XMLHttpRequest();
	}else{
		xhttp=new ActiceXObject("Microsoft.XMLHTTP");
	}
	xhttp.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			var jsonObj=JSON.parse(xhttp.responseText);
			tablePrenotazione=creaTabellaClientePrenotazione(jsonObj);
			tableAcquisto=creaTabellaClienteAcquisto(jsonObj);
		}		
	}
	xhttp.open("GET", dataFile, true);
	xhttp.send();
}

function creaTabellaClientePrenotazione(jsonObj){
	var modifica="<a href='#login'><input type='button' value='modifica' class='primary'/></a>";
	var conferma="<a href='#login'><input type='button' value='acquista' onclick='richiestaAcquisto()' class='primary'/></a>";
	var table="<table><tr><th>CodiceVolo</th>" +
	"<th>Aeroporto</th>" +
	"<th>orarioP</th>" +
	"<th>Prezzo</th>" +
	"<th>Acquista</th>"+
	"<th>Modifica</th>"+"</tr>";
	for(x in jsonObj){
		var item=jsonObj[x];
		table += "<tr><td>" +
		item.code +
		"</td><td>" +
		item.arrivalAirport+
		"</td><td>" +
		item.departureTime+
		"</td><td>" +
		item.price+
		"</td><td>" +
		conferma+
		"</td><td>" +
		modifica
		"</td></tr></table>";
	}
	document.getElementById("tabellaPrenotazioneCliente").innerHTML = table;	
}
function creaTabellaClienteAcquisto(jsonObj){
	var modifica="<a href='#login'><input type='button' value='modifica' class='primary'/></a>";
	var conferma="<a href='#login'><input type='button' value='conferma' onclick='richiestaAcquisto()' class='primary'/></a>";
	var table="<table><tr><th>CodiceVolo</th>" +
	"<th>Aeroporto</th>" +
	"<th>orarioP</th>" +
	"<th>Prezzo</th>" +
	"<th>Conferma</th>"+
	"<th>Modifica</th>"+"</tr>";
	for(x in jsonObj){
		var item=jsonObj[x];
		table += "<tr><td>" +
		item.code +
		"</td><td>" +
		item.arrivalAirport+
		"</td><td>" +
		item.departureTime+
		"</td><td>" +
		item.price+
		"</td><td>" +
		conferma+
		"</td><td>" +
		modifica
		"</td></tr></table>";
	}
	document.getElementById("tabellaAcquistiCliente").innerHTML = table;	
} 
function ricercaData(){
	var dataFile ="http://localhost:8080/progetto-aereo-6/ticketsale/tickets";
	var xhttp;
	var table;
	var form=document.forms["sceltaData"];
	var data="";
	console.log(form);
	if(window.XMLHttpRequest){
		xhttp=new XMLHttpRequest();
	}else{
		xhttp=new ActiceXObject("Microsoft.XMLHTTP");
	}
	xhttp.onreadystatechange=function(){
		for(var i=0;i<form.lenght;i++){
			data+=form[i].childNodes[0].nodeValue;
		}
		if(this.readyState==4 && this.status==200){
			var jsonObj=JSON.parse(xhttp.responseText);
			table=ricercaTabellaData(jsonObj,data);
		}		
	}	
	xhttp.open("GET", dataFile, true);
	xhttp.send();
}

function ricercaTabellaData(jsonObj,data){
	var prenotazione="<a href='#login'><input type='button' value='prenota' onclick='' class='primary'/></a>";
	var acquisto="<a href='#login'><input type='button' value='acquista' onclick='richiestaAcquisto()'class='primary'/></a>";
	var table="<table><tr><th>CodiceVolo</th>" +
	"<th>Aeroporto di partenza</th>" +
	"<th>Aeroporto di destinazione</th>" +
	"<th>orarioP</th>" +
	"<th>Prezzo</th>" +
	"<th>Prenota</th>"+
	"<th>Acquista</th>"+"</tr>";
	
	for(x in jsonObj){
		var item=jsonObj[x];
		if( item.departureTime >= data){
		table += "<tr><td>" +
		item.code +
		"</td><td>" +
		item.departureAirport+
		"</td><td>" +
		item.arrivalAirport+
		"</td><td>" +
		item.departureTime+
		"</td><td>" +
		item.price+
		"</td><td>" +
		prenotazione+
		"</td><td>" +
		acquisto
		"</td></tr></table>";
		}
	}
	document.getElementById("tabellaRicercaData").innerHTML = table;	
}

function convalida(){
	var username = document.modul.username.value;
	var password = document.modul.password.value;
	console.log(username);
	console.log(password);
	//Effettua il controllo sul campo NOME
	if ((username == "") || (username == "undefined")) {
		alert("Il campo username è obbligatorio.");
		document.modulo.username.focus();
		return false;
	}
	
	//Effettua il controllo sul campo PASSWORD
	if ((password == "") || (password == "undefined")) {
		alert("Il campo Password è obbligatorio.");
		document.modulo.password.focus();
		return false;
	}
	//INVIA IL MODULO
	else {
		cercaPersona(username,password);
		document.modulo.submit();
	}
}

function cercaPersona(username,password){
	var datafile="http://localhost:8080/progetto-aereo-6/auth/authentication/";
	console.log(datafile);
	var xhttp;
	if(window.XMLHttpRequest){
		xhttp=new XMLHttpRequest();
	}else{
		xhttp=new ActiceXObject("Microsoft.XMLHTTP");
	}
	xhttp.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
		console.log(this.readyState);
		console.log(this.status);
			var jsonObj=JSON.parse(xhttp.responseText);
			for( x in jsonObj){
				if(username==jsonObj[x].username && password==jsonObj[x].password){
					console.log(i);
					document.modulo.submit();
				}
			}
		}		
	}
	xhttp.open("POST", datafile, true);
	xhttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	xhttp.send("username="+username+"&password="+password);
}

function registrazione() {
	var nome = document.modulo.nome.value;
	var cognome = document.modulo.cognome.value;
	var username = document.modulo.username.value;
	var password = document.modulo.password.value;
	var conferma = document.modulo.conferma.value;
	var nascita = document.modulo.nascita.value;
	var citta = document.modulo.città.value;
	var indirizzo = document.modulo.indirizzo.value;
	var telefono = document.modulo.telefono.value;
	var email = document.modulo.email.value;
	// Espressione regolare dell'email
	var email_reg_exp = /^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-]{2,})+.)+([a-zA-Z0-9]{2,})+$/;
	//Effettua il controllo sul campo NOME
	if ((nome == "") || (nome == "undefined")) {
		alert("Il campo Nome è obbligatorio.");
		document.modulo.nome.focus();
		return false;
	}
	//Effettua il controllo sul campo COGNOME
	else if ((cognome == "") || (cognome == "undefined")) {
		alert("Il campo Cognome è obbligatorio.");
		document.modulo.cognome.focus();
		return false;
	}
	//Effettua il controllo sul campo username
	else if ((username == "") || (username == "undefined")) {
		alert("Il campo username è obbligatorio.");
		document.modulo.username.focus();
		return false;
	}
	//Effettua il controllo sul campo PASSWORD
	else if ((password == "") || (password == "undefined")) {
		alert("Il campo Password è obbligatorio.");
		document.modulo.password.focus();
		return false;
	}
	//Effettua il controllo sul campo CONFERMA PASSWORD
	else if ((conferma == "") || (conferma == "undefined")) {
		alert("Il campo Conferma password è obbligatorio.");
		document.modulo.conferma.focus();
		return false;
	}
	//Verifica l'uguaglianza tra i campi PASSWORD e CONFERMA PASSWORD
	else if (password != conferma) {
		alert("La password confermata è diversa da quella scelta, controllare.");
		document.modulo.conferma.value = "";
		document.modulo.conferma.focus();
		return false;
	}
	//Effettua il controllo sul campo DATA DI NASCITA
	else if (document.modulo.nascita.value.substring(2,3) != "/" ||
			document.modulo.nascita.value.substring(5,6) != "/" ||
			isNaN(document.modulo.nascita.value.substring(0,2)) ||
			isNaN(document.modulo.nascita.value.substring(3,5)) ||
			isNaN(document.modulo.nascita.value.substring(6,10))) {
		alert("Inserire nascita in formato gg/mm/aaaa");
		document.modulo.nascita.value = "";
		document.modulo.nascita.focus();
		return false;
	}
	else if (document.modulo.nascita.value.substring(0,2) > 31) {
		alert("Impossibile utilizzare un valore superiore a 31 per i giorni");
		document.modulo.nascita.select();
		return false;
	}
	else if (document.modulo.nascita.value.substring(3,5) > 12) {
		alert("Impossibile utilizzare un valore superiore a 12 per i mesi");
		document.modulo.nascita.value = "";
		document.modulo.nascita.focus();
		return false;
	}
	else if (document.modulo.nascita.value.substring(6,10) < 1900) {
		alert("Impossibile utilizzare un valore inferiore a 1900 per l'anno");
		document.modulo.nascita.value = "";
		document.modulo.nascita.focus();
		return false;
	}
	//Effettua il controllo sul campo CITTA'
	else if ((citta == "") || (citta == "undefined")) {
		alert("Il campo Città è obbligatorio.");
		document.modulo.citta.focus();
		return false;
	}
	//Effettua il controllo sul campo INDIRIZZO
	else if ((indirizzo == "") || (indirizzo == "undefined")) {
		alert("Il campo Indirizzo è obbligatorio.");
		document.modulo.indirizzo.focus();
		return false;
	}
	//Effettua il controllo sul campo TELEFONO
	else if ((isNaN(telefono)) || (telefono == "") || (telefono == "undefined")) {
		alert("Il campo Telefono è numerico ed obbligatorio.");
		document.modulo.telefono.value = "";
		document.modulo.telefono.focus();
		return false;
	}
	else if (!email_reg_exp.test(email) || (email == "") || (email == "undefined")) {
		alert("Inserire un indirizzo email corretto.");
		document.modulo.email.select();
		return false;
	}
	//INVIA IL MODULO
	else {
		cercaRegistrazione(username,password,email);
			
	}
}
function cercaRegistrazione(username,password,email){
	var datafile="http://localhost:8080/progetto-aereo-6/auth/registration";
	var xhttp;
	if(window.XMLHttpRequest){
		xhttp=new XMLHttpRequest();
	}else{
		xhttp=new ActiceXObject("Microsoft.XMLHTTP");
	}
	xhttp.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			var jsonObj=xhttp.responseText;
			for( x in jsonObj){
				console.log(jsonObj[x]);
				if(username==jsonObj[x].username && password==jsonObj[x].password && email==jsonObj[x].email){
					alert("l'utente è già registrato");
					return false;
				}
			}
		}		
	}
	xhttp.open("POST", datafile, true);
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhttp.send("username="+username+"&password="+password);
}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////
//gestione data

//define variables per la data
var nativePicker = document.querySelector('.nativeDateTimePicker');
var fallbackPicker = document.querySelector('.fallbackDateTimePicker');
var fallbackLabel = document.querySelector('.fallbackLabel');

var yearSelect = document.querySelector('#year');
var monthSelect = document.querySelector('#month');
var daySelect = document.querySelector('#day');
var hourSelect = document.querySelector('#hour');
var minuteSelect = document.querySelector('#minute');

// hide fallback initially
fallbackPicker.style.display = 'none';
fallbackLabel.style.display = 'none';

// test whether a new datetime-local input falls back to a text input or not
var test = document.createElement('input');

try {
  test.type = 'datetime-local';
} catch (e) {
  console.log(e.description);
}

// if it does, run the code inside the if() {} block
if(test.type === 'text') {
  // hide the native picker and show the fallback
  nativePicker.style.display = 'none';
  fallbackPicker.style.display = 'block';
  fallbackLabel.style.display = 'block';

  // populate the days and years dynamically
  // (the months are always the same, therefore hardcoded)
  populateDays(monthSelect.value);
  populateYears();
  populateHours();
  populateMinutes();
}

function populateDays(month) {
  // delete the current set of <option> elements out of the
  // day <select>, ready for the next set to be injected
  while(daySelect.firstChild){
    daySelect.removeChild(daySelect.firstChild);
  }

  // Create variable to hold new number of days to inject
  var dayNum;

  // 31 or 30 days?
  if(month === '1' || month === '3' || month === '5' || month === '7' || month === '8' || month === '10' || month === '12') {
    dayNum = 31;
  } else if(month === '4' || month === '6' || month === '9' || month === '11') {
    dayNum = 30;
  } else {
  // If month is February, calculate whether it is a leap year or not
    var year = yearSelect.value;
    var isLeap = new Date(year, 1, 29).getMonth() == 1;
    isLeap ? dayNum = 29 : dayNum = 28;
  }

  // inject the right number of new <option> elements into the day <select>
  for(i = 1; i <= dayNum; i++) {
    var option = document.createElement('option');
    option.textContent = i;
    daySelect.appendChild(option);
  }

  // if previous day has already been set, set daySelect's value
  // to that day, to avoid the day jumping back to 1 when you
  // change the year
  if(previousDay) {
    daySelect.value = previousDay;

    // If the previous day was set to a high number, say 31, and then
    // you chose a month with less total days in it (e.g. February),
    // this part of the code ensures that the highest day available
    // is selected, rather than showing a blank daySelect
    if(daySelect.value === "") {
      daySelect.value = previousDay - 1;
    }

    if(daySelect.value === "") {
      daySelect.value = previousDay - 2;
    }

    if(daySelect.value === "") {
      daySelect.value = previousDay - 3;
    }
  }
}

function populateYears() {
  // get this year as a number
  var date = new Date();
  var year = date.getFullYear();

  // Make this year, and the 100 years before it available in the year <select>
  for(var i = 0; i <= 100; i++) {
    var option = document.createElement('option');
    option.textContent = year-i;
    yearSelect.appendChild(option);
  }
}

function populateHours() {
  // populate the hours <select> with the 24 hours of the day
  for(var i = 0; i <= 23; i++) {
    var option = document.createElement('option');
    option.textContent = (i < 10) ? ("0" + i) : i;
    hourSelect.appendChild(option);
  }
}

function populateMinutes() {
  // populate the minutes <select> with the 60 hours of each minute
  for(var i = 0; i <= 59; i++) {
    var option = document.createElement('option');
    option.textContent = (i < 10) ? ("0" + i) : i;
    minuteSelect.appendChild(option);
  }
}

// when the month or year <select> values are changed, rerun populateDays()
// in case the change affected the number of available days
yearSelect.onchange = function() {
  populateDays(monthSelect.value);
}

monthSelect.onchange = function() {
  populateDays(monthSelect.value);
}

//preserve day selection
var previousDay;

// update what day has been set to previously
// see end of populateDays() for usage
daySelect.onchange = function() {
  previousDay = daySelect.value;
}