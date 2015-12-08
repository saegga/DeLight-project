$(document).ready(function() {

	$('#login').click(function(){

		$('#login').prop('disabled', true);
		$('#login').addClass("blocked");

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
				$('#login').prop('disabled', false);
				$('#login').removeClass("blocked");
				if (data == "1"){
					location.href = "schedule";
				}
			}
		})
	})
});