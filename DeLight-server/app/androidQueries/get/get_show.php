<?php

require_once $_SERVER['DOCUMENT_ROOT'].'/db/db_connect.php';

$db = new DB_CONNECT();

$response = array();

	if(isset($_POST['event_id'])){
		$id = $_POST['event_id'];
		$result = $db->getConnection()->query("SELECT s.name, s.date, s.description, s.start_time, s.end_time,
		p.name as p_name, p.address from  `shows` s, `places` p WHERE s.event_id = '$id' AND s.place_id = p.place_id ");
		
		 if (!empty($result)) {
			$response['show'] = array();
			if($result->num_rows > 0){
				$show_row = $result->fetch_array();
				
				//$show['event_id'] = $show_row['event_id'];
				$show['name'] = $show_row['name'];
				$show['date'] = $show_row['date'];
				$show['description'] = $show_row['description'];
				$show['start_time'] = $show_row['start_time'];
				$show['end_time'] = $show_row['end_time'];
				
				$show['p_name'] = $show_row['p_name'];
				$show['address'] = $show_row['address'];
				$response['show'] = $show;
				$response['success'] = 1;		
			}else{
				$response["success"] = 0;
				$response["message"] = "empty result";
			}
		 
		 }else{
		$response["success"] = 0;
		$response["message"] = "mysql error";
		 }
	
	}else{
	$response["success"] = 0;
    $response["message"] = "requered fileds is missing";
	}
echo json_encode($response);
?>