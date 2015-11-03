<?php

require $_SERVER['DOCUMENT_ROOT']."/"."vote/vote_config.inc";
if (!mysql_connect($host, $user, $password))
{
	echo "<h3>MySQL Error!</h3>";
	echo "<small>".mysql_errno().mysql_error()."</small>";
	exit;
}

if (isset($_SERVER["HTTP_REFERER"]) & !empty($_SERVER["HTTP_REFERER"])) {
	$referer = $_SERVER["HTTP_REFERER"];
}
else {
	$referer = 'null';}
if (isset($_GET['update'])) {#) && (preg_match("/vpic.php/",$referer))) {
	mysql_select_db($db);
	$pic_out = mysql_query('SELECT `id`, `path`,`rate` FROM `pictures` order by rate desc');
	while ($result = mysql_fetch_array($pic_out)) {
		if (file_exists($_SERVER['DOCUMENT_ROOT']."/".$result['path'])) {
			echo "<div class='votecss'><span class='vote_text_css'>vote:".$result['rate']."</span>";
			echo "<span class='photocss'><img class='cboxElement imgc' rel='colorbox' href='/".$result['path']."' src='/".$result['path']."' ></span>";
			echo '<span class="formcss"><form name="f_vote" class="f_vote" id="f_vote"><input type="hidden" id="photo" name="photo" value="'.$result['path'].'"><input type="button" id="sub" value="Голосовать"></form></div></span>';
		}}
		exit;
	}

	if (isset($_GET['push']) or isset($_POST['push'])) {


	}


	function ls($files) {
		$result = array();
		for ($i = 0; $i < count($files); $i++) {
			if ($files[$i] != "." && $files[$i] != "..") $result[] = $files[$i];
		}
		return $result;
	}
	$files = scandir($_SERVER['DOCUMENT_ROOT']."/".$dir);
	$files = ls($files);

	if (isset($_SERVER["HTTP_REFERER"]) & !empty($_SERVER["HTTP_REFERER"])) {
		$referer = $_SERVER["HTTP_REFERER"];
if ((isset($_POST['photo']) & !empty($_POST['photo']))) {#&& preg_match("/vpic.php/",$referer))) {
	$photo = $_POST['photo'];

	function check_and_update($_c) {

		global $db,$photo,$error_msg,$error_msg_tw,$msg_success,$error_not_allowed;
		if ($_c == 'null' ) {
			$_u = $_COOKIE['yandexuidm'];
		}
		else {$_u = $_c;}

		if (!mysql_select_db($db)) {
			echo mysql_errno().mysql_error(); 
			exit;
		}
		$time = mysql_query("SELECT `valid_time` FROM `users` WHERE user='".$_u."'");
		$u_time = mysql_fetch_array($time, 'valid_time');
		if (!empty($u_time)) {
			if ((time() - $u_time) < 86400) {
				$current_photos = mysql_query("SELECT `photos` FROM `users` WHERE user='".$_u."'");
				$photo_from_db = mysql_fetch_array($current_photos, 'photos');
				$current_rate = mysql_query("SELECT `rate` FROM `pictures` WHERE path='".$photo."'");
				$rate = (int) mysql_fetch_array($current_rate, 'rate');
				$photo_ereg = preg_replace("/\//","\/",$photo);
				$total_photo_count = count(explode(";",$photo_from_db));
				$photo_arr = explode(";",$photo_from_db);
				preg_match_all("/$photo_ereg/",$photo_from_db,$match_curphoto);
				if ($total_photo_count == 4 ) {
					echo $error_msg;
				}

				elseif (($total_photo_count == 1) or ($total_photo_count == 2)) {
					mysql_query("UPDATE `users` SET `photos`='".$photo.";".$photo_from_db."' WHERE user='".$_u."'");
					mysql_query("UPDATE `pictures` SET `rate`='".($rate+1)."' WHERE path='".$photo."'");
					echo $msg_success;
				}
				elseif ($total_photo_count == 3) {
					if ((($photo_arr[0] == $photo_arr[1]) and (($photo != $photo_arr[0]) or ($photo != $photo_arr[1]))) or (($photo_arr[0] != $photo_arr[1]) and (($photo == $photo_arr[0]) or ($photo == $photo_arr[1])))) {
						mysql_query("UPDATE `users` SET `photos`='".$photo.";".$photo_from_db."' WHERE user='".$_u."'");
						mysql_query("UPDATE `pictures` SET `rate`='".($rate+1)."' WHERE path='".$photo."'");
						echo $msg_success;
					}
					elseif (($photo_arr[0] == $photo_arr[1]) and (($photo == $photo_arr[0]) or ($photo == $photo_arr[1]))) {
						echo $error_msg_tw;
					}
					else {
						echo $error_not_allowed;
					}
				}
			}
			elseif ((time() - $u_time) > 86400) {
				mysql_query("UPDATE `users` SET `valid_time`='".time()."' WHERE user='".$_u."'");
			}
		}
		else {
			$_c = uniqid();
			$_t = time()+86400;
			setCookie("yandexuidm",$_c,$_t);
			mysql_select_db($db);
			mysql_query("INSERT INTO `users` (`user`, `valid_time`) VALUES ('".$_c."',".$_t.")");
			check_and_update($_c);
		}
	}

	if (isset($_COOKIE['yandexuidm'])) {
		check_and_update($_COOKIE['yandexuidm']);
	} 
	elseif (!isset($_COOKIE['yandexuidm'])) {
		$_c = uniqid();
		$_t = time()+86400;
		setCookie("yandexuidm",$_c,$_t);
		mysql_select_db($db);
		mysql_query("INSERT INTO `users` (`user`, `valid_time`) VALUES ('".$_c."',".$_t.")");
		check_and_update($_c);}
	}
	exit;
} 

