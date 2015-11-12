<?php
 
require '../db_connect.php';

$db = new DB_CONNECT();

if (isset($_POST["login"])){
	
	$response = array();

	$login = $_POST["login"];
	
	$result = $db->getConnection()->query("SELECT *FROM users WHERE login = '$login'");


	if (!empty($result)) {
        if ($result->num_rows > 0) {

        	$result = $result->fetch_array();

        	if (sha1($_POST["password"]) == $result["password"]){
        		$response["success"] = 1;

        		$user = array();
        		$user["first_name"] = $result["first_name"];
        		$user["last_name"] = $result["last_name"];

        		$response["user"] = array();
 
            	array_push($response["user"], $user);
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