
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
	"<th>Posti</th>"+
	"<th>Prenota</th>"+
	"<th>Acquista</th>"+"</tr>";
	
	for(x in jsonObj){
		var item=jsonObj[x];
		var prenotazione="<a href='#login'><input type='button' onclick='pagamento(item)' value='prenota' class='primary'/></a>";
		var acquisto="<a href='#login'><input type='button' onclick='pagamento(item)' value='acquista' class='primary'/></a>";
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
		item.seat+
		"</td><td>" +
		prenotazione+
		"</td><td>" +
		acquisto
		"</td></tr></table>";
	}
	document.getElementById("tabellaProposte").innerHTML = table;
}

function pagamento(item){
	var dataFile ="http://localhost:8080/progetto-aereo-6/ticketsale/tickets/'item.code'/sale";
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
			paga(jsonObj);
		}		
	}	
	xhttp.open("GET", dataFile, true);
	xhttp.send();
}

function paga(jsonObj){	
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
	var prenotazione="<a href='login.html'><input type='button' value='prenota' class='primary'/></a>";
	var acquisto="<a href='login.html'><input type='button' value='acquista' class='primary'/></a>";
	var table="<table><tr><th>CodiceVolo</th>" +
	"<th>Aeroporto di partenza</th>" +
	"<th>Aeroporto di destinazione</th>" +
	"<th>orarioP</th>" +
	"<th>Prezzo</th>" +
	"<th>Posti</th>"+
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
		item.seat+
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
	var prenotazione="<a href='login.html'><input type='button' value='prenota' class='primary'/></a>";
	var acquisto="<a href='login.html'><input type='button' value='acquista' class='primary'/></a>";
	var table="<table><tr><th>CodiceVolo</th>" +
	"<th>Aeroporto di partenza</th>" +
	"<th>Aeroporto di destinazione</th>" +
	"<th>orarioP</th>" +
	"<th>Prezzo</th>" +
	"<th>Posti</th>"+
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
		item.seat+
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
	var table;
	if(window.XMLHttpRequest){
		xhttp=new XMLHttpRequest();
	}else{
		xhttp=new ActiceXObject("Microsoft.XMLHTTP");
	}
	xhttp.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			var jsonObj=JSON.parse(xhttp.responseText);
			table=creaTabellaCliente(jsonObj);
		}		
	}
	xhttp.open("GET", dataFile, true);
	xhttp.send();
}

function creaTabellaCliente(){
	var modifica="<a href='login.html'><input type='button' value='modifica' class='primary'/></a>";
	var conferma="<a href='login.html'><input type='button' value='acquista' class='primary'/></a>";
	var table="<table><tr><th>CodiceVolo</th>" +
	"<th>Aeroporto</th>" +
	"<th>orarioP</th>" +
	"<th>Prezzo</th>" +
	"<th>Posti</th>"+
	"<th>Prenota</th>"+
	"<th>Acquista</th>"+"</tr>";
	
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
		item.seat+
		"</td><td>" +
		conferma+
		"</td><td>" +
		modifica
		"</td></tr></table>";
	}
	document.getElementById("tabellaPrenotazioneCliente").innerHTML = table;	
}
function ricercaData(){
	var dataFile ="http://localhost:8080/progetto-aereo-6/ticketsale/tickets";
	var xhttp;
	var table;
	var form=document.forms["sceltaData"];
	var data="";
	console.log(form);
	for(var i=0;i<form.lenght;i++){
		data+=form[i].value  + "<br>";
	}
	console.log(data);
	if(window.XMLHttpRequest){
		xhttp=new XMLHttpRequest();
	}else{
		xhttp=new ActiceXObject("Microsoft.XMLHTTP");
	}
	xhttp.onreadystatechange=function(){
		
		if(this.readyState==4 && this.status==200){
			var jsonObj=JSON.parse(xhttp.responseText);
			table=ricercaTabellaData(jsonObj,data);
		}		
	}	
	xhttp.open("GET", dataFile, true);
	xhttp.send();
}

