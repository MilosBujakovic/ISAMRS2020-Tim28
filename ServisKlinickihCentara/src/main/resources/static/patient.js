


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
    getLoggedPatient();
    getClinicsForBasicView();
    getPatientsAppointments();
});

function getLoggedPatient() {
	$.ajax({
		type : 'GET',
		url : "/auth/getLoggedPatient",
		cache: false,
		dataType: "json",
		headers: { "Authorization": "Bearer " + token},
		success : function(patient) {
			if (typeof (patient) == 'undefined') {
				window.location.href = "index.html";
			} else {
			    localStorage.setItem("username",patient.email);

				$(document).ready(function(){

					$("#name").attr('value',patient.name);
					$("#surname").attr('value',patient.surname);
					$("#name").attr('value',patient.name);
                    $("#address").attr('value',patient.address);
                    $("#city").attr('value',patient.city);
                    $("#country").attr('value',patient.country);
					$("#phone").attr('value',patient.phone);
				});
			}
		},
	    error : function(errorThrown) {
		    alert(errorThrown);
	    }
    });
}



function getSpecialities(){
	$.ajax({
    		type : 'GET',
    		url : "/clinic/getSpecialities",
    		cache: false,
    		dataType: "json",
            headers: { "Authorization": "Bearer " + token},
    		success : function(specialities) {
    			$("#typeOfSpeciality").find('optionl:gt(0)').remove();
    			$.each(
    					specialities,
    					function(index,speciality){
    						$("#typeOfSpeciality").append('<option value=\"' + speciality + '\">' + speciality + '</option>');
    					}
    			)
    		},
    		error : function(errorThrown) {
    			alert(errorThrown);
    		}
    	});
}


function getClinicsForBasicView(){
	$.ajax({
    		type : 'GET',
    		url : "/clinic/getClinicsForBasicView",
    		cache: false,
    		dataType: "json",
            headers: { "Authorization": "Bearer " + token},
    		success : function(clinics) {
    			$("#clinicsForBasicViewFilter").find("tr:not(:first)").remove();
    			$.each(
    					clinics,
    					function(index,clinic){
    						var tr = $('<tr></tr>');
    						var rating = "No rating";
                            console.log(clinic.rating);
    						if(clinic.rating !== '0.0'){
    							rating = clinic.average_rating;
    						}

    						tr.append(
    						        "<td>" + clinic.id+"</td>" +
    								"<td>" + clinic.name+"</td>" +
    								"<td>" + clinic.address+"</td>" +
    								"<td>" + clinic.speciality+"</td>" +
    								"<td>" + rating+"</td>" +
    								"<td><button name=\"" + clinic.id + "\" id=\"clinic\" class=\"clinicButton\" background-color=\"#555555\">" + 'Predefined appointments'+"</button></td>"

    						);
    						$("#clinicsForBasicViewFilter").append(tr);
    					}
    			)
    		},
    		error : function(errorThrown) {
    			alert(errorThrown);
    		}
    	});
}



$(document).on('click', '#clinic', function(e) {
	e.preventDefault();
		var clinicId = $(this).attr("name");
        localStorage.setItem("clickedClinicId", clinicId);
        console.log(clinicId);

        var win = window.open('http://localhost:8080/clinicForPatient.html', '_blank');



})




function basicFilterSortingClinics(){
	var typeOfSpeciality = document.getElementById("typeOfSpeciality").value;
	var clinicsSortingType = document.getElementById("clinicsSortingType").value;
    console.log(clinicsSortingType);
	$.ajax({
		type : 'POST',
		url : "/clinic/basicFilterSortingClinics",
		dataType : "json",
		cache: false,
		contentType : 'application/json',
		data: JSON.stringify({"nameAddressSorting": clinicsSortingType,"speciality": typeOfSpeciality}),
		headers: { "Authorization": "Bearer " + token},
		success : function(clinics) {

			$("#clinicsForBasicViewFilter").find("tr:not(:first)").remove();

			if(clinics.length === 0){
				$("#messageClinicsForFilterFound").show();
			}else{
				$("#messageClinicsForFilterFound").hide();

				$.each(
						clinics,
						function(index,clinic){
							var tr = $('<tr></tr>');
                            var rating = "No rating";

                            if(clinic.rating !== '0.0'){
                                rating = clinic.average_rating;
                            }
							tr.append(
                                "<td>" + clinic.id+"</td>" +
                                "<td>" + clinic.name+"</td>" +
       							"<td>" + clinic.address+"</td>" +
       							"<td>" + clinic.speciality+"</td>" +
                                "<td>" + rating+"</td>" +
                                "<td><button name=\"" + clinic.id + "\" id=\"clinic\" class=\"clinicButton\" background-color=\"#555555\">" + 'Predefined appointments'+"</button></td>"
                            );
                            $("#clinicsForBasicViewFilter").append(tr);
						}
				)
			}
		},
		error : function(errorThrown) {
			alert(errorThrown);
		}
	});
}



