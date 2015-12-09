<?php include "include/header.php" ?>

	<div class="fullscreen">
		<div class="centered small-form violet-style">
			<form method="POST" action="authorization.php" class="main-form">
				<label class="form-group">
					<span class="input">Логин:</span>
					<input type="text" class="js-check-password" name="username" placeholder="Введите логин">
				</label>
				<label class="form-group">
					<span class="input">Пароль:</span>
					<input type="password" class="js-check-password" name="password" placeholder="Введите пароль">
				</label>
				<label class="form-group">
					<span class="input">Подтвердите пароль:</span>
					<input type="password" name="password_confirm" placeholder="Введите пароль">
				</label>
				<input type="hidden" value="register" name="from">
				<button class="login-button" type="button" id="register">Регистрация</button>
				<p class="already-registered">
					<span>Уже зарегистрированы?</span><a href="login">Войти</a>
				</p>
			</form>
		</div>
	</div>
	
	<script type="text/javascript" charset="utf-8" src="../libs/jquery.tubular.1.0.1/js/jquery.tubular.1.0.js"></script> 
	<script>
	$(document).ready(function() {

		$('.fullscreen').tubular({videoId: 'k5kmjb0Eq1Y'});

	});
	</script>
	<script src="js/registration.js"></script>

<?php include "include/footer.php" ?>