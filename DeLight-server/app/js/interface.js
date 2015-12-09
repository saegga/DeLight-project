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

