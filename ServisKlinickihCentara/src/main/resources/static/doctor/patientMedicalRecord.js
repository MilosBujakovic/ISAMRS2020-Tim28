
var token = localStorage.getItem("token");
if (token == null){
    window.location.href = "./../index.html";
};

$(document).ready(
function()
{	
	displayMedicalRecord();
	getClinicData();
	/*
	setTimeout(function() {
        
	}, 1000);*/
	
	//localStorage.setItem("clinicAdminTabs", document.getElementsByClassName("tabcontent"));
	
});

function displayMedicalRecord()
{
	
}