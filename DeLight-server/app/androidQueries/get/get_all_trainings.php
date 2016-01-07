<?php

require_once $_SERVER['DOCUMENT_ROOT'].'/db/db_connect.php';

$db = new DB_CONNECT();

    $response = array();

    $training_id = $_POST['training_id'];
    
    $result = $db->getConnection()->query("SELECT *FROM trainings");
    if (!empty($result)) {
        if ($result->num_rows > 0) {
           $training = array();
           while($raw = mysqli_fetch_row($result)){
				
            
            $training["training_id"] = $raw["training_id"];
            $training["name"] = $raw["name"];
            $training["owner_login"] = $raw["owner_login"];
            $training["time"] = $raw["time"];
            $training["dayOfWeek"] = $raw["dayOfWeek"];
            $training["agenda"] = $raw["agenda"];
            $response["success"] = 1;
            $training[] = $raw;
            
            }
	    $response["training"] = array();
            array_push($response["training"], $training);
            echo json_encode($response);
        } else {
            $response["success"] = 0;
            $response["message"] = "Trainings not found";
            
            echo json_encode($response);
        }
    } else {
        $response["success"] = 0;
        $response["message"] = "Empty result";
        
        echo json_encode($response);
    }

?>