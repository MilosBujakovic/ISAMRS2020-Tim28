

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
    //getTypeOfExamsWithoutOperations();
    readFreeDoctorsTerms();
    /*setTimeout(function() {
                var choosenExam = localStorage.getItem("choosenExam");
                $("#typeOfExam option[value='" + choosenExam + "']").prop("selected",true);

    }, 500);*/

});




function readFreeDoctorsTerms() {
    var token = localStorage.getItem("token");
    var email = localStorage.getItem("email");

    var clinicId = localStorage.getItem("clickedClinicForDoctorsTerms");
    var date = localStorage.getItem("choosenDate");

    $('#dateOfExamDTO').val(date);


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
                                    "<tr><td>"  + slot.startTime + "</td><td>" + slot.endTime +
                                     "</td><td><button name=\"choosenTerm\" id=\""
                                      + clinicId + " " +  doctorSlots.doctorId + " " + date + " " +
                                      slot.startTime + " " + slot.endTime + "\">Reserve</button></td></tr>"
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

    var clinicId = localStorage.getItem("clickedClinicForDoctorsTerms");

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
                                    "<tr><td>"  + slot.startTime + "</td><td>" + slot.endTime +
                                    "</td><td><button name=\"choosenTerm\" id=\""
                                    + clinicId + " " +  doctorSlots.doctorId + " " + date + " " +
                                    slot.startTime + " " + slot.endTime + "\">Reserve</button></td></tr>"

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



$(document).on('click',"button[name=choosenTerm]",function(e){
	e.preventDefault();
	var token = localStorage.getItem("token");
	var email = localStorage.getItem("email");
    var id = $(this).attr("id");

    var array = id.split(" ");

    var clinicId = array[0];
    var doctorId = array[1];
    var date = array[2];
    var startTime = array[3];
    var endTime = array[4];

    localStorage.setItem("choosenClinicId",clinicId);
    localStorage.setItem("choosenDoctorId",doctorId);
    localStorage.setItem("choosenDate", date);
    localStorage.setItem("choosenStartTime",startTime);
    localStorage.setItem("choosenEndTime",endTime);
    var win = window.open('http://localhost:8080/checkUpReserve.html', '_blank');

})


function removeChildsFromDoctorTermsDiv(){
    const parent = document.getElementById("doctorsTerms");
    while (parent.firstChild) {
        parent.firstChild.remove();
    }
}


function getTypeOfExamsWithoutOperations(){
     var token = localStorage.getItem("token");
 	$.ajax({
     		type : 'GET',
     		url : "/typeOfExam/getTypeOfExamsWithoutOperations",
     		cache: false,
     		dataType: "json",
            headers: { "Authorization": "Bearer " + token},
     		success : function(types) {
     			$("#typeOfExam").find('option:gt(0)').remove();
     			var options = $('#typeOfExam').attr('options');

     			$.each(
     					types,
     					function(index,type){
     						$("#typeOfExam").append('<option value=\"' + type + '\" data-id=\"' + type + '\">' + type + '</option>');
     					}
     			)
     		},
     		error : function(errorThrown) {
     			alert(errorThrown);
     		}
     	});
 }