function ricercaTabellaData(jsonObj,data){
	var prenotazione="<a href='login.html'><input type='button' value='prenota' class='primary'/></a>";
	var acquisto="<a href='login.html'><input type='button' value='acquista' class='primary'/></a>";
	var table="<table><tr><th>CodiceVolo</th>" +
	"<th>Aeroporto di partenza</th>" +
	"<th>Aeroporto di destinazione</th>" +
	"<th>orarioP</th>" +
	"<th>Prezzo</th>" +
	"<th>Posti</th>"+
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
		item.seat+
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
var username = document.getElementById("name");
var password=document.getElementById("password");
var errorBox=document.getElementById("errorMessage");
var alertDiv='<div class="alert alert-danger alert-dismissible" role="alert">';
var alertBtn='<buttom type="buttom" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></buttom>';
if(username==null||username==""){
	errorBox.innerHTML= alertDiv+ alertBtn+'<strong>Attento!</strong> hai dimenticato di inserire l\'username.'+"</div>"
	username.focus();
	username.style.border="3px solid #990033";
	return false;
}

if(password==null||password==""){
	errorBox.innerHTML= alertDiv+ alertBtn+'<strong>Attento!</strong> hai dimenticato di inserire la password.'+"</div>"
	password.focus();
	password.style.border="3px solid #990033";
	return false;
}
if(password.value.length<8){
	errorBox.innerHTML=alertDiv+alertBtn+'<strong>Attento!</strong> la password deve essere almeno  di 8 caratteri.'+"</div>"
	password.focus();
	password.style.border="3px solid #990033";
	return false;
}
var cerca=cercaPersona(username,password);
	if (cerca == true)
		return true;
	else {
		errorBox.innerHTML = alertDiv + alertBtn + '<strong>Attento!</strong> non sei registrato!' + "</div>"
		username.focus();
		username.style.border = "3px solid #990033";
		return false;
	}
}

function registrazione() {
	var username = document.getElementById("username");
	var password = document.getElementById("password");
	var email=document.getElementById("email");
	var errorBox = document.getElementById("errorMessage");
	var alertDiv = '<div class="alert alert-danger alert-dismissible" role="alert">';
	var alertBtn = '<buttom type="buttom" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></buttom>';
	if (username == null || username == "") {
		errorBox.innerHTML = alertDiv + alertBtn + '<strong>Attento!</strong> hai dimenticato di inserire l\'username.' + "</div>"
		username.focus();
		username.style.border = "3px solid #990033";
		return false;
	}
	if (password == null || password == "") {
		errorBox.innerHTML = alertDiv + alertBtn + '<strong>Attento!</strong> hai dimenticato di inserire la password.' + "</div>"
		password.focus();
		password.style.border = "3px solid #990033";
		return false;
	}
	if (password.value.length < 8) {
		errorBox.innerHTML = alertDiv + alertBtn + '<strong>Attento!</strong> la password deve essere almeno  di 8 caratteri.' + "</div>"
		password.focus();
		password.style.border = "3px solid #990033";
		return false;
	}
	if (email == null || email == "") {
		errorBox.innerHTML = alertDiv + alertBtn + '<strong>Attento!</strong> hai dimenticato di inserire l\'username.' + "</div>"
		email.focus();
		email.style.border = "3px solid #990033";
		return false;
	}
	var reg=cercaRegistrazione();
	if(reg==true){
		errorBox.innerHTML = alertDiv + alertBtn + '<strong>Attento!</strong> non sei registrato!' + "</div>"
		username.focus();
		username.style.border = "3px solid #990033";
		return false;
	}else {
		return true;
	}
}
function cercaPersona(username,password){
	var datafile="http://localhost:8080/progetto-aereo-6/home";
	var xhttp;
	if(window.XMLHttpRequest){
		xhttp=new XMLHttpRequest();
	}else{
		xhttp=new ActiceXObject("Microsoft.XMLHTTP");
	}
	xhttp.onreadystatechange=function(){

		if(this.readyState==4 && this.status==200){
			var jsonObj=JSON.parse(xhttp.responseText);
			for( x in jsonObj){
				if(username==jsonObj[x].username && password==jsonObj[x].password){
					return true;
				}
			}
			return false;
		}		
	}
	xhttp.open("POST", datafile, true);
	xhttp.setRequestHeader("Content-type", " application/x-www-form-urlencoded");
	xhttp.send("username=val&password=val");
}
function cercaRegistrazione(username,password,email){
	var datafile="http://localhost:8080/progetto-aereo-6/home";
	var xhttp;
	if(window.XMLHttpRequest){
		xhttp=new XMLHttpRequest();
	}else{
		xhttp=new ActiceXObject("Microsoft.XMLHTTP");
	}
	xhttp.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			var jsonObj=JSON.parse(xhttp.responseText);
			for( x in jsonObj){
				if(username==jsonObj[x].username && password==jsonObj[x].password && email==jsonObj[x].email){
					return true;
				}
			}
			return false;
		}		
	}
	xhttp.open("POST", datafile, true);
	xhttp.setRequestHeader("Content-type", " application/x-www-form-urlencoded");
	xhttp.send("username=val&password=val");
}






///////////////////////////////////////////////////////////////////////////////////////////////////////////////


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






