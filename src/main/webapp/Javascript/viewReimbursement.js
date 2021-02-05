forms = "";
//Get all pending approvals for this employee
function loadMe() {
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = () => {

		if (xhttp.readyState == 4 && xhttp.status == 200) {
			input = JSON.parse(xhttp.responseText);
			forms = input;
			console.log(forms);
			for (let i = 0; i < input.length; i++) {
				//Begin to format a table
				let output = document.getElementById("Reimbursements");
				let state = "";
				var notes = input[i].requestNotes;
				switch (input[i].state) {
					case "1":
						state = "Pending supervisor approval";
						break;
					case "2":
						state = "Pending Department Head approval";
						break;
					case "3":
						state = "Pending Benco approval";
						break;
					case "4":
						state = "Request Approved";
						break;
					case "5":
						state = "Request Denied";
						break;
					case "6":
						state = "Request Canceled by Owner";
						break;
					case "7":
						state = "Approved pending passing Grade";
						break;
					default:
						state = "ERROR";
						break;
				}
				let outputHtml = output.innerHTML;
				outputHtml += `<br><br><div class="card " style="clear:both">`
				outputHtml += `<table class="table table-striped table-bordered table-hover"><tr class="table-dark">
                                    <th>Category</th>
                                    <th>Value</th>
                                </tr>`;
				outputHtml += `<tr>
                                    <td>Name of Employee</td>
                                    <td>${input[i].employeeName}</td>
                                </tr>`;
				outputHtml += `<tr>
                                    <td>Status of Reimbursement Request</td>
                                    <td>${state}</td>
                                </tr>`;
				outputHtml += `<tr>
                                    <td>Reimbursement Amount</td>
                                    <td>${input[i].reimbursementAmount}</td>
                                </tr>`;
				outputHtml += `<tr>
                                    <td>Date and time request form submitted</td>
                                    <td>${input[i].dateOfRequest}</td>
                                </tr>`;
				outputHtml += `<tr>
                                    <td>Type of Event</td>
                                    <td>${input[i].eventType}</td>
                                </tr>`;
				outputHtml += `<tr>
                                    <td>Date of the Event</td>
                                    <td>${input[i].dateOfEvent}</td>
                                </tr>`;
				outputHtml += `<tr>
                                    <td>Location of Event</td>
                                    <td>${input[i].location}</td>
                                </tr>`;
				outputHtml += `<tr>
                                    <td>Event Related Attachments</td>
                                    <td>${input[i].relatedAttachments}</td>
                                </tr>`;
				outputHtml += `<tr>
                                    <td>Justification</td>
                                    <td>${input[i].justification}</td>
                                </tr>`;
				outputHtml += `<tr>
                                    <td>Days of work to be missed</td>
                                    <td>${input[i].workMissed}</td>
                                </tr>`;
				if (notes != null || notes.length == 0) {
					for (let j = 0; j < notes.length; j++) {
						outputHtml += `<tr>
                                            <td>Note</td>
                                            <td>${notes[j]}</td>
                                        </tr>`
					}
				}
				outputHtml += `</table>`

				// if the request is no longer pending, no need for buttons
				outputHtml += `<div class="btn-groupH">`
				if (input[i].state > 0 && input[i].state < 4) {
					outputHtml += `<input type="button" class="buttonH" onclick="addInformation(${i})" value="Add information"><input type="button" class="buttonH" onclick="cancel(${i})" value="Cancel">`
				} else if (input[i].state == 7) {
					outputHtml += `<input type="button" class="buttonH" onclick="submitGrade(${i})" value="Submit Grade">`;
				}
				outputHtml += `</div></div>`
				output.innerHTML = outputHtml;
			}
		}
	}

	xhttp.open("GET", "http://localhost:8080/Project1/viewMyRequests.do", true);
	xhttp.setRequestHeader('Content-Type', 'application/json');
	xhttp.send();
};
window.onload = loadMe();
function submitGrade(index) {
	let xhttp = new XMLHttpRequest();
	let idOfRt = forms[index].idOfRt;
	let grade = {
		"id": 0,
		"requestId": idOfRt
	}
	xhttp.onreadystatechange = () => {

		if (xhttp.readyState == 4 && xhttp.status == 200) {
			window.location.replace("http://localhost:8080/Project1/HTML/submitGrade.html");
		}
	}

	xhttp.open("POST", "http://localhost:8080/Project1/goToGrade.do", true);
	xhttp.send(JSON.stringify(grade));
}
function addInformation(index) {
	let response = prompt(`Enter Information`);
	if (response == null || response == "") {

	} else {
		let idOfRt = forms[index].idOfRt;
		let note = {
			"id": 0,
			"body": response,
			"requestId": idOfRt
		}
		let xhttp = new XMLHttpRequest();

		xhttp.onreadystatechange = () => {

			if (xhttp.readyState == 4 && xhttp.status == 200) {
				window.location.replace("http://localhost:8080/Project1/HTML/viewReimbursements.html");
			}
		}

		xhttp.open("POST", "http://localhost:8080/Project1/addNote.do", true);
		xhttp.send(JSON.stringify(note));
	}
}

function cancel(index) {

	if (!confirm(`Are you absolutely sure you want to cancel this reimbursement form?`)) {

	} else {
		let idOfRt = forms[index].idOfRt;
		let note = {
			"id": 0,
			"requestId": idOfRt
		}
		console.log(note)
		let xhttp = new XMLHttpRequest();

		xhttp.onreadystatechange = () => {

			if (xhttp.readyState == 4 && xhttp.status == 200) {
				window.location.replace("http://localhost:8080/Project1/HTML/viewReimbursements.html");
			}
		}

		xhttp.open("POST", "http://localhost:8080/Project1/cancel.do", true);
		xhttp.send(JSON.stringify(note));
	}
}

