

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

		    doctorsIds = [];
            $.each(
                doctorsSlots,
                function(index,doctorSlots){
                    doctorsIds.push(doctorSlots.doctorId);
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

            localStorage.setItem("doctorsIds",doctorsIds);

		},
	    error : function(errorThrown) {
		    alert(errorThrown);
	    }
    });
}

function filterExistingDoctors() {
    var token = localStorage.getItem("token");

    var name = document.getElementById("nameDoctorDTO").value;
    var surname = document.getElementById("surnameDoctorDTO").value;
    var rating = document.getElementById("ratingDoctorDTO").value;
    var date = localStorage.getItem("choosenDate");
    var doctorsIds = localStorage.getItem("doctorsIds");

	$.ajax({
		type : 'POST',
		url : "/doctor/filterExistingDoctors?ids=" + doctorsIds,
		cache: false,
		dataType: "json",
		contentType : 'application/json',
		data: JSON.stringify({"name": name,"surname": surname,"rating":rating,"date":date}),
		headers: { "Authorization": "Bearer " + token},
		success : function(doctorsSlots) {
		    removeChildsFromDoctorTermsDiv();
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

$(document).ready(function(){
	$('#nameDoctorDTO').on('keyup',function() {
		filterExistingDoctors();
	});
});

$(document).ready(function(){
	$('#surnameDoctorDTO').on('keyup',function() {
		filterExistingDoctors();
	});
});

$(document).ready(function(){
	$('#ratingDoctorDTO').on('change',function() {
		filterExistingDoctors();
	});
});


function removeChildsFromDoctorTermsDiv(){
    const parent = document.getElementById("doctorsTerms");
    while (parent.firstChild) {
        parent.firstChild.remove();
    }
}

