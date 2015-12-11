$(document).ready(function() {

	$('#register').click(function(){

		toggleButtonStatus('#register');

		var login = $('input[name=username]');
		var password = $('input[name=password]');
		var passwordConfirm = $('input[name=password_confirm]');

		if (checkData(login) && checkData(password)){
			if (password.val() == passwordConfirm.val()){
				$.ajax({
					type: "POST",
					url: "authorization",
					data: {
						login: login.val(), 
						password: password.val(),
						passwordConfirm: passwordConfirm.val(),
						from: "register"
					},
					success: function (data) {
						if (data == "1"){
							toastr.warning("Дождитель подтверждения регистрации администратором");
							toggleButtonStatus('#register');
						}
						else {
							toastr.error("Пользователь с таким именем существует");
							toggleButtonStatus('#register');
						}
					}
				})
			}
			else {
				toastr.error("Пароли не совпадают");
				toggleButtonStatus("#register");
			}
		}
		else {
			toggleButtonStatus("#register");
		}
	})
});