$(document).ready(function() {

	$('#login').click(function(){

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
				alert(data);
			}
		})
	})
});