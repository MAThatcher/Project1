function loadMe(){
	getName();
	getNotifications();
}
window.onload = loadMe();
function getName() {
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = () => {

		if (xhttp.readyState == 4 && xhttp.status == 200) {

			let employee = JSON.parse(xhttp.responseText);
			document.getElementById("welcomeh1").innerHTML = `Welcome ${employee.fname} ${employee.lname}`;
		}
	}

	xhttp.open("GET", "http://localhost:8080/Project1/getLoggedInEmployee.do", true);
	xhttp.setRequestHeader('Content-Type', 'application/json');
	xhttp.send();
}
function getNotifications(){
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = () => {

		if (xhttp.readyState == 4 && xhttp.status == 200) {
			input = JSON.parse(xhttp.responseText);
			if (input.length > 0){
				let div = document.getElementById("notificationsGoHere");
				let output = `<div class=card><h3>Notifications</h3>`;
				for (let i = 0 ; i < input.length ; i++){
					output+= `<p>${i+1}- ${input[i].body}</p>`;
				}
				output+= `</div><br><br>`
				div.innerHTML = output;
			}
			
			
		}
	}

	xhttp.open("GET", "http://localhost:8080/Project1/getNotifications.do", true);
	xhttp.setRequestHeader('Content-Type', 'application/json');
	xhttp.send();
}
function logOut() {
	let xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = () => {

		if (xhttp.readyState == 4 && xhttp.status == 200) {
			window.location.replace("http://localhost:8080/Project1/HTML/index.html");
		}
	}

	xhttp.open("GET", "http://localhost:8080/Project1/logOut.do", true);
	xhttp.send();
}