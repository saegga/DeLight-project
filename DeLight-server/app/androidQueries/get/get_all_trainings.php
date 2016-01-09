<?php

require_once $_SERVER['DOCUMENT_ROOT'].'/db/db_connect.php';

$db = new DB_CONNECT();

    $response = array();

    
    $result = $db->getConnection()->query("SELECT *FROM trainings");
    if (!empty($result)) {
        if ($result->num_rows > 0) {
			$training = array();
			while($raw = mysqli_fetch_object($result)){
			$training[] = $raw;		 
            }	    
		    $response["training"] = array();
            array_push($response["training"], $training);
        } else {
            $response["success"] = 1;
            $response["message"] = "Empty result";
        }
    } else {
			$response["success"] = 0;
			$response["message"] = "mysql error";
    }        
        echo json_encode($response);

?>