mysql_select_db($db);
$pics = mysql_query('SELECT `id`, `path`,`rate` FROM `pictures`');
$photo_array = array();
$file_array = array();
for ($i = 0; $i < count($files); $i++) {
	$file_array[] = $dir."/".$files[$i];
}

while ($row = mysql_fetch_array($pics)) { 
	$photo_array[] = $row['path'];
}
$diff = array_diff($file_array,$photo_array);
if (count($diff) != 0) {
	foreach($diff as $value) {
		mysql_query("INSERT INTO `pictures` (`path`, `rate`) VALUES ('".$value."','0')");
	}
}

echo <<<END
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>;
<script type="text/javascript" src="/vote/js/jquery.colorbox-min.js"></script>
<script type="text/javascript" src="/vote/js/jquery.bpopup.min.js"></script>
<link rel="stylesheet" type="text/css" href="/vote/css/colorbox.css">
<link rel="stylesheet" type="text/css" href="/vote/css/popup.css">
END;
echo "<div id='vote_picture'>";
$pic_out = mysql_query('SELECT `id`, `path`,`rate` FROM `pictures` order by rate desc');
while ($result = mysql_fetch_array($pic_out)) {
	if (file_exists($_SERVER['DOCUMENT_ROOT']."/".$result['path'])) {
		echo "<div class='votecss'><span class='vote_text_css'>vote:".$result['rate']."</span>";
		echo "<span class='photocss'><img class='cboxElement imgc' rel='colorbox' href='/".$result['path']."' src='/".$result['path']."' ></span>";
		echo '<span class="formcss"><form name="f_vote" class="f_vote" id="f_vote"><input type="hidden" id="photo" name="photo" value="'.$result['path'].'"><input type="button" id="sub" value="Голосовать"></form></div></span>';
	}}
	echo "</div>";
	echo <<<END
	<div id="popup" >
	<span id="msg_vote"></span>
	</div>
	<script async type="text/javascript" src="/vote/js/vote2.js"></script>
	<script>
	$(document).on('click', '.f_vote', function(e){
		var form = $(this);e.preventDefault();var m_data=$(this).serialize();
		var h=document.location.host;
		$.ajax({type: "post",url: "http://"+h+"/vote/vpic.php",dataType: "html",data: m_data,
			success: function(m_data){console.log(m_data);
				$('#msg_vote').text(m_data);
				jQuery('#popup').bPopup({autoClose: 2000});},
				error: function (xhr, ajaxOptions, thrownError) {
					console.log(msg);
					console.log(xhr.status);
					console.log(thrownError);},
					complete: function(m_data) {
						console.log(m_data);
					}});});
$("img[rel='colorbox']").colorbox({rel: 'colorbox'});</script>
END;
?>