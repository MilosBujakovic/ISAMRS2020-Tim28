


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
    getPatientsHistory();

});


$(document).ready(function(){
    getMedicalRecord();
});



function getLoggedPatient() {
    var token = localStorage.getItem("token");

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



function getMedicalRecord() {
    var token = localStorage.getItem("token");
    var email = localStorage.getItem("email");

	$.ajax({
		type : 'POST',
		url : "/medicalRecord/getMedicalRecord",
		cache: false,
		contentType : 'application/json',
		dataType: "json",
		data: JSON.stringify({"email": email}),
		headers: { "Authorization": "Bearer " + token},
		success : function(medicalRecord) {

            if(medicalRecord.age != null){
                $("#medicalRecordData").show();
                $("#noMedicalRecordMessage").hide();
                $("#age").text(medicalRecord.age);
                $("#height").text(medicalRecord.height);
                $("#weight").text(medicalRecord.weight);
                $("#diopter").text(medicalRecord.diopter);
                $("#bloodType").text(medicalRecord.bloodtype);
                $("#rhFactor").text(medicalRecord.rhfactor);
                $("#alergies").text(medicalRecord.alergies);

                if (medicalRecord.diseaseHistoryDTOS.length > 0){
                    $("#diseaseHistoryFound").show();
                    $("#diseases").show();

                    $.each(
                        medicalRecord.diseaseHistoryDTOS,
                        function(index,disease){
                            var table = $("<table></table>");
                            table.append(
                                "<tr><td>" + 'Desease: ' + disease.desease + "</td></tr>" +
                                "<tr><td>" +  'Treatment: ' + disease.treatment + "</td></tr>" +
                                "<tr><td>" +  'Description: ' + disease.description + "</td></tr>"
                            )
                            $("#diseases").append("<li>" + disease.date + "</li>");
                            $("#diseases").append(table);

                            if (disease.prescriptionMrDTOS.length > 0){
                                $("#diseases").append("<p>" + 'Prescriptions:' + "</p>");
                                var prescriptionTable = $("<table class=\"table table-bordered table-hover\" style=\"width:350px\" bgcolor=\"#d9d9d9\"><thead><th>medication</th><th>amount per day</th></thead></table>");
                                $.each(
                                   disease.prescriptionMrDTOS,
                                   function(index,prescription){

                                        prescriptionTable.append(
                                            "<tr><td>"  + prescription.name + "</td><td>" + prescription.amount + "</td></tr>"
                                        )
                                   }
                                )
                                 $("#diseases").append(prescriptionTable);
                            }
                        }
                    )
                }else{
                    $("#diseaseHistoryFound").hide();
                    $("#diseases").hide();
                }


            }else{
                $("#medicalRecordData").hide();
                $("#noMedicalRecordMessage").show();
            }

		},
	    error : function(errorThrown) {
		    alert(errorThrown);
	    }
    });
}



function getSpecialities(){
     var token = localStorage.getItem("token");
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
     						$("#specialityOfCheckup").append('<option value=\"' + speciality + '\">' + speciality + '</option>');
     					}
     			)
     		},
     		error : function(errorThrown) {
     			alert(errorThrown);
     		}
     	});
}

function getTypeOfExams(){
     var token = localStorage.getItem("token");
 	$.ajax({
     		type : 'GET',
     		url : "/typeOfExam/getTypeOfExams",
     		cache: false,
     		dataType: "json",
            headers: { "Authorization": "Bearer " + token},
     		success : function(types) {
     			$("#examTypes").find('optionl:gt(0)').remove();
     			$.each(
     					types,
     					function(index,type){
     						$("#examTypes").append('<option value=\"' + type + '\">' + type + '</option>');
     						$("#typeOfExams").append('<option value=\"' + type + '\">' + type + '</option>');
     					}
     			)
     		},
     		error : function(errorThrown) {
     			alert(errorThrown);
     		}
     	});
 }


function getClinicsForBasicView(){
    var token = localStorage.getItem("token");
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

    						tr.append(
    						        "<td>" + clinic.id + "</td>" +
    								"<td>" + clinic.name + "</td>" +
    								"<td>" + clinic.address + "</td>" +
    								"<td>" + clinic.average_rating + "</td>" +
    								"<td><button name=\"" + clinic.id + "\" id=\"clinic\">" + 'Predefined appointments'+"</button></td>"

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
        var win = window.open('http://localhost:8080/clinicForPatient.html', '_blank');
})

