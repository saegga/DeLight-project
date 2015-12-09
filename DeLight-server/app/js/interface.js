$(document).ready(function() {

	$(".fullscreen").css("height", $(window).height());

});

function toggleButtonStatus(buttonSelector){
	button = $(buttonSelector);
	if (button.hasClass("blocked")){
		button.prop('disabled', false);
		button.removeClass("blocked");
	}
	else {
		button.prop('disabled', true);
		button.addClass("blocked");
	}
}

function hasWhiteSpace(s) {
	return s.indexOf(' ') > 0;
}

function checkData(jqElement){

	var value = jqElement.val();

	if (jqElement.hasClass("js-check-login")){
		if (!value){
			toastr.error('Все поля обязательны для заполнения');
			return false;
		} 
		else if (hasWhiteSpace(value)){
			toastr.error("Неверный формат имени пользователя. Имя пользователя должно состоять только из " 
				+ "символов латинского алфавита, цифр, знака подчеркивания и иметь длинну от 4 до 20 символов");
			return false;
		}
		else return true;
	}

	if (jqElement.hasClass("js-check-password")){
		if (!value){
			toastr.error('Все поля обязательны для заполнения');
			return false;
		}
		else return true;
	}
}