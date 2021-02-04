forms = "";
//Get all pending approvals for this employee
function loadMe() {
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = () => {

		if (xhttp.readyState == 4 && xhttp.status == 200) {
            input = JSON.parse(xhttp.responseText);
            forms = input;
			for (let i = 0; i < input.length; i++) {
                //Begin to format a table
                let output = document.getElementById("requests");
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
				outputHtml += `<br><br><div class="card"><table class="table table-striped table-bordered table-hover"><tr class="table-dark">
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
                if (notes != null || notes.length == 0){
                    for (let j = 0 ; j < notes.length ; j++){
                        outputHtml += `<tr>
                                            <td>Note</td>
                                            <td>${notes[j]}</td>
                                        </tr>`;
                    }
                }
                //add another loop for notes for the request
                outputHtml += `</table><div class="brn-groupH"><input type="button" class="buttonH" onclick="approve(${i})" value="Approve Request"><input type="button" class="buttonH" onclick="deny(${i})" value="Deny Request"><input type="button" class="buttonH" onclick="requestMoreInfo(${i})" value="Request More information"></div>`
                output.innerHTML = outputHtml;
			}
		}
	}

	xhttp.open("GET", "http://localhost:8080/Project1/viewRequests.do", true);
	xhttp.setRequestHeader('Content-Type', 'application/json');
	xhttp.send();
};
window.onload = loadMe();

function approve(index){
    let idOfRt = forms[index].idOfRt;
    let sent = {
        "id" : idOfRt
    }
    let xhttp = new XMLHttpRequest();

        xhttp.onreadystatechange = () => {

            if (xhttp.readyState == 4 && xhttp.status == 200) {
                window.location.replace("http://localhost:8080/Project1/HTML/pendingReimbursements.html");
            }
        }

        xhttp.open("POST", "http://localhost:8080/Project1/approve.do", true);
        xhttp.send(JSON.stringify(sent));
}

function deny(index){

    let response = prompt("Please enter a reason to deny this request form");
    if (response == null || response == ""){

    }else{
        let idOfRt = forms[index].idOfRt;
        let sent = {
        "id" : idOfRt,
        "date" : response
        }
        let xhttp = new XMLHttpRequest();

        xhttp.onreadystatechange = () => {

            if (xhttp.readyState == 4 && xhttp.status == 200) {
                window.location.replace("http://localhost:8080/Project1/HTML/pendingReimbursements.html");
            }
        }

        xhttp.open("POST", "http://localhost:8080/Project1/deny.do", true);
        xhttp.send(JSON.stringify(sent));
    }
}

function requestMoreInfo(index){
    let response = prompt(`What mote information do you need from ${forms[index].employeeName}`);
    if (response == null || response == ""){

    }else{
        let idOfRt = forms[index].idOfRt;
        let note = {
        "id" : 0,
        "body" : response,
        "requestId" : idOfRt
        }
        let xhttp = new XMLHttpRequest();

        xhttp.onreadystatechange = () => {

            if (xhttp.readyState == 4 && xhttp.status == 200) {
                window.location.replace("http://localhost:8080/Project1/HTML/pendingReimbursements.html");
            }
        }

        xhttp.open("POST", "http://localhost:8080/Project1/addNote.do", true);
        xhttp.send(JSON.stringify(note));
    }
}