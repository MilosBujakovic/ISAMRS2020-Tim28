

var token = localStorage.getItem("token");
var authority = localStorage.getItem("authority");
if (token == null){
    window.location.href = "index.html";
}else{
    if (authority != "PATIENT"){
        window.location.href = "index.html";
    }
}


$(document).ready(function(){
    getClinicNameAddressRating();
    getPredefinedAppointments();
    getTypeOfExamsWithoutOperations();
    if(localStorage.getItem("forAppointments") == "true"){
         document.getElementById("predefinedAppointmentsButton").click();
    }else{
        document.getElementById("clinicButton").click();
    }
    setTimeout(function() {
        localStorage.removeItem("forAppointments");
    }, 500);

});



function getClinicNameAddressRating(){
    var clinicId = localStorage.getItem("clickedClinicId");
    var token = localStorage.getItem("token");
    $.ajax({
            type : 'GET',
            dataType : "json",
            cache: false,
            url : "/clinic/getClinicNameAddressRating/" + clinicId,
            headers: { "Authorization": "Bearer " + token},
            success : function(clinicData) {
                $("#clinicName").text(clinicData.name);
                $("#clinicAddress").text(clinicData.address);
                $("#clinicRating").text(clinicData.average_rating);
            },
            error : function(errorThrown) {
                alert(errorThrown);
            }
        });

}



function getPredefinedAppointments(){
    var clinicId = localStorage.getItem("clickedClinicId");
    var token = localStorage.getItem("token");
    $.ajax({
            type : 'GET',
            dataType : "json",
            cache: false,
            url : "/appointment/getPredefinedAppointments/" + clinicId,
            headers: { "Authorization": "Bearer " + token},
            success : function(appointments) {
                $("#predefinedAppointmentsTable").find("tr:not(:first)").remove();
                if(appointments.length == 0){
                    $("#predefinedAppointmentsTable").hide();
                    $("#messagePredefineAppointementsFound").show();
                }else{
                    $("#predefinedAppointmentsTable").show();
                    $("#messagePredefineAppointementsFound").hide();


                    $.each(appointments, function(index,a){
                        var tr = $("<tr></tr>");

                    	tr.append(
                    			"<td>" + a.id+"</td>" +
                    			"<td>" + a.timestamp+"</td>" +
                    		    "<td>" + a.room+"</td>" +
                    			"<td>" + a.doctor+"</td>" +
                    			"<td>" + a.typeSpeciality+"</td>" +
                    			"<td>" + a.price+"</td>" +
                    			"<td><button name=\"" + a.id + "\" id=\"appointmentReserve\" background-color=\"#008CBA\">" + 'Reserve'+"</button></td>"
                    	);
                    $("#predefinedAppointmentsTable").append(tr);
                })
                }
            },
            error : function(errorThrown) {
                alert(errorThrown);
            }
        });

}

$(document).on('click', '#appointmentReserve', function(e) {
	e.preventDefault();
	    var email = localStorage.getItem("email")
		var appointmentId = $(this).attr("name");
        console.log(appointmentId);
        var token = localStorage.getItem("token");
        $.ajax({
        		type : 'POST',
        		url : "/appointment/quickAppointmentReservation",
        		dataType : "json",
        		cache: false,
        		contentType : 'application/json',
        		data: JSON.stringify({"email": email,"appointmentId": appointmentId}),
        		headers: { "Authorization": "Bearer " + token},
        		success : function(message) {
                    if(message.success == true){
                        getPredefinedAppointments();
                    }
                    alert(message.message);

        		},
        		error : function(errorThrown) {
        			alert(errorThrown);
        		}
        	});




})


$(document).on('submit', '#doctorsSearchForm', function(e) {
	e.preventDefault();
	    var clinicId = localStorage.getItem("clickedClinicId");
        var token = localStorage.getItem("token");
        var date = document.getElementById("dateOfCheckup").value;
        var typeOfExam = document.getElementById("typeOfExam").value;
	 	$.ajax({
         		type : 'POST',
         		url : "/clinic/checkClinicHasFreeDoctorsForSpecificDateAndTypeOfExam/" + clinicId,
         		cache: false,
         		dataType: "json",
         		contentType : 'application/json',
         		data: JSON.stringify({"date": date,"typeOfExam": typeOfExam}),
                headers: { "Authorization": "Bearer " + token},
         		success : function(message) {
                    if(message.success == false){
                        alert(message.message);
                    }else{
                        goToDoctorsFreeSlotsPage();
                    }

         		},
         		error : function(errorThrown) {
         			alert(errorThrown);
         		}
         	});

})


function goToDoctorsFreeSlotsPage(){
    var date = document.getElementById("dateOfCheckup").value;
    var typeOfExam = document.getElementById("typeOfExam").value;
    var clinicId = localStorage.getItem("clickedClinicId");

    localStorage.setItem("choosenDate",date);
    localStorage.setItem("choosenExam",typeOfExam);
    localStorage.setItem("clickedClinicForDoctorsTerms",clinicId);
    var win = window.open('http://localhost:8080/doctorsFreeSlots.html', '_blank');
}


function getTypeOfExamsWithoutOperations(){
    var token = localStorage.getItem("token");
 	$.ajax({
     		type : 'GET',
     		url : "/typeOfExam/getTypeOfExamsWithoutOperations",
     		cache: false,
     		dataType: "json",
            headers: { "Authorization": "Bearer " + token},
     		success : function(types) {
     			$("#typeOfExam").find('optionl:gt(0)').remove();
     			$.each(
     					types,
     					function(index,type){
     						$("#typeOfExam").append('<option value=\"' + type + '\">' + type + '</option>');
     					}
     			)
     		},
     		error : function(errorThrown) {
     			alert(errorThrown);
     		}
     	});
 }
