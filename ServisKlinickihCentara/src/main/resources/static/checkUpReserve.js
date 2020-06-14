
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

function readDataToTableCheckup() {
    var token = localStorage.getItem("token");
    var email = localStorage.getItem("email");

    var clinicId = localStorage.getItem("choosenClinicId");
    var doctorId = localStorage.getItem("choosenDoctorId");
    var date = localStorage.getItem("choosenDate");
    var startTime = localStorage.getItem("choosenStartTime");
    var endTime = localStorage.getItem("choosenEndTime");
    var typeOfExam = localStorage.getItem("choosenExam");

	$.ajax({
		type : 'GET',
		url : "/doctor/getClinicAndDoctor?clinicId=" + clinicId + "&doctorId=" + doctorId,
		cache: false,
		dataType: "json",
		headers: { "Authorization": "Bearer " + token},
		success : function(clinicDoctor) {

            document.getElementById('clinic').innerText = clinicDoctor.clinicName;
            document.getElementById('doctor').innerText = clinicDoctor.doctorNameSurname;
            document.getElementById('date').innerText = date;
            document.getElementById('startTime').innerText  = startTime;
            document.getElementById('endTime').innerText = endTime;
            document.getElementById('typeOfExam').innerText = typeOfExam;

		},
	    error : function(errorThrown) {
		    alert(errorThrown);
	    }
    });
}


$(document).on("click", "#reserveButton", function(e){
	e.preventDefault();


	var token = localStorage.getItem("token");
    var email = localStorage.getItem("email");

    var clinicId = localStorage.getItem("choosenClinicId");
    var doctorId = localStorage.getItem("choosenDoctorId");

    var date = localStorage.getItem("choosenDate");
    var startTime = localStorage.getItem("choosenStartTime");
    var typeOfExam = localStorage.getItem("choosenExam");


    $.ajax({
            type : 'POST',
            url : "/appointment/customAppointmentReservation",
            cache: false,
            dataType: "json",
            contentType : 'application/json',
            data: JSON.stringify({"email": email, "clinicId": clinicId,"doctorId": doctorId,
            "date":date,"typeOfExam":typeOfExam,"startTime": startTime}),
            headers: { "Authorization": "Bearer " + token},
            success : function(message) {
                alert(message.message);
                $("#reserveButton").html('Reservation confirmed');
                $('#reserveButton').prop("disabled",true);
         	},
         	error : function(errorThrown) {
         		alert(errorThrown);
         	}
        });
})
