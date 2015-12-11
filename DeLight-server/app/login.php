<?php include "include/header.php" ?>

<div class="fullscreen">
	<div class="centered small-form violet-style">
		<form method="POST" action="authorization" class="">
			<label class="form-group">
				<span class="input">Логин:</span>
				<input class="js-check-login" id="username" type="text" name="login" placeholder="Введите логин">
			</label>
			<label class="form-group">
				<span class="input">Пароль:</span>
				<input class="js-check-password" id="password" type="password" name="password" placeholder="Введите пароль">
			</label>
			<button class="login-button" type="button" id="login">Вход</button>
			<button class="login-button" type="button" id="register">Регистрация</button>
			<input type="hidden" value="login" name="from">
		</form>
	</div>
</div>

<script type="text/javascript" charset="utf-8" src="../libs/jquery.tubular.1.0.1/js/jquery.tubular.1.0.js"></script> 

<script>
$(document).ready(function() {

	$('.fullscreen').tubular({videoId: 'k5kmjb0Eq1Y'});

});
</script>
<script src="js/autorization.js"></script>

<?php include "include/footer.php" ?>