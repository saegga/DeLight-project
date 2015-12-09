<?php include "include/header.php" ?>

	<div class="fullscreen">
		<div class="centered small-form">
			<form method="POST" action="authorization.php" class="main-form">
				<label class="form-group">
					<span class="input">Имя пользователя:</span>
					<input type="text" name="username" placeholder="Введите имя">
				</label>
				<label class="form-group">
					<span class="input">Пароль:</span>
					<input type="password" name="password" placeholder="Введите пароль">
				</label>
				<label class="form-group">
					<span class="input">Подтвердите пароль:</span>
					<input type="password" name="password_confirm" placeholder="Введите пароль">
				</label>
				<input type="hidden" value="register" name="from">
				<button class="login-button" type="button" id="register">Регистрация</button>
			</form>
		</div>
	</div>
	
	<script src="../libs/jquery/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" charset="utf-8" src="../libs/jquery.tubular.1.0.1/js/jquery.tubular.1.0.js"></script> 

	<script>
	$(document).ready(function() {

		$('.fullscreen').tubular({videoId: 'k5kmjb0Eq1Y'});

	});
	</script>

	<script src="../libs/toastr/toastr.min.js"></script>
	<script src="js/interface.js"></script>
	<script src="js/registration.js"></script>

<?php include "include/footer.php" ?>