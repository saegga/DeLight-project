$(document).ready(function() {

	$('#login').click(function(){

		toggleButtonStatus("#login");

		var login = $('#username');
		var password = $('#password');

		if (checkData(login) && checkData(password)){
			$.ajax({
				type: "POST",
				url: "authorization",
				data: {
					login: login.val(), 
					password: password.val(), 
					from: "login"
				},
				success: function (data) {
					toggleButtonStatus("#login");
					if (data == "1"){
						location.href = "schedule";
					}
					else {
						toastr.error("Неверный логин или пароль");
					}
				}
			})
		}
		else {
			toggleButtonStatus("#login");
		}
	})
});