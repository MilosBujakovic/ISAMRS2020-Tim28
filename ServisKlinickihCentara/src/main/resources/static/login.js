
$(document).ready(function() {
    localStorage.clear();
});



$(document).on("submit", ".login-form", function(e) {
	e.preventDefault();
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;

	$.ajax({
		type : 'POST',
		url : "/auth/login",
		contentType : 'application/json',
		dataType : "json",
		cache: false,
		data : JSON.stringify({"username" : username, "password" : password}),
		success : function(results) {

		    localStorage.setItem("token", results.access_token);
            localStorage.setItem("expires_in", results.expires_in);
            localStorage.setItem("authority", results.authority);
            localStorage.setItem("email",username);

            if(results.authority == "PATIENT"){
                window.location.href = "patient.html";
            } else if(results.authority == "SYSTEM_ADMIN"){
                window.location.href = "systemAdmin.html";
            } 
            else if(results.authority == "CLINIC_ADMIN")
            {
            	window.location.href = "/clinicAdmin/clinicAdmin.html";
            }


		},
		error : function(error) {
			alert("Wrong email or password");
		}

	});

});
