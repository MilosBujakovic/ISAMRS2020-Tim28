


var token = localStorage.getItem("token");
var authority = localStorage.getItem("authority");
if (token == null){
    window.location.href = "index.html";
}else{
    if (authority != "PATIENT"){
        window.location.href = "index.html";
    }
}


getLoggedPatient();

function getLoggedPatient() {
	$.ajax({
		type : 'GET',
		url : "/auth/getLoggedPatient",
		cache: false,
		dataType: "json",
		headers: { "Authorization": "Bearer " + token},
		success : function(patient) {
			if (typeof (patient) == 'undefined') {
				window.location.href = "index.html";
			} else {
			    localStorage.setItem("username",patient.email);

				$(document).ready(function(){

					$("#name").attr('value',patient.name);
					$("#surname").attr('value',patient.surname);
					$("#name").attr('value',patient.name);
                    $("#address").attr('value',patient.address);
                    $("#city").attr('value',patient.city);
                    $("#country").attr('value',patient.country);
					$("#phone").attr('value',patient.phone);
				});
			}
		},
	    error : function(errorThrown) {
		    alert(errorThrown);
	    }
    });
}





$(document).on("submit", "#updatePatient", function(e){
	e.preventDefault();
	var name = document.getElementById("name").value;
	var surname = document.getElementById("surname").value;
	var address = document.getElementById("address").value;
	var city = document.getElementById("city").value;
    var country = document.getElementById("country").value;
    var phone = document.getElementById("phone").value;


	if(name === "" || surname === "" || address === "" || city === "" || country === "" || phone === ""){
		alert("You must fill all fields!");
		return;
	}

    var token = localStorage.getItem("token");

	$.ajax({
		type : 'PUT',
		contentType : 'application/json',
		dataType : "json",
		cache: false,
		url : "/user/updatePatient",
		headers: { "Authorization": "Bearer " + token},
		data : JSON.stringify({"email":localStorage.getItem("username"),"name": name,"surname": surname,"address":address,"city":city,
			"country":country,"phone": phone
		}),
		success : function(retVal) {
			alert(retVal.message);
		},
		error : function(errorThrown) {
			alert(errorThrown);
		}
	});

})



$(document).on("submit", "#changePassword", function(e){
	e.preventDefault();
	var oldPassword = document.getElementById("oldPassword").value;
	var newPassword = document.getElementById("newPassword").value;


    if (oldPassword === ""){
        alert("You must enter old password!");
        return;
    }

    if (newPassword === ""){
         alert("You didn't enter new password");
         return;
    }




	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		cache: false,
		url : "/auth/change-password",
		dataType: "json",
		headers: { "Authorization": "Bearer " + token},
		data : JSON.stringify({"oldPassword": oldPassword, "newPassword": newPassword}),
		success : function(userTokenState) {
            //refreshToken();
            localStorage.setItem("token", userTokenState.access_token);
            localStorage.setItem("expires_in", userTokenState.expires_in);
		    alert("Your password was successfully changed :)")
		},
		error : function(error) {
			alert("Something is wrong with changing password!");
		}
	});

})



/*function refreshToken(){
    $.ajax({
    		type : 'POST',
    		contentType : 'application/json',
    		cache: false,
    		url : "/auth/refresh",
    		dataType: "json",
    		headers: { "Authorization": "Bearer " + token},
    		success : function(userTokenState) {
    		    //localStorage.clear();
                localStorage.setItem("token", userTokenState.access_token);
                localStorage.setItem("expires_in", userTokenState.expires_in);
    		},
    		error : function(error) {
    			alert("Something is wrong with refreshing token!");
    		}
    	});

}*/

$(document).on("change", "input[id='passwordCheckbox']", function () {
     if(this.checked) {
         document.getElementById("oldPasswordId").style.display = "block";
         document.getElementById("newPasswordId").style.display = "block";
         document.getElementById("changeButton").style.display = "block";

     }else{
         document.getElementById("oldPasswordId").style.display = "none";
         document.getElementById("newPasswordId").style.display = "none";
         document.getElementById("changeButton").style.display = "none";
     }
});





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


