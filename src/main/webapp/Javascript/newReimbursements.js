function loadMe(){
	getEventTypes();
	getAvailableFunds()
}
window.onload = loadMe();

function getAvailableFunds(){
	let xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = () => {

		if (xhttp.readyState == 4 && xhttp.status == 200) {
			let employee = JSON.parse(xhttp.responseText);
			let availablefunds = document.getElementById("funds");
			availablefunds.innerHTML = `$${employee.funds}`
		}
	}

	xhttp.open("GET", "http://localhost:8080/Project1/getLoggedInEmployee.do", true);
	xhttp.setRequestHeader('Content-Type', 'application/json');
	xhttp.send();
}
function getEventTypes(){
	let xhttp = new XMLHttpRequest();
	let employee;	
	let eventTypes;
	xhttp.onreadystatechange = () => {

		if (xhttp.readyState == 4 && xhttp.status == 200) {
			eventTypes = JSON.parse(xhttp.responseText);
			let formLocation = document.getElementById("eventTypeTd");

			let newInnerHtml = "<table>";
			for (var i = 0 ; i < eventTypes.length; i++){
				newInnerHtml += `<tr><td style="width:50%;"><p style="text-align: right;"> ${eventTypes[i].name}</p></td><td style="width:50%; text-align:left;"><input type="radio" id="eventType${i}" name="eventType" value="${eventTypes[i].name}" checked></td></tr>`
			}
			newInnerHtml += "</table>"
			
			formLocation.innerHTML = newInnerHtml;

			//add event listeners
			var inputs=document.querySelectorAll("input[type=radio]")
			var x = inputs.length;
			while(x--){
				inputs[x].addEventListener("change",function(){
					let index = this.id;
					let lastChar = index.substr(index.length - 1);
					let costinput = document.getElementById("cost");
					let projectedValue = document.getElementById("projectedAmount");
					let num = eventTypes[lastChar].coverage * costinput.value * 0.01;
					let availablefunds = document.getElementById("funds").innerHTML;
					let availablefundsOut = availablefunds.substr(1);
					if (availablefundsOut < num){
						num = availablefundsOut;
					}
					// TODO Fix num to show only 2 decimal places
					projectedValue.innerHTML = `$${num}`
				},0);
			}

			// let inputCost = document.getElementById("cost");
			// inputCost.addEventListener("change",function(){
			// 	for (var i = 0 ; i < x ; i++){
			// 		if (inputs[i].checked){
			// 			let index = this.id;
			// 			let lastChar = index.substr(index.length - 1);
			// 			let projectedValue = document.getElementById("projectedAmount");
			// 			let num = eventTypes[lastChar].coverage * costinput.value * 0.01;
			// 			let availablefunds = document.getElementById("funds").value;
			// 			availablefunds = availablefunds.substring(1);
			// 			if (availablefunds < num){
			// 				num = availablefunds;
			// 			}
			// 			// TODO Fix num to show only 2 decimal places
			// 			projectedValue.innerHTML = `$${num}`
			// 			break;
			// 		}
			// 	}
			// });
		}
	}

	xhttp.open("GET", "http://localhost:8080/Project1/getEventType.do", true);
	xhttp.setRequestHeader('Content-Type', 'application/json');
	xhttp.send();
}
function submitRequest() {
	let date = document.getElementById("date").value;
	//console.log(date);
	createEvent();
}

function createEvent(){
	let name = document.getElementById("name").value;
	let date = document.getElementById("date").value;
	let location = document.getElementById("location").value;
	let gradeFormat = 1;
	let time = document.getElementById("time").value;

	let eventTypeId = 0;
	let e = document.getElementsByName("eventType");
	for (var i = 0 ; i < e.length ; i++){
		if (e[i].checked){
			let index = e[i].id;
			let lastChar = index.substr(index.length - 1);
			let idOfEventType = lastChar - 1 + 2;
			eventTypeId = idOfEventType;
			break;
		}
	}

	let relatedAttachment = document.getElementById("relatedAttachments").value;
	let newEvent = {
		name,
		date,
		location,
		gradeFormat,
		eventTypeId,
		relatedAttachment,
		time
	}

	let xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = () => {

		if (xhttp.readyState == 4 && xhttp.status == 200) {
			let eventId = xhttp.responseText;
			createRequest(eventId);
		}
	}

	xhttp.open("POST", "http://localhost:8080/Project1/createEvent.do", true);
	xhttp.setRequestHeader('Content-Type', 'application/json');
	xhttp.send(JSON.stringify(newEvent));
}

function createRequest(inEventId){
	let timeMissed = document.getElementById("timeMissed").value;
	let justification = document.getElementById("justification").value;
	let attachedApproval = document.getElementById("attachedApproval").value;
	let amount = document.getElementById("projectedAmount").innerHTML;
	amount = amount.substr(1);
	let form = {
		timeMissed,
		justification,
		attachedApproval,
		amount,
		eventId:inEventId
	}
	let xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = () => {

		if (xhttp.readyState == 4 && xhttp.status == 200) {
			alert("Your form has been submitted");
			window.location.replace("http://localhost:8080/Project1/HTML/homePage.html");
		}
	}

	xhttp.open("POST", "http://localhost:8080/Project1/submitRequest.do", true);
	xhttp.setRequestHeader('Content-Type', 'application/json');
	xhttp.send(JSON.stringify(form));
}