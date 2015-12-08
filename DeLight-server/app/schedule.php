<?php include "include/header.php" ?>

<div class="main schedule-wrapper">
	<div class="centered schedule-container ">
		<?php include "include/events.php" ?>
	</div>
</div>

<script src="../libs/jquery/jquery-2.1.4.min.js"></script>
<script type="text/javascript" charset="utf-8" src="../libs/jquery.tubular.1.0.1/js/jquery.tubular.1.0.js"></script> 

<script>
$(document).ready(function() {

	$(".main").css("height", $(window).height());

});
</script>

<script src="js/autorization.js"></script>

<?php include "include/footer.php" ?>