<?php include "include/header.php" ?>

<div class="fullscreen wrapper">
	<div class="centered user-area-wrapper">
		<div class="user-area">
			<div class="menu violet-style">
				<div class="tab-button">
					<span class="icon-basic-todo-txt"></span>
					<span class="menu-item">Расписание</span>
				</div>
				<div class="tab-button">
					<span class="menu-item">Выход</span>
					<span class="fa fa-sign-out"></span>
				</div>
			</div>
			<div class="schedule-container violet-style">
				<?php include "include/events.php" ?>
			</div>
		</div>
	</div>
</div>

<?php include "include/footer.php" ?>