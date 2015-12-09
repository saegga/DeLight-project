<?php

require_once $_SERVER['DOCUMENT_ROOT'].'/db/db_connect.php';

//$user_id = $_SESSION["user_id"];

//if ($user_id){

	$query = "select login from users where login = '$user_id'";

	$db = new DB_CONNECT();

	$result = $db->getConnection()->query($query);
	$result = $result->fetch_array();

	if ($user_id == $result["login"]){
		$query = "select * from trainings";

		$result = $db->getConnection()->query($query);

		for ($i = 0; $i < $result->num_rows; $i++){
			$row = $result->fetch_array();
			echo '<div class="schedule-row"><div class="vert-divider"></div>' 
			.'<span class="dayofweek">'.$row["dayOfWeek"].' '.$row["start_time"]
			.' - '.$row["end_time"].'</span>'
			.'</div>'."\n";
		}
	}
//} else {
	//echo '<p class="autorization-error">Для доступа к данной странице, необходимо авторизироваться.</p>';
//}

?>