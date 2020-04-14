


$(document).on("submit", ".register-form", function(e) {
	e.preventDefault();
	var email = document.getElementById("email").value;
	var password1 = document.getElementById("password1").value;
	var password2 = document.getElementById("password2").value;
	var name = document.getElementById("name").value;
	var surname = document.getElementById("surname").value;
	var address = document.getElementById("address").value;
	var city = document.getElementById("city").value;
	var country = document.getElementById("country").value;
	var phone = document.getElementById("phone").value;
	var insurance = document.getElementById("insurance").value;


	if(email === "" || password1 === "" || password2 === "" || name === "" || surname === "" || address === "" || city === "" || country === "" || phone === "" || insurance === ""){
		alert("You must fill all fields!");
		return;
	}

	if(password1 !== password2){
		alert("Passwords don't match!");
		return;
	}


	$.ajax({
		type : 'POST',
		url : "/user/register",
		contentType : 'application/json',
		dataType : "json",
		cache: false,
		data : JSON.stringify({"email": email,"password1": password1, "password2": password2, "name":name,
		"surname":surname, "address": address, "city": city, "country": country, "phone_number": phone,
		"insurance_number": insurance
		}),
		success : function(m) {
            alert(m.message);
            if(m.success === true){
                window.location.href = "index.html"
            }
		},
		error : function(errorThrown) {
			alert(errorThrown);
		}

	});

});