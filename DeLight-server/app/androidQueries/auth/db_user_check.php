<?php

require_once '../../../db/db_connect.php';

if (isset($_POST["login"]) && isset($_POST["password"])){

	$db = new DB_CONNECT();

	$login = $_POST["login"];
	$password = $_POST["password"];
	
	$response = array();

	$login = $_POST["login"];
	
	$result = $db->getConnection()->query("SELECT * FROM users WHERE login = '$login'");

	if (!empty($result)) {
		if ($result->num_rows > 0) {

			$result = $result->fetch_array();
			$salt = $result["salt"];

			$hash = md5(md5($password . md5(sha1($salt))));

			$iterations = 10;

			for ($i = 0; $i < $iterations; ++$i) {
				$hash = md5(md5(sha1($hash)));
			}

			if ($hash == $result["password"]){
				$response["success"] = 1;

				$response["user"] = array();

				$response["user"]["first_name"] = $result["first_name"];
				$response["user"]["last_name"] = $result["last_name"];
				$response["user"]["user_id"] = $result["user_id"];
			} else {
				$response["success"] = 0;
				$response["salt"] = $salt;
				$response["password"] = $hash;
				$response["message"] = "Wrong password";
			}

			echo json_encode($response);
			
		} else {
			$response["success"] = 0;
			$response["message"] = "User not found";

			echo json_encode($response);
		}
	} else {
		$response["success"] = 0;
		$response["message"] = "Empty result" ;

		echo json_encode($response);
	}
} else {
	$response["success"] = 0;
	$response["message"] = "Required field(s) is missing";

	echo json_encode($response);
}
?>