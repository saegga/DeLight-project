<?php
	require_once $_SERVER['DOCUMENT_ROOT'].'/db/db_connect.php';

	$db = new DB_CONNECT();

    $response = array(); 
	
	$result = $db->getConnection()->query("select *from trainings");
		 if (!empty($result)) {
			if ($result->num_rows > 0) {
			while($raw = mysqli_fetch_object($result)){
				
				$training[] = $raw;
			}
			$response['training'] = array();
			array_push($response['training'], $training);
		}
	}else{
		$response["success"] = 0;
        $response["message"] = "error mysql query";	
	}
	$result = $db->getConnection()->query("SELECT *FROM `show`");
	 if (!empty($result)) {
	
			if ($result->num_rows > 0) {
			$show = array();
			
			while($raw = mysqli_fetch_object($result)){
				$show[] = $raw;
			}
			$response['show'] = array();
			array_push($response['show'], $show);
		}
			
	}else{
		$response["success"] = 0;
        $response["message"] = "error mysql query";
	}
	$result = $db->getConnection()->query("SELECT *FROM `meet`") or die(mysqli_error($db->getConnection()));
	 if (!empty($result)) {
	
			if ($result->num_rows > 0) {
			$meet = array();
			
			while($raw = mysqli_fetch_object($result)){
				$meet[] = $raw;
			}
			$response['meet'] = array();
			array_push($response['meet'], $meet);
		}
			$response["success"] = 1;
	}else{
		$response["success"] = 0;
        $response["message"] = "error mysql query";	
	}
	unset($db);
	echo json_encode($response);


?>