

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
    document.getElementById("acsButton").click();
    getSpecialities();
});

$(document).on("submit", "#clinicSearchForm", function(e){
	e.preventDefault();
	var date = document.getElementById("dateOfCheckup").value;
	var typeOfSpeciality = document.getElementById("typeOfSpeciality").value;
	var address = document.getElementById("address").value;
	var rating = document.getElementById("rating").value;


    var today = new Date().setHours(0,0,0,0);
    var d = new Date(date).setHours(0,0,0,0);
    var token = localStorage.getItem("token");

    if(d < today){
        alert("Day must be today or in the future!");
    }else {
        localStorage.setItem("choosenDate",date);
        $.ajax({
        		type : 'POST',
        		contentType : 'application/json',
        		dataType : "json",
        		cache: false,
        		url : "/clinic/getClinicsByAdvancedSearch",
        		headers: { "Authorization": "Bearer " + token},
        		data : JSON.stringify({"date":date,"speciality": typeOfSpeciality,"address": address,"rating":rating}),
        		success : function(clinics) {
        		    $("#clinicSpeciality").val("");
                    $("#clinicRating").val("");

                    writeClinicsData(clinics, false);
        		},
        		error : function(errorThrown) {
        			alert(errorThrown);
        		}
        	});
    }

})


function filterExistingAdvancedSearchedItems(){
	var clinicSpeciality = document.getElementById("clinicSpeciality").value;
	var clinicRating = document.getElementById("clinicRating").value;
	var clinicsIds = localStorage.getItem("clinicsIds");

    var token = localStorage.getItem("token");
	$.ajax({
		type : 'POST',
		url : "/clinic/filterExistingAdvancedSearchedItems?ids=" + clinicsIds,
		dataType : "json",
		cache: false,
		contentType : 'application/json',
		data: JSON.stringify({"speciality": clinicSpeciality,"rating": clinicRating}),
		headers: { "Authorization": "Bearer " + token},
		success : function(clinics) {
            writeClinicsData(clinics, true);
		},
		error : function(errorThrown) {
			alert(errorThrown);
		}
	});
}

function writeClinicsData(clinics, isFilteringExisting){
    $("#clinics").find("tr:not(:first)").remove();

    if(clinics.length === 0){
        $("#clinics").hide();
        $("#messageClinicsFound").show();
    }else{
        $("#clinics").show();
        $("#messageClinicsFound").hide();
        clinicsIds = [];

        $.each(clinics, function(index,clinic){
            var tr = $("<tr></tr>");

            if(!isFilteringExisting){
                clinicsIds.push(clinic.id);
            }

            tr.append(
                "<td>" + clinic.id + "</td>" +
                "<td>" + clinic.name + "</td>" +
                "<td>" + clinic.address + "</td>" +
                "<td>" + clinic.speciality + "</td>" +
                "<td>" + clinic.rating + "</td>" +
                "<td>" + clinic.price + "</td>" +
                "<td><button name=\"" + clinic.id + "\" id=\"doctors\" background-color=\"#555555\">" + 'Doctor\'s terms'+"</button></td>"
            );
            $("#clinics").append(tr);
        })
        if(!isFilteringExisting){
            localStorage.setItem("clinicsIds",clinicsIds);
        }
    }
}


$(document).on('click', '#doctors', function(e) {
	e.preventDefault();
		var clinicId = $(this).attr("name");
        localStorage.setItem("clickedClinicForDoctorsTerms", clinicId);
        var win = window.open('http://localhost:8080/doctorsFreeSlots.html', '_blank');
})


$(document).ready(function(){
	$('#clinicSpeciality').on('change',function() {
		filterExistingAdvancedSearchedItems();
	});
});

$(document).ready(function(){
	$('#clinicRating').on('change',function() {
		filterExistingAdvancedSearchedItems();
	});
});

function getSpecialities(){
    var token = localStorage.getItem("token");
	$.ajax({
    		type : 'GET',
    		url : "/clinic/getSpecialities",
    		cache: false,
    		dataType: "json",
            headers: { "Authorization": "Bearer " + token},
    		success : function(specialities) {
    			$("#typeOfSpeciality").find('optionl:gt(0)').remove();
    			$.each(
    					specialities,
    					function(index,speciality){
    						$("#typeOfSpeciality").append('<option value=\"' + speciality + '\">' + speciality + '</option>');
    						$("#clinicSpeciality").append('<option value=\"' + speciality + '\">' + speciality + '</option>');
    					}
    			)
    		},
    		error : function(errorThrown) {
    			alert(errorThrown);
    		}
    	});
}