<?php
 
require_once $_SERVER['DOCUMENT_ROOT'].'/db/db_connect.php';

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

        	if (sha1($password) == $result["password"]){
        		$response["success"] = 1;

        		$response["user"] = array();

        		$response["user"]["first_name"] = $result["first_name"];
        		$response["user"]["last_name"] = $result["last_name"];

        	} else {
        		$response["success"] = 0;
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