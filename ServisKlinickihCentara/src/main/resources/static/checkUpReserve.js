
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
    document.getElementById("checkUp").click();
    readDataToTableCheckup();

});


$(document).ready(function(){
    setTimeout(function() {
        getDurationToChoose();
    }, 2000);
});



function readDataToTableCheckup() {
    var token = localStorage.getItem("token");
    var email = localStorage.getItem("email");

    var clinicId = localStorage.getItem("choosenClinicId");
    var doctorId = localStorage.getItem("choosenDoctorId");
    var date = localStorage.getItem("choosenDate");
    var startTime = localStorage.getItem("choosenStartTime");
    var endTime = localStorage.getItem("choosenEndTime");

	$.ajax({
		type : 'GET',
		url : "/doctor/getClinicAndDoctor?clinicId=" + clinicId + "&doctorId=" + doctorId,
		cache: false,
		dataType: "json",
		headers: { "Authorization": "Bearer " + token},
		success : function(clinicDoctor) {
            /*$('#clinic').text(clinicDoctor.clinicName);
            $('#doctor').text(clinicDoctor.doctorNameSurname);
            $('#date').text(date);
            $('#startTime').text(startTime);
            $('#endTime').text(endTime);*/

            document.getElementById('clinic').innerText = clinicDoctor.clinicName;
            document.getElementById('doctor').innerText = clinicDoctor.doctorNameSurname;
            document.getElementById('date').innerText = date;
            document.getElementById('startTime').innerText  = startTime;
            document.getElementById('endTime').innerText = endTime;
            console.log(document.getElementById('startTime').innerText);
            console.log(document.getElementById('endTime').innerText);



		},
	    error : function(errorThrown) {
		    alert(errorThrown);
	    }
    });
}


function getDurationToChoose(){
    var token = localStorage.getItem("token");
    var email = localStorage.getItem("email");
    var startTime = document.getElementById("startTime").innerText;
    var endTime = document.getElementById("endTime").innerText;

	$.ajax({
		type : 'GET',
		contentType : 'application/json',
		cache: false,
		url : "/doctor/getDurationToChoose?startTime=" + startTime + "&endTime=" + endTime,
		dataType: "json",
		headers: { "Authorization": "Bearer " + token},
		success : function() {

		},
		error : function(error) {
			alert(error);
		}
	});
}


$(document).on("submit", "#reserveButton", function(e){
	e.preventDefault();


})