$(document).on('click', '#advancedClinicsSearch', function(e) {
	e.preventDefault();
        var win = window.open('http://localhost:8080/advancedClinicSearch.html', '_blank');
})




function basicFilterSortingClinics(){
	var clinicsSortingType = document.getElementById("clinicsSortingType").value;

    var token = localStorage.getItem("token");
	$.ajax({
		type : 'POST',
		url : "/clinic/basicFilterSortingClinics",
		dataType : "json",
		cache: false,
		contentType : 'application/json',
		data: JSON.stringify({"nameAddressSorting": clinicsSortingType}),
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

							tr.append(
                                "<td>" + clinic.id+"</td>" +
                                "<td>" + clinic.name+"</td>" +
       							"<td>" + clinic.address+"</td>" +
                                "<td>" + clinic.average_rating+"</td>" +
                                "<td><button name=\"" + clinic.id + "\" id=\"clinic\">" + 'Predefined appointments'+"</button></td>"
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


function getPatientsHistory(){
    var token = localStorage.getItem("token");
    var email = localStorage.getItem("email");
	$.ajax({
    		type : 'POST',
    		url : "/appointment/getPatientsHistory",
    		cache: false,
    		dataType: "json",
    		contentType : 'application/json',
    		data: JSON.stringify({"email": email}),
            headers: { "Authorization": "Bearer " + token},
    		success : function(visits) {
    			writeVisitsData(visits);
    		},
    		error : function(errorThrown) {
    			alert(errorThrown);
    		}
    	});
}


function writeVisitsData(visits){
    $("#historyAppointmentsView").find("tr:not(:first)").remove();

    if(visits.length === 0){
        //$("#historyAppointmentsView").hide();
        $("#messageHistoryAppointmentsFound").show();
    }else{
       // $("#historyAppointmentsView").show();
        $("#messageHistoryAppointmentsFound").hide();
        $.each(
             visits,
             function(index,visit){
                var tr = $('<tr></tr>');

                tr.append(
                    "<td>" + visit.date + "</td>" +
                    "<td id=\"clinicName" + index + "\">" + visit.clinic + "</td>" +
                    "<td>" + visit.doctor + "</td>" +
                    "<td>" + visit.visitType + "</td>" +
                    "<td>" + visit.typeOfExam + "</td>" +
                    "<td>" + visit.price + "</td>" +
                    "<td><p id=\"clinicGrade" + index + "\" name=\"clinicGradeName" + visit.clinic + "\">" +  visit.clinicGrade + "</p><button id=\"" + index + "\" name=\"giveGradeClinicButton\">Give grade</button>&nbsp<input id=\"givenClinicGrade" + index + "\" style=\"visibility: hidden\" type=\"number\" min=\"1\" max=\"5\" required></td>" +
                    "<td><p id=\"doctorGrade" + index + "\" name=\"doctorGradeName" + visit.doctorId + "\">" +  visit.doctorGrade + "</p><button id=\"" + index + " " + visit.doctorId  + "\" name=\"giveGradeDoctorButton\">Give grade</button>&nbsp<input id=\"givenDoctorGrade" + index + "\" style=\"visibility: hidden\" type=\"number\" min=\"1\" max=\"5\" required></td>"
                );
                $("#historyAppointmentsView").append(tr);
             }
        )
    }
}


$(document).on('click',"button[name=giveGradeClinicButton]",function(e){
	e.preventDefault();
	var token = localStorage.getItem("token");
	var email = localStorage.getItem("email");
    var index = $(this).attr("id");


	if(document.getElementById(index).innerText  == "Give grade"){
        document.getElementById(index).innerText  = "Save";
        document.getElementById("givenClinicGrade" + index).style.visibility = "visible";
	}else{
        var grade = document.getElementById("givenClinicGrade" + index).value;
        var clinicName = document.getElementById("clinicName" + index).innerText;
        $.ajax({
            type : 'PUT',
            url : "/clinicRating/evaluateClinicByPatient",
            cache: false,
            dataType: "json",
            contentType : 'application/json',
            data: JSON.stringify({"clinicName": clinicName,"email": email,"grade": grade}),
            headers: { "Authorization": "Bearer " + token},
            success : function(message) {
                alert(message.message);
                if(message.success == true){
                    //getPatientsHistory();
                    //document.getElementById("clinicGrade" + index).innerText = (parseInt(grade)).toFixed(1);

                    updateGrades("clinicGradeName" + clinicName,(parseInt(grade)).toFixed(1));
                    document.getElementById(index).innerText  = "Give grade";
                    document.getElementById("givenClinicGrade" + index).style.visibility = "hidden";
                }

            },
            error : function(errorThrown) {
                alert(errorThrown);
            }
        });

    }
})

$(document).on('click',"button[name=giveGradeDoctorButton]",function(e){
	e.preventDefault();
	var token = localStorage.getItem("token");
	var email = localStorage.getItem("email");
    var id = $(this).attr("id");
    var index = id.split(" ")[0];
    var doctorId = id.split(" ")[1];

	if(document.getElementById(id).innerText  == "Give grade"){
        document.getElementById(id).innerText  = "Save";
        document.getElementById("givenDoctorGrade" + index).style.visibility = "visible";
	}else{
        var grade = document.getElementById("givenDoctorGrade" + index).value;
        $.ajax({
            type : 'PUT',
            url : "/doctorRating/evaluateDoctorByPatient",
            cache: false,
            dataType: "json",
            contentType : 'application/json',
            data: JSON.stringify({"doctorId": doctorId,"email": email,"grade": grade}),
            headers: { "Authorization": "Bearer " + token},
            success : function(message) {
                alert(message.message);
                if(message.success == true){
                    //getPatientsHistory();
                    //document.getElementById("doctorGrade" + index).innerText = (parseInt(grade)).toFixed(1);
                    updateGrades("doctorGradeName" + doctorId,(parseInt(grade)).toFixed(1));
                    document.getElementById(id).innerText  = "Give grade";
                    document.getElementById("givenDoctorGrade" + index).style.visibility = "hidden";
                }

            },
            error : function(errorThrown) {
                alert(errorThrown);
            }
        });

    }
})


function updateGrades(name,newGrade){
    var grades = document.getElementsByName(name);
    $.each(
        grades,
        function(index,grade){
            grade.innerText = newGrade;
        }
    )
}


function filterSortingPatientsHistory(){
    var token = localStorage.getItem("token");
    var email = localStorage.getItem("email");
    var visitType = document.getElementById("visitType").value;
    var typeOfExam = document.getElementById("typeOfExams").value;
    var sortingType = document.getElementById("historySortingType").value;

	$.ajax({
    		type : 'POST',
    		url : "/appointment/filterSortingPatientsHistory",
    		cache: false,
    		dataType: "json",
    		contentType : 'application/json',
    		data: JSON.stringify({"email": email,"visitType": visitType,"typeOfExam": typeOfExam, "sortingType": sortingType}),
            headers: { "Authorization": "Bearer " + token},
    		success : function(visits) {
    			writeVisitsData(visits);
    		},
    		error : function(errorThrown) {
    			alert(errorThrown);
    		}
    });
}

$(document).ready(function(){
	$('#visitType').on('change',function() {
		filterSortingPatientsHistory();
	});
});

$(document).ready(function(){
	$('#typeOfExams').on('change',function() {
		filterSortingPatientsHistory();
	});
});

$(document).ready(function(){
	$('#historySortingType').on('change',function() {
		filterSortingPatientsHistory();
	});
});


function getPatientsAppointments(){
	var email = localStorage.getItem("email");
    var token = localStorage.getItem("token");
	$.ajax({
		type : 'POST',
		url : "/appointment/getPatientsAppointments",
		dataType : "json",
		cache: false,
		contentType : 'application/json',
		data: JSON.stringify({"email": email}),
		headers: { "Authorization": "Bearer " + token},
		success : function(appointments) {
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

    var token = localStorage.getItem("token");
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
	$('#examTypes').on('change',function() {
		basicFilterSortingClinics();
	});
});

$(document).ready(function(){
	$('#clinicsSortingType').on('change',function() {
		basicFilterSortingClinics();
	});

});

$(document).ready(function(){
    //getSpecialities();
    getTypeOfExams();
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


    var token = localStorage.getItem("token");
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


