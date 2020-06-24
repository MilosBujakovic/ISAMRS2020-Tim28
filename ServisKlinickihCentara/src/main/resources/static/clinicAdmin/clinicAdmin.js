
var token = localStorage.getItem("token");
if (token == null){
    window.location.href = "index.html";
};

$(document).ready(
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
			console.log(clinic.name);
			document.getElementById("clinicId").value = clinic.id;
			document.getElementById("clinicName").value = clinic.name;
			document.getElementById("clinicAddress").value = clinic.address;
			document.getElementById("clinicDescription").value = clinic.description;
		},
		error : function(errorThrown) {
			alert(errorThrown);
		}
	})
	
	
});

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