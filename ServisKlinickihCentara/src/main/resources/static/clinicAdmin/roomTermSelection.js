
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
    //evt.currentTarget.className += " active";
    
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
			
			$(".roomTableRow").remove();
			var header = $("<tr class=\"roomTableRow\"></tr>");
			header.append("<td>Room number:</td><td>Choose one:</td>")
			var rows =document.getElementsByClassName("roomTableRow")
	
			$("#roomSelection").append(header);
			
			for(i=0; i< retVal.length; i++)
			{
				var row = $("<tr class=\"roomTableRow\"></tr>");
				row.append("<td>"+retVal[i].number+"</td><td><button " +
						"class=\"chooseRoom\" name=\"roomId\" value=\""+retVal[i].Id+"\">Reserve</button></td>");
				$("#roomSelection").append(row);
				//console.log(i);
			}
			localStorage.setItem("startTerm", startTerm);
			localStorage.setItem("endTerm", endTerm);
			
		},
		error : function(errorThrown) {
			alert("No free rooms found! Please select another term!");
		}
	});
	
}

$(document).on('click',"button[name=roomId]",function(e)
		{
			//Term:
			var roomId = $(this).attr("value");
			var startTime = localStorage.getItem("startTerm");
			var endTime = localStorage.getItem("endTerm");
			var dateOfCheckup = localStorage.getItem("dateOfCheckup");
			
			var clinicId = localStorage.getItem("clinicId");
			var doctorId = localStorage.getItem("doctorId");
			var typeOfExam = localStorage.getItem("typeOfExam");
			/*
			var predefined = true;
			var active = true; //predefined is valid
			var cancelled = false;
			*/
			$.ajax({
				method: "POST",
				url: "predefinedAppointment/makeAppointment",
				dataType : "json",
				cache: false,
				contentType : 'application/json',
				data: JSON.stringify({"startTime": startTime, "endTime": endTime, 
					"clinicId": clinicId, "dateOfCheckup": dateOfCheckup, "doctorId" : doctorId,
					"typeOfExam" : typeOfExam}),
				headers: { "Authorization": "Bearer " + token},
				success : function(response) {
					alert(response.message);
					$("#reserveButton").html('Reservation confirmed');
					$('.chooseTerm').prop("disabled", true);
	                $('.chooseRoom').prop("disabled",true);
	                alert("Return to homepage:");
	                window.location.href = "clinicAdmin.html";
				},
	         	error : function(errorThrown) {
	         		alert(errorThrown);
	         	}
			});
			/*
			private Long id;
			private Patient patient;
			private Term term;
			private Clinic clinic;
			private Doctor employee;
			private AppointmentType type;
			private TypeOfExam typeOfExam;
			private AppointmentReport report;
			private boolean predefined;
			private boolean active;
			private boolean cancelled;
			*/
					
	});