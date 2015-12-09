$(document).ready(function() {

	$('#register').click(function(){

		toggleButtonStatus('#register');

		var login = $('input[name=username]').val();
		var password = $('input[name=password]').val();
		var passwordConfirm = $('input[name=password_confirm]').val();

		if (checkData() != false){
			$.ajax({
				type: "POST",
				url: "authorization",
				data: {
					login: login, 
					password: password,
					passwordConfirm: passwordConfirm,
					from: "register"
				},
				success: function (data) {
					toggleButtonStatus('#register');
					if (data == "1"){
						toastr.success("Вы зарегистрированы");
						toastr.warning("Дождитель подтверждения регистрации администратором");
						toggleButtonStatus('#register');
					}
					else {
						alert(data);
						toastr.error("Пользователь с таким именем существует");
						toggleButtonStatus('#register');
					}
				}
			})
		}
	})
});

function checkData(){
	var login = $('input[name=username]').val();
	var password = $('input[name=password]').val();
	var passwordConfirm = $('input[name=password_confirm]').val();

	if (!login || !password){
		toastr.error('Все поля обязательны для заполнения');
		toggleButtonStatus('#register');
		return false;
	}
	else if (passwordConfirm != password){
		toastr.error('Пароли не совпадают');
		toggleButtonStatus('#register');
		return false;
	}
	else if (hasWhiteSpace(login)){
		toastr.error("Неверный формат имени пользователя");
		toggleButtonStatus('#register');
		return false;
	}
	else return true;
}