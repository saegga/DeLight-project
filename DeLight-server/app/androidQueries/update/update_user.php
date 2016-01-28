<?php

require_once $_SERVER['DOCUMENT_ROOT'].'/db/db_connect.php';

$db = new DB_CONNECT();

$response = array();


	if(isset($_POST["user_id"]) && isset($_POST["first_name"]) && isset($_POST["last_name"]) && isset($_POST["phone"])){
		$id = $_POST["user_id"];
		$first_name = $_POST['first_name'];
		$last_name = $_POST['last_name'];
		$phone = $_POST['phone'];
		
		$result = $db->getConnection()->query("UPDATE users SET 
		first_name='$first_name', 
		last_name='$last_name', 
		phone='$phone' where user_id='$id'");

		if ($result) {
			$response['success'] = 1; 
	}else{
			$response["success"] = 0;
			$response["message"] = "empty result";
		 }
}else{
		$response["success"] = 0;
	    $response["message"] = "Require field(S) is missing";
}
	echo json_encode($response);
	?>