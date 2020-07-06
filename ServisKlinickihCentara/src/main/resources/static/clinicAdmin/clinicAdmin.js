
var token = localStorage.getItem("token");
if (token == null){
    window.location.href = "index.html";
};

$(document).ready(
function()
{
	getClinicData();
	setTimeout(function() {
        
        getTypeOfExams();
	}, 500);
	
	localStorage.setItem("clinicAdminTabs", document.getElementsByClassName("tabcontent"));
	
});
function getClinicData()
{

	$.ajax({
		method: "POST",
		url: "clinicEdit/getClinicData",
		dataType: "json", //Povratni tip
		cache: false,
		contentType: 'application/json',
		data: JSON.stringify(localStorage.getItem("email")),
		headers: { "Authorization": "Bearer " + token},
		success : function(clinic) {
			document.getElementById("clinicId").value = clinic.id;
			localStorage.setItem("clinicId", clinic.id);
			document.getElementById("clinicName").value = clinic.name;
			document.getElementById("clinicAddress").value = clinic.address;
			document.getElementById("clinicDescription").value = clinic.description;
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

$(document).on("click", "#editClinicSubmitButton", function updateClinicBasics()
{
	
	id = document.getElementById("clinicId").value;
	admin = localStorage.getItem("email");
	name = document.getElementById("clinicName").value;
	address = document.getElementById("clinicAddress").value;
	description = document.getElementById("clinicDescription").value;
	
	$.ajax({
		method: "PUT",
		url: "clinicEdit/updateBasics",
		dataType : "json",
		cache: false,
		contentType : 'application/json',
		data: JSON.stringify({"id": id, "admin": admin, "name": name, "address": address,"description": description}),
		headers: { "Authorization": "Bearer " + token},
		success : function(retVal) {
			alert(retVal.message);
		},
		error : function(errorThrown) {
			alert(errorThrown);
		}
	})
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
	
	
	
	
	