function getPatientsAppointments(){
	var email = localStorage.getItem("email");
    console.log(email);
	$.ajax({
		type : 'POST',
		url : "/appointment/getPatientsAppointments",
		dataType : "json",
		cache: false,
		contentType : 'application/json',
		data: JSON.stringify({"email": email}),
		headers: { "Authorization": "Bearer " + token},
		success : function(appointments) {
            console.log(appointments.length);
			$("#reservedAppointmentsTable").find("tr:not(:first)").remove();

			if(appointments.length === 0){
				$("#reservedAppointmentsTable").hide();
                $("#messageReservedAppointmentsFound").show();
			}else{
				$("#reservedAppointmentsTable").show();
                $("#messageReservedAppointmentsFound").hide();
				$.each(
						appointments,
						function(index,appointment){
							var tr = $('<tr></tr>');
                            var button = "";
                            console.log(appointment.status);
                            if(appointment.status == 'ACCEPTED'){
                                button = "<button name=\"" + appointment.id + "\" id=\"cancelAppointment\"  background-color=\"#f44336\">" + 'Cancel'+"</button>";
                            }


							tr.append(
                                "<td>" + appointment.id+"</td>" +
                                "<td>" + appointment.timestamp+"</td>" +
       							"<td>" + appointment.room+"</td>" +
       							"<td>" + appointment.doctor+"</td>" +
                                "<td>" + appointment.typeSpeciality+"</td>" +
                                "<td>" + appointment.price+"</td>" +
                                "<td>" + appointment.status+"</td>" +
                                "<td>" + button + "</td>"
                            );
                            $("#reservedAppointmentsTable").append(tr);
						}
				)
			}
		},
		error : function(errorThrown) {
			alert(errorThrown);
		}
	});
}



$(document).on('click',"#cancelAppointment",function(e){
	e.preventDefault();
    var appointmentId = $(this).attr("name");
    console.log(appointmentId);
    $.ajax({
		type : 'PUT',
		url : "/appointment/cancelAppointment/" + appointmentId,
		dataType: "json",
		cache: false,
		headers: { "Authorization": "Bearer " + token},
		success : function(message) {
            if(message.success == true){
                getPatientsAppointments();
            }
            alert(message.message);
	    },
		error : function(errorThrown) {
			alert(errorThrown);
		}
	});
})





$(document).ready(function(){
	$('#typeOfSpeciality').on('change',function() {
		basicFilterSortingClinics();
	});

});

$(document).ready(function(){
	$('#clinicsSortingType').on('change',function() {
		basicFilterSortingClinics();
	});

});

$(document).ready(function(){
    getSpecialities();
});




$(document).on("submit", "#updatePatient", function(e){
	e.preventDefault();
	var name = document.getElementById("name").value;
	var surname = document.getElementById("surname").value;
	var address = document.getElementById("address").value;
	var city = document.getElementById("city").value;
    var country = document.getElementById("country").value;
    var phone = document.getElementById("phone").value;


	if(name === "" || surname === "" || address === "" || city === "" || country === "" || phone === ""){
		alert("You must fill all fields!");
		return;
	}

    var token = localStorage.getItem("token");

	$.ajax({
		type : 'PUT',
		contentType : 'application/json',
		dataType : "json",
		cache: false,
		url : "/user/updatePatient",
		headers: { "Authorization": "Bearer " + token},
		data : JSON.stringify({"email":localStorage.getItem("username"),"name": name,"surname": surname,"address":address,"city":city,
			"country":country,"phone": phone
		}),
		success : function(retVal) {
			alert(retVal.message);
		},
		error : function(errorThrown) {
			alert(errorThrown);
		}
	});

})



$(document).on("submit", "#changePassword", function(e){
	e.preventDefault();
	var oldPassword = document.getElementById("oldPassword").value;
	var newPassword = document.getElementById("newPassword").value;


    if (oldPassword === ""){
        alert("You must enter old password!");
        return;
    }

    if (newPassword === ""){
         alert("You didn't enter new password");
         return;
    }




	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		cache: false,
		url : "/auth/change-password",
		dataType: "json",
		headers: { "Authorization": "Bearer " + token},
		data : JSON.stringify({"oldPassword": oldPassword, "newPassword": newPassword}),
		success : function(userTokenState) {
            //refreshToken();
            localStorage.setItem("token", userTokenState.access_token);
            localStorage.setItem("expires_in", userTokenState.expires_in);
		    alert("Your password was successfully changed :)")
		},
		error : function(error) {
			alert("Something is wrong with changing password!");
		}
	});

})



/*function refreshToken(){
    $.ajax({
    		type : 'POST',
    		contentType : 'application/json',
    		cache: false,
    		url : "/auth/refresh",
    		dataType: "json",
    		headers: { "Authorization": "Bearer " + token},
    		success : function(userTokenState) {
    		    //localStorage.clear();
                localStorage.setItem("token", userTokenState.access_token);
                localStorage.setItem("expires_in", userTokenState.expires_in);
    		},
    		error : function(error) {
    			alert("Something is wrong with refreshing token!");
    		}
    	});

}*/

$(document).on("change", "input[id='passwordCheckbox']", function () {
     if(this.checked) {
         document.getElementById("oldPasswordId").style.display = "block";
         document.getElementById("newPasswordId").style.display = "block";
         document.getElementById("changeButton").style.display = "block";

     }else{
         document.getElementById("oldPasswordId").style.display = "none";
         document.getElementById("newPasswordId").style.display = "none";
         document.getElementById("changeButton").style.display = "none";
     }
});





$(document).on('click',"#logout",function(e){
	e.preventDefault();
	if (confirm('Are you sure that you want to log out?')) {

	    localStorage.clear();


		$.ajax({
			type : 'GET',
			url : "/auth/logout",
			dataType: "json",
			headers: { "Authorization": "Bearer " + token},
			success : function(message) {
			    alert(message.message);
				window.location.href = "index.html";
			},
			error : function(errorThrown) {
				alert(errorThrown);
			}
		});
	}

})


