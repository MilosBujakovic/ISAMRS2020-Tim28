
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
			//console.log(retVal);
			var i;
			//var table = $("#freeTermSelection");
			for(i=0; i< retVal.length; i++)
			{
				var row = $("<tr></tr>");
				row.append("<td>"+retVal[i].startTime+"</td><td>"+retVal[i].endTime+"</td><td>"
						+"<input type=\"radio\" class=\"chooseTerm\" name=\"term\" value=\""+retVal[i].startTime+" "+retVal[i].endTime+"\"></td>");
				$("#freeTermSelection").append(row);
				//console.log(i);
			}
			
			//localStorage.setItem("terms", retVal);
			
			//document.getElementById("freeTermSelection").innerHTML = table;
			//alert(retVal.message);
		},
		error : function(errorThrown) {
			alert(errorThrown);
		}
	})
});

$(document).on('click',"input[name=term]",function(e)
		{
					var startTerm, endTerm;
					var termDuration = $(this).attr("value").split(" ");
					
					//console.log(i);
					startTerm = termDuration[0];
					endTerm = termDuration[1];
					
					displayFreeRoomPicker(startTerm, endTerm);
					//console.log("displayRooms");
					
		});

function displayFreeRoomPicker(startTerm, endTerm)
{
	var dateOfCheckup = localStorage.getItem("dateOfCheckup");
	var clinicId = localStorage.getItem("clinicId");
	$.ajax({
		method: "POST",
		url: "roomAdmin/findFreeRooms",
		dataType : "json",
		cache: false,
		contentType : 'application/json',
		data: JSON.stringify({"startTime": startTerm, "endTime": endTerm, "clinicId": clinicId, "date": dateOfCheckup}),
		headers: { "Authorization": "Bearer " + token},
		success : function(retVal) {
			
			
		},
		error : function(errorThrown) {
			alert("No free rooms found! Please select another term!");
		}
	});
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