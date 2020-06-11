

var token = localStorage.getItem("token");
if (token == null){
    window.location.href = "index.html";
};


$(document).ready(function(){
    document.getElementById("requestsButton").click();
    readUnregisteredPatients();
});



function readUnregisteredPatients() {
	$.ajax({
		type : 'GET',
		url : "/user/getUnregisteredPatients",
		dataType : "json",
		cache: false,
		headers: { "Authorization": "Bearer " + token},
		success : function(patients) {

		    if(patients.length === 0){
		        $("#messageRequestsExists").show();
		        $("#registrations").hide();
		    }else{
		         $("#messageRequestsExists").hide();
                 $("#registrations").show();
		        $("#registrations").find("tr:not(:first)").remove();
                			$.each(
                					patients,
                					function(index,patient){
                						var tr = $('<tr></tr>');
                						var accept = "Accept";
                						var reject = "Reject";

                						tr.append(
                								"<td>" + patient.email+"</td>" +
                								"<td>" + patient.name+"</td>" +
                								"<td>" + patient.surname+"</td>" +
                								"<td>" + patient.address+"</td>" +
                								"<td>" + patient.city+"</td>" +
                								"<td>" + patient.country+"</td>" +
                								"<td>" + patient.phone+"</td>" +
                								"<td>" + patient.insurance_number+"</td>" +
                								"<td><button name=\"" + patient.email + "\" id=\"accept\"  background-color=\"#555555\">" + accept+"</button></td>" +
                								"<td><button name=\"reject " + patient.email + "\" id=\"reject\"  background-color=\"#555555\">" + reject+"</button></td>"

                						);
                						$("#registrations").append(tr);
                					}
                			)
		    }
		},
		error : function(error) {
			alert(error);
		}
	});
}


$(document).on('click', '#accept', function(e) {
	e.preventDefault();
		if (confirm('Are you sure that you want to accept registration of this patient?')) {
		    var email = $(this).attr("name");
		    var form = this;
		    console.log(email);
            		$.ajax({
            			type : 'PUT',
            			url : "/user/acceptPatient/" + email,
            			headers: { "Authorization": "Bearer " + token},
            			success : function() {
            		        //$('[name=\"' + email + '\"]').prop("disabled",true);

                            alert("Activation was sent to patient's mail.");
                            $(form).replaceWith("<p>ACCEPTED</p>");
                            document.getElementsByName("reject " + email)[0].replaceWith("");

            			},
            			error : function(errorThrown) {
            				alert(errorThrown);
            			}
            		});
		}
})


$(document).on('click', '#reject', function(e) {
	e.preventDefault();
		if (confirm('Are you sure that you want to reject registration of this patient?')) {
		    var email = $(this).attr("name").split(" ")[1];

		    if ($.trim($('#reason').val()).length == 0)
            {
                alert("You must write reason for rejecting!");
                return;
            }

		    var reason = $("#reason").val();

		    console.log(email);
            		$.ajax({
            			type : 'PUT',
            			url : "/user/rejectPatient/" + email + "/" + reason,
            			headers: { "Authorization": "Bearer " + token},
            			success : function() {
            			    alert("Reason for rejecting was sent to patient's mail.")
                            readUnregisteredPatients();
            			},
            			error : function(errorThrown) {
            				alert(errorThrown);
            			}
            		});
		}
})

$(document).on('click',"#logout",function(e){
	e.preventDefault();
	if (confirm('Are you sure that you want to log out?')) {

	    localStorage.clear();


		$.ajax({
			type : 'GET',
			url : "/auth/logout",
			dataType: "json",
			headers: { "Authorization": "Bearer " + token},
			success : function(message) {
			    alert(message.message);
				window.location.href = "index.html";
			},
			error : function(errorThrown) {
				alert(errorThrown);
			}
		});
	}

})
