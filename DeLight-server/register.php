<?php
session_start();
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
		<div class="centered big-form">
			<form method="POST" action="authorization.php" class="main-form">
				<div class="side">
					<label class="form-group">
						<span class="input">Имя пользователя:</span>
						<input type="text" name="login" placeholder="Введите имя" required>
					</label>
					<label class="form-group">
						<span class="input">Пароль:</span>
						<input type="password" name="password" placeholder="Введите пароль" required>
					</label>
					<label class="form-group">
						<span class="input">Подтвердите пароль:</span>
						<input type="password" name="password_confirm" placeholder="Введите пароль" required>
					</label>
				</div>
				<div class="side">
					<label class="form-group">
						<span class="input">Имя:</span>
						<input type="text" name="first_name" placeholder="Введите имя">
					</label>
					<label class="form-group">
						<span class="input">Фамилия:</span>
						<input type="text" name="last_name" placeholder="Введите фамилию">
					</label>
					<label class="form-group">
						<span class="input">Номер телефона:</span>
						<input type="text" name="phone" placeholder="Введите телефон">
					</label>
					<input type="hidden" name="from" value="register">
				</div>
				<button class="register-button" type="submit" id="register">Регистрация</button>
			</form>
		</div>
	</div>
	
	<script src="libs/jquery/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" charset="utf-8" src="libs/jquery.tubular.1.0.1/js/jquery.tubular.1.0.js"></script> 

	<script>
	$(document).ready(function() {

		$(".main").css("height", $(window).height());

		$('#register').click(function(){
			location.href = "register.php";
		});

	});
	</script>

</body>
</html>