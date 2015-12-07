<?php
header('Content-type: text/html; charset=utf-8');
?>
<!doctype html>
<html>
<head>

	<title>DeLight - Огненное шоу в благовещенске</title>
	<link rel="shortcut icon" href="favicon.png" />
	<link rel="stylesheet" href="libs/bootstrap/bootstrap.min.css" />
	<link rel="stylesheet" href="libs/linea/styles.css" />
	<link rel="stylesheet" type="text/css" href="css/fonts.css" />
	<link rel="stylesheet" type="text/css" href="css/intarface.css" />

</head>
<body>

	<div class="main">
		<div class="centered">
			<form method="POST" action="" class="">
				<label class="form-group">
					<span class="input">Имя пользователя:</span>
					<input type="text" name="login" placeholder="Введите имя" required>
				</label>
				<label class="form-group">
					<span class="input">Пароль:</span>
					<input type="password" name="password" placeholder="Введите пароль" required>
				</label>
				<button>Вход</button>
				<button>Регистрация</button>
			</form>
		</div>
	</div>
	
	<script src="libs/jquery/jquery-2.1.4.min.js"></script>

	<script>
	$(document).ready(function() {

		$(".main").css("height", $(window).height());

	});
	</script>

</body>
</html>