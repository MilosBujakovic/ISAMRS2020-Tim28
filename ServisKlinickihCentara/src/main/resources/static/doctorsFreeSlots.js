

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
    document.getElementById("doctorTermsButton").click();
    readFreeDoctorsTerms();
});


function readFreeDoctorsTerms() {
    var token = localStorage.getItem("token");
    var email = localStorage.getItem("email");

    var clinicId = localStorage.getItem("clickedClinicForDoctorsTerms");
    var date = localStorage.getItem("choosenDate");

	$.ajax({
		type : 'GET',
		url : "/doctor/getForClinicDoctorsFreeSlots?clinicId=" + clinicId + "&date=" + date,
		cache: false,
		dataType: "json",
		headers: { "Authorization": "Bearer " + token},
		success : function(doctorsSlots) {
		    console.log(doctorsSlots.length);
            $.each(
                doctorsSlots,
                function(index,doctorSlots){
                    var table = $("<table></table>");
                    table.append(
                        "<tr><td>" + 'Name: ' + doctorSlots.name + "</td></tr>" +
                        "<tr><td>" + 'Surname: ' + doctorSlots.surname + "</td></tr>" +
                        "<tr><td>" + 'Rating: ' + doctorSlots.rating + "</td></tr>"
                    )
                    $("#doctorsTerms").append(table);
                    if (doctorSlots.freeSlots.length > 0){
                        var slotTable = $("<table class=\"table table-bordered table-hover\" style=\"width:275px\" bgcolor=\"#d9d9d9\"><thead><th>start time</th><th>end time</th></thead></table>");
                        $.each(
                            doctorSlots.freeSlots,
                            function(index,slot){
                                slotTable.append(
                                    "<tr><td>"  + slot.startTime + "</td><td>" + slot.endTime + "</td></tr>"
                                )
                            }
                        )
                     $("#doctorsTerms").append(slotTable);
                    }
                }
            )

		},
	    error : function(errorThrown) {
		    alert(errorThrown);
	    }
    });
}
