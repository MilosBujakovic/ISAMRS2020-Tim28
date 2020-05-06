

var token = localStorage.getItem("token");
var authority = localStorage.getItem("authority");
if (token == null){
    window.location.href = "index.html";
}else{
    if (authority != "PATIENT"){
        window.location.href = "index.html";
    }
}


getPredefinedAppointments();


function getPredefinedAppointments(){
    var clinicId = localStorage.getItem("clickedClinicId");
    var token = localStorage.getItem("token");
    $.ajax({
            type : 'GET',
            dataType : "json",
            cache: false,
            url : "/appointment/getPredefinedAppointments/" + clinicId,
            headers: { "Authorization": "Bearer " + token},
            success : function(appointments) {
                $("#predefinedAppointmentsTable").find("tr:not(:first)").remove();
                if(appointments.length == 0){
                    $("#predefinedAppointmentsTable").hide();
                    $("#messagePredefineAppointementsFound").show();
                }else{
                    $("#predefinedAppointmentsTable").show();
                    $("#messagePredefineAppointementsFound").hide();


                    $.each(appointments, function(index,a){
                        var tr = $("<tr></tr>");

                    	tr.append(
                    			"<td>" + a.id+"</td>" +
                    			"<td>" + a.timestamp+"</td>" +
                    		    "<td>" + a.room+"</td>" +
                    			"<td>" + a.doctor+"</td>" +
                    			"<td>" + a.typeSpeciality+"</td>" +
                    			"<td>" + a.price+"</td>" +
                    			"<td><button name=\"" + a.id + "\" id=\"appointmentReserve\" background-color=\"#008CBA\">" + 'Reserve'+"</button></td>"
                    	);
                    $("#predefinedAppointmentsTable").append(tr);
                })
                }
            },
            error : function(errorThrown) {
                alert(errorThrown);
            }
        });

}

$(document).on('click', '#appointmentReserve', function(e) {
	e.preventDefault();
	    var email = localStorage.getItem("email")
		var appointmentId = $(this).attr("name");
        console.log(appointmentId);
        var token = localStorage.getItem("token");
        $.ajax({
        		type : 'POST',
        		url : "/appointment/quickAppointmentReservation",
        		dataType : "json",
        		cache: false,
        		contentType : 'application/json',
        		data: JSON.stringify({"email": email,"appointmentId": appointmentId}),
        		headers: { "Authorization": "Bearer " + token},
        		success : function(message) {
                    if(message.success == true){
                        getPredefinedAppointments();
                    }
                    alert(message.message);

        		},
        		error : function(errorThrown) {
        			alert(errorThrown);
        		}
        	});




})

