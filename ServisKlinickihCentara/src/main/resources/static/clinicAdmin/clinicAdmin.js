
var token = localStorage.getItem("token");
if (token == null){
    window.location.href = "index.html";
};

function updateClinicBasics()
{
	id = document.getElementById("clinicId").value;
	admin = document.getElementById("clinicAdmin").value;
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
	})
}