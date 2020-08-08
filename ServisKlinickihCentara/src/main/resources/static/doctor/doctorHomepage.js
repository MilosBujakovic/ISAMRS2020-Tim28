
var token = localStorage.getItem("token");
if (token == null){
    window.location.href = "./../index.html";
};

$(document).ready(
function()
{	
	getDoctorData();
	getClinicData();
	setTimeout(function() {
        
        filterPatients();
	}, 2000);
	
	//localStorage.setItem("clinicAdminTabs", document.getElementsByClassName("tabcontent"));
	
});

function filterPatients()
{
	var clinicId = localStorage.getItem("clinicId");
	var name = document.getElementById("patientName").value;
	var surname = document.getElementById("patientSurname").value;
	var insuranceNumber = document.getElementById("insuranceNumber").value;
	var city = document.getElementById("patientCity").value;
	//$("#filterPatientsTable").find("tr:not(:first)").remove();
	
	console.log(name);
	console.log(surname);
	console.log(insuranceNumber);
	console.log(city);
	
	$.ajax({
		type : 'GET',
		url : "/doctor/filterClinicPatients?clinicId=" + clinicId + "&name=" + name + "&surname=" + surname + 
		"&insuranceNumber=" + insuranceNumber + "&city=" + city,
		cache: false,
		dataType: "json",
		headers: { "Authorization": "Bearer " + token},
		success : function(patientSlots) {
		var table = $("<table></table>");
		table.append("<tr><th>Name:</th><th>Surname:</th><th>Patient ID:</th><th>City:</th><th>More info:</th></tr>");
	    patientIds = [];
	    removeChildsFromPatientsTable();
        $.each(
            patientSlots,
            function(index,patientSlots){
                patientIds.push(patientSlots.id);
                table.append(
                    "<tr><td>" + patientSlots.name + "</td>>" +
                    "<td>" + patientSlots.surname + "</td>" +
                    "<td>" + patientSlots.insuranceNumber + "</td>" +
                    "<td>" + patientSlots.city + "</td>" +
                    "<td><button name=\"patientMedicalRecord\" value=" + patientSlots.id + ">Open medical record</button></td></tr>"
                )
                $("#filterPatientsTable").append(table);
                
                

                if(patientSlots.length === 0){
                    //$("#historyAppointmentsView").hide();
                    $("#messageNoPatients").show();
                }else{
                   // $("#historyAppointmentsView").show();
                    $("#messageNoPatients").hide(); 
                }
            });
		}
	});

}


function getClinicData()
{

	$.ajax({
		method: "POST",
		url: "../clinicAdmin/clinicEdit/getClinicDataDoctor",
		dataType: "json", //Povratni tip
		cache: false,
		contentType: 'application/json',
		data: JSON.stringify(localStorage.getItem("email")),
		headers: { "Authorization": "Bearer " + token},
		success : function(clinic) {
			document.getElementById("clinicId").value = clinic.id;
			localStorage.setItem("clinicId", clinic.id);
		},
		error : function(errorThrown) {
			alert(errorThrown);
		}
	})
}

function getDoctorData()
{

	$.ajax({
		method: "POST",
		url: "/doctor/getDoctorData",
		dataType: "json", //Povratni tip
		cache: false,
		contentType: 'application/json',
		data: JSON.stringify(localStorage.getItem("email")),
		headers: { "Authorization": "Bearer " + token},
		success : function(doctor) {
			
			localStorage.setItem("doctorId", doctor.id);
			//TODO: edit profile here
		},
		error : function(errorThrown) {
			alert(errorThrown);
		}
	})
}

function getTypeOfExams(){
    var token = localStorage.getItem("token");
    var clinicId = localStorage.getItem("clinicId");
	$.ajax({
    		type : 'GET',
    		url : "../typeOfExam/getTypeOfExamsForClinic?clinicId=" + clinicId,
    		cache: false,
    		dataType: "json",
           headers: { "Authorization": "Bearer " + token},
    		success : function(types) {
    			$("#examTypes").find('optionl:gt(0)').remove();
    			$.each(
    					types,
    					function(index,type){
    						$("#examTypes").append('<option value=\"' + type + '\">' + type + '</option>');
    						//$("#typeOfExams").append('<option value=\"' + type + '\">' + type + '</option>');
    					}
    			)
    		},
    		error : function(errorThrown) {
    			alert(errorThrown);
    		}
    	});
}

$(document).ready(function(){
	$('#patientName').on('keyup',function() {
		filterPatients();
	});
});

$(document).ready(function(){
	$('#patientSurname').on('keyup',function() {
		filterPatients();
	});
});

$(document).ready(function(){
	$('#insuranceNumber').on('keyup',function() {
		filterPatients();
	});
});

$(document).ready(function(){
	$('#patientCity').on('keyup',function() {
		filterPatients();
	});
});

$(document).on('click',"button[name=patientMedicalRecord]",function(e)
		{

			var patientId = $(this).attr("value");
			
			$.ajax({
				method: "GET",
				url: "patient/getMedicalRecords?patientId=" + patientId,
				dataType : "json",
				cache: false,
				headers: { "Authorization": "Bearer " + token},
				success : function(patientMedicalRecord) {
					localStorage.setItem("patientRecord", patientMedicalRecord);
					window.location.replace('patientMedicalRecord.html');
				},
	         	error : function(errorThrown) {
	         		alert(errorThrown);
	         	}
			});
			
					
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
				window.location.href = "../index.html";
			},
			error : function(errorThrown) {
				alert(errorThrown);
			}
		});
	}

})

$(document).on('submit', "#makePredefinedAppointment", function(e)
	{
		e.preventDefault();
		if(document.getElementById("examTypes").value=="")
		{
			alert("Please choose a type of exam!");
				
		}
		else
		{
			//console.log(document.getElementById("dateOfCheckup").value);
			localStorage.setItem("dateOfCheckup", document.getElementById("dateOfCheckup").value);
			localStorage.setItem("typeOfExam", document.getElementById("examTypes").value);
			//window.open("./../index.html", "currentWindow", "");
			//window.location.replace("./../index.html");
			window.location.replace("doctorsFreeTerms.html");
		}

	})
	
	function removeChildsFromPatientsTable(){
    const parent = document.getElementById("filterPatientsTable");
    while (parent.firstChild) {
        parent.firstChild.remove();
    }
}
	
	