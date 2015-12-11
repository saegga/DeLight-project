<?php
 
require_once $_SERVER['DOCUMENT_ROOT'].'/db/db_connect.php';

$db = new DB_CONNECT();

if (isset($_POST["key_value"])){
	
	$response = array();

	$key = $_POST["key_value"];
	
	$result = $db->getConnection()->query("SELECT *FROM register_key WHERE key_value = sha1($key)");

	if (!empty($result)) {
        if ($result->num_rows > 0) {

        	$result = $result->fetch_array();

        	$response["success"] = 1;
        	$response["key_status"] = $result["free"];

			echo json_encode($response);
		} else {
			$response["success"] = 0;
			$response["message"] = "Key not found";

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