function logIn() {
	let xhttp = new XMLHttpRequest();

	let e = document.getElementById("email").value;
	let p = document.getElementById("password").value;
	let emp = {
		email: e,
		password: p
	}
	xhttp.onreadystatechange = () => {

		if (xhttp.readyState == 4 && xhttp.status == 200) {
			let employee = JSON.parse(xhttp.responseText);
			if (employee !== null) {
				window.location.replace("http://localhost:8080/Project1/HTML/homePage.html");
			} else {
				document.getElementById("badinput").innerHTML = "Incorrect Login information"
			}
		}
	}

	xhttp.open("POST", "http://localhost:8080/Project1/login.do", true);
	xhttp.setRequestHeader('Content-Type', 'application/json');
	xhttp.send(JSON.stringify(emp));
}