

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
        alert("Day must be today or in the future");
    }else {
        $.ajax({
        		type : 'POST',
        		contentType : 'application/json',
        		dataType : "json",
        		cache: false,
        		url : "/clinic/getClinicsByAdvancedSearch",
        		headers: { "Authorization": "Bearer " + token},
        		data : JSON.stringify({"date":date,"speciality": typeOfSpeciality,"address": address,"rating":rating}),
        		success : function(clinics) {
                    $("#clinics").find("tr:not(:first)").remove();
                    if(clinics.length === 0){
                        $("#clinics").hide();
                    	$("#messageClinicsFound").show();
                    }else{
                    	$("#clinics").show();
                        $("#messageClinicsFound").hide();

                    	$.each(clinics, function(index,clinic){
                    	    var tr = $("<tr></tr>");

                    		tr.append(
                    		    "<td>" + clinic.id+"</td>" +
                				"<td>" + clinic.name+"</td>" +
                    			"<td>" + clinic.address+"</td>" +
                    			"<td>" + clinic.rating+"</td>" +
                   	    		"<td>" + clinic.price+"</td>" +
                    		    "<td><button name=\"" + clinic.id + "\" id=\"doctors\" background-color=\"#555555\">" + 'Doctors'+"</button></td>"
                    		);
                    		$("#clinics").append(tr);
                    	})
                    }

        		},
        		error : function(errorThrown) {
        			alert(errorThrown);
        		}
        	});
    }



})

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
    					}
    			)
    		},
    		error : function(errorThrown) {
    			alert(errorThrown);
    		}
    	});
}