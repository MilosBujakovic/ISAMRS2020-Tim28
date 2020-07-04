
var token = localStorage.getItem("token");
var authority = localStorage.getItem("authority");
if (token == null){
    window.location.href = "index.html";
}else{
    if (authority != "CLINIC_ADMIN"){
        window.location.href = "./../index.html";
    }

}

$(document).ready(function(){
	console.log("Page loaded");
	var startTime = localStorage.getItem("startTime");
	var endTime = localStorage.getItem("endTime");
	var typeOfExam = localStorage.getItem("typeOfExam");
	$.ajax({
		method: "POST",
		url: "predefinedAppointment/getTerms",
		dataType : "json",
		cache: false,
		contentType : 'application/json',
		data: JSON.stringify({"startTime": startTime, "endTime": endTime, "typeOfExam": typeOfExam}),
		headers: { "Authorization": "Bearer " + token},
		success : function(retVal) {
			alert(retVal.message);
		},
		error : function(errorThrown) {
			alert(errorThrown);
		}
	})
	
	
    displayFreeTermPicker();

});

function displayFreeTermPicker()
{
	/*TODO: na bekend posaljem odabrani slot i izgenerisem sve termine 
	 koje posaljem kao listu koju poslije prikažem
	 	*/
	var table = $("<table></table>");
	var startTime = localStorage.getItem("startTime");
	var finalTime = localStorage.getItem("endTime");
	/*
    table.append(
        "<tr><td>" + doctorSlots.name + "</td></tr>" +
        "<tr><td>" + 'Surname: ' + doctorSlots.surname + "</td></tr>" +
        "<tr><td>" + 'Rating: ' + doctorSlots.rating + "</td></tr>"
       
    )*/
}