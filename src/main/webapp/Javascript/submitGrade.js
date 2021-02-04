function loadMe(){
    let xhttp = new XMLHttpRequest();
	let gradingFormats;
	xhttp.onreadystatechange = () => {

		if (xhttp.readyState == 4 && xhttp.status == 200) {
			gradingFormats = JSON.parse(xhttp.responseText);
            let tableLocation = document.getElementById("table");
            console.log(gradingFormats);
            
			let newInnerHtml = `<tr class="table-dark"><th colspan="2" style=" text-align: center;">Grade</th></tr>`;
			for (var i = 0 ; i < gradingFormats.length; i++){
				newInnerHtml += `<tr><td><p>${gradingFormats[i].grade}</p></td><td><input type="radio" id="gradingFormat${i}" name="gradingFormat" value="${gradingFormats[i].id}" checked></td></tr>`
			}
			tableLocation.innerHTML = newInnerHtml;
		}
	}

	xhttp.open("GET", "http://localhost:8080/Project1/getGradingFormats.do", true);
	xhttp.setRequestHeader('Content-Type', 'application/json');
	xhttp.send();
}
window.onload(loadMe());

function submit(){
    let xhttp = new XMLHttpRequest();
    var inputs=document.querySelectorAll("input[type=radio]")
    var x = inputs.length;

   	for (var i = 0 ; i < x ; i++){
  		if (inputs[i].checked){
              let grades = inputs[i].value;
              let grade = {
                  id:grades
              }
              console.log(inputs[i].value)
            xhttp.onreadystatechange = () => {
            if (xhttp.readyState == 4 && xhttp.status == 200) {
                window.location.replace("http://localhost:8080/Project1/HTML/viewReimbursements.html");
                }
            }
        
            xhttp.open("POST", "http://localhost:8080/Project1/submitGrade.do", true);
            xhttp.send(JSON.stringify(grade));
   			break;
   		}
   	}
}