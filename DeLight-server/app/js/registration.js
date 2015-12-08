$(document).ready(function() {

	$('#register').click(function(){

		$('#register').prop('disabled', true);
		$('#register').addClass("blocked");

		var login = $('#username').val();
		var password = $('#password').val();
		var passwordConfirm = $('#password_confirm').val();

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