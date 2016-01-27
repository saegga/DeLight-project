<?php

require_once $_SERVER['DOCUMENT_ROOT'].'/db/db_connect.php';


$response = array();


	if(isset($_POST["user_id"])){
	
		$db = new DB_CONNECT();
		$id = $_POST["user_id"];
		$response = array();
		$result = $db->getConnection()->query("SELECT * FROM users where user_id = '$id'");

	if (!empty($result)) {
		$response['user'] = array();
		 if ($result->num_rows > 0) {
			
			$user_row = $result->fetch_array();
			
			$user['user_id'] = $user_row['user_id'];
			$user['login'] = $user_row['login'];
			$user['first_name'] = $user_row['first_name'];
			$user['last_name'] = $user_row['last_name'];
			$user['role'] = $user_row['role'];
			$user['phone'] = $user_row['phone'];
			
			$response['user'] = $user;
			
			$response['success'] = 1;
			//array_push($response["user"], $user);
		
		 }else{
			$response["success"] = 0;
			$response["message"] = "empty result";
		 }
	}
	else {
	   $response["success"] = 0;
	   $response["message"] = "mysql error";
	}
}else{
		$response["success"] = 0;
	    $response["message"] = "Require field(S) is missing";
}
	echo json_encode($response);
	?>