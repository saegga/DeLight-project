$(document).ready(function() {

	$(".main_head").css("height", $(window).height());

	$(".animated").animated("fadeIn", "fadeOut");

	$(".top_menu li a").mPageScroll2id();

	(function(s) {
	    var n;
	    s(".tabs").on("click", "li:not(.active)", function() {
	        n = s(this).parents(".tabs_block"), s(this).dmtabs(n)
	    }), s.fn.dmtabs = function(n) {
	        s(this).addClass("active").siblings().removeClass("active"), n.find(".box").eq(s(this).index()).show(1, function() {
	            s(this).addClass("open_tab")
	        }).siblings(".box").hide(1, function() {
	            s(this).removeClass("open_tab")
	        })
	    }
	})(jQuery);
});

$(window).load(function() {
	$(".loader_inner").fadeOut();
	$(".loader").delay(400).fadeOut("slow");
});