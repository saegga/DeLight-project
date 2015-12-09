$(document).ready(function() {

	$('#login').click(function(){

		toggleButtonStatus(#login);

		var login = $('#username').val();
		var password = $('#password').val();

		$.ajax({
			type: "POST",
			url: "authorization",
			data: {
				login: login, 
				password: password, 
				from: "login"
			},
			success: function (data) {
				toggleButtonStatus(#login);
				if (data == "1"){
					location.href = "schedule";
				}
			}
		})
	})
});