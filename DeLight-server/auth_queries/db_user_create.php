<?php

if (isset($_POST["login"])&&isset($_POST["password"])){

	require '../db_connect.php';

	$db = new DB_CONNECT();
	
	$response = array();

	$login = $_POST["login"];
	$password = sha1($_POST["password"]);
	
	$result = $db->getConnection()->query("INSERT INTO users(login, password) VALUES('$login', '$password')");

	if (!empty($result)) {

		$result = $db->getConnection()->query("SELECT *FROM users WHERE login = '$login'");

        if ($result->num_rows > 0) {

        	$result = $result->fetch_array();

        	if ($password == $result["password"]){
        		$response["success"] = 1;
        		$response["message"] = "User successfully added";
        	}

			echo json_encode($response);
		} else {
			$response["success"] = 0;
			$response["message"] = "User not created";

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