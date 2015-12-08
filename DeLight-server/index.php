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
	<link rel="stylesheet" href="libs/animate/animate.min.css" />
	<link rel="stylesheet" href="libs/linea/styles.css" />
	<link rel="stylesheet" type="text/css" href="css/fonts.css" />
	<link rel="stylesheet" type="text/css" href="css/style.css" />
	<link  href="http://cdnjs.cloudflare.com/ajax/libs/fotorama/4.6.4/fotorama.css" rel="stylesheet">

</head>
<body>

	<div class="loader">
		<div class="loader_inner"></div>
	</div>

	<header class="main_head" data-parallax="scroll" data-image-src="img/gallery/image9.jpg">
		<div class="row">
			<div class="col-md-12">
				<nav class="top_menu">
					<ul>
						<li><a href="#about">О нас</a></li>
						<li><a href="#media">Фото и видео</a></li>
						<li><a href="#order">Заказ шоу</a></li>
						<li class="dropdown">
							<a href="#auth">Авторизация</a>
							<ul>
								<li>
									<a href="login">Вход</a>
								</li>
								<li>
									<a href="register">Регистрация</a>
								</li>
							</ul>
						</li>
					</ul>
				</nav>
			</div>
		</div>

		
		<div class="container">
			<div class="video_promo">
				<iframe width="550" height="345" src="https://www.youtube.com/embed/k5kmjb0Eq1Y" frameborder="0" allowfullscreen></iframe>
			</div>
			<div class="order_form">
				<form method="POST" action="http://formspree.io/scrd.cha@gmail.com" class="main-form">
					<label class="form-group">
						<span class="input">Ваше имя:</span>
						<input type="text" name="name" placeholder="Имя (обязательно)" required>
					</label>
					<label class="form-group">
						<span class="input">Ваш телефон:</span>
						<input type="text" name="phone" placeholder="Телефон (обязательно)" required>
					</label>
					<label class="form-group">
						<span class="input">Ваш E-mail:</span>
						<input class="no_required"type="email" name="e-mail" placeholder="E-mail">
					</label>
					<label class="form-group">
						<span class="input">Ваше сообщение:</span>
						<textarea class="no_required" name="message" placeholder="Сообщение"></textarea>
					</label>
					<input type="hidden" name="_cc" value="v-i-v-y@mail.ru" />
					<input type="hidden" name="_cc" value="slashev27@yandex.ru" />
					<button>Заказать шоу</button>
				</form>
			</div>
		</div>
	</header>
<!--
	<section id="about" class="s_about s_bg">
		<div class="section_header animated">
			<h2>О нас</h2>
			<div class="s_desc_wrap">]
				<div class="s_descr">
					<span >Шоу-группа</span> <span>DeLight</span>
				</div>
			</div>
		</div>

		<div class="section_content  animated">
			<div class="container">
				<div class="row">
					<div class="col-md-4">
						<h3>Шоу-группа DeLight</h3>
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ullam repellendus velit dolore ab explicabo hic unde error, recusandae laborum! Autem, eligendi aspernatur alias quasi accusantium fugiat impedit, voluptatibus necessitatibus id?</p>
					</div>
					<div class="col-md-8">
						<div class="col-md-6 show_item"><img src="img/gallery/image9.jpg" alt=""></div>
						<div class="col-md-6 show_item"><img src="img/gallery/image13.jpg" alt=""></div>
					</div>
				</div>
			</div>
		</div>
	</section>

-->
<section id="media" class="s_media s_light_bg">
	<div class="section_header animated">
		<h2>Фото и видео</h2>
	</div>

	<div class="section_content animated">
		<div class="container">
			<div class="tabs_block">
				<ul class="tabs">
					<li class="active">Фото</li>
					<li>Видео</li>
				</ul>
				<div class="box visible"> 
					<div class="fotorama" data-nav="thumbs" data-width="100%"
					data-loop="true" data-keyboard="true" data-allowfullscreen="true">
					<img src="img/gallery/image15.jpg">
					<img src="img/gallery/image2.jpg">
					<img src="img/gallery/image6.jpg">
					<img src="img/gallery/image7.jpg">
					<img src="img/gallery/image4.jpg">
					<img src="img/gallery/image8.jpg">
					<img src="img/gallery/image10.jpg">		
					<img src="img/gallery/image11.jpg">
					<img src="img/gallery/image14.jpg">
				</div>
			</div>
			<div class="box">
				<div class="fotorama" data-nav="thumbs" data-width="100%"
				data-loop="true" data-keyboard="true" data-allowfullscreen="true">
				<a href="http://www.youtube.com/watch?v=y1N6n63ogk0"></a>
				<a href="http://www.youtube.com/watch?v=mGe_0wqq85g"></a>
				<a href="http://www.youtube.com/watch?v=SHNqAklAQLs"></a>
				<a href="http://www.youtube.com/watch?v=b0o5x6AFkyE"></a>
			</div>
		</div>
	</div>
</div>
</div>
</section>

	<!--
	<section id="order" class="s_contacts s_bg">
		<div class="section_header animated">
			<h2>Заказ шоу</h2>
			<div class="s_desc_wrap">
				<div class="s_descr">
					<span class="upper">Шоу-группа</span> <span>DeLight</span>
				</div>
			</div>
		</div>

		<div class="section_content animated">
			<div class="container">
				<div class="row">
					<div class="col-md-6">
						<div class="contact-box">
							<div class="contacts-icon icon-basic-smartphone"></div>
							<h3>Телефон:</h3>
							<p>+7-924-442-73-02 - Артем</p>
						</div>
						<div class="contact-box">
							<div class="contacts-icon icon-basic-smartphone"></div>
							<h3>Телефон:</h3>
							<p>+7-914-399-38-50 - Виктория</p>
						</div>
					</div>
					<div class="col-md-6">
						<form method="POST" action="http://formspree.io/scrd.cha@gmail.com" class="main-form">
							<label class="form-group">
								<span class="color-element">* </span>Ваше имя:
								<input type="text" name="name" placeholder="Имя" required>
							</label>
							<label class="form-group">
								<span class="color-element">* </span>Ваш телефон:
								<input type="text" name="name" placeholder="Телефон" required>
							</label>
							<label class="form-group">
								&nbsp;&nbsp;&nbsp;Ваш E-mail:
								<input type="email" name="e-mail" placeholder="E-mail">
							</label>
							<label class="form-group">
								<span class="color-element">* </span>Ваше сообщение:
								<textarea name="message" id="" placeholder="Сообщение" required></textarea>
							</label>
							<button>Отправить</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
<footer class="main_footer s_dark_bg">
	<div class="container">
		<div class="col-md-12">
			&copy; 2015 DeLight
		</div>
	</div>
</footer>
-->

<script src="libs/jquery/jquery-2.1.4.min.js"></script>
<script src="libs/waypoints.min.js"></script>
<script src="libs/parallax/parallax.min.js"></script>
<script src="libs/animate/animate-css.js"></script>
<script src="libs/scroll2id/PageScroll2id.min.js"></script>
<script src="libs/fotorama/fotorama.js"></script>
<script type="text/javascript" src="js/common.js"></script>

</body>
</html>