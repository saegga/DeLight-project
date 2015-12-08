<?php
session_start();
header('Content-type: text/html; charset=utf-8');
?>
<!doctype html>
<html>
<head>

	<title>Расписание тренировок</title>
	<link rel="shortcut icon" href="favicon.png" />
	<link rel="stylesheet" href="libs/bootstrap/bootstrap.min.css" />
	<link rel="stylesheet" href="libs/linea/styles.css" />
	<link rel="stylesheet" type="text/css" href="css/fonts.css" />
	<link rel="stylesheet" type="text/css" href="css/intarface.css" />

</head>
<body>

	<div class="main schedule-container">
		<div class="centered">

		</div>
	</div>
	
	<script src="libs/jquery/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" charset="utf-8" src="libs/jquery.tubular.1.0.1/js/jquery.tubular.1.0.js"></script> 

	<script>
	$(document).ready(function() {

		$(".main").css("height", $(window).height());

	});
	</script>

	<script src="js/autorization.js"></script>

</body>
</html>