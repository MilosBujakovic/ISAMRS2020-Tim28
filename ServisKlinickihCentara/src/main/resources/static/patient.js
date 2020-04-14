


var token = localStorage.getItem("token");
if (token == null){
    window.location.href = "index.html";
};


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