<?php

require_once $_SERVER['DOCUMENT_ROOT'].'/db/db_connect.php';

$db = new DB_CONNECT();

$response = array();

	if(isset($_POST['event_id'])){
		$id = $_POST['event_id'];
		$result = $db->getConnection()->query("SELECT s.name, s.date, s.description, s.start_time, s.end_time,
		p.name as p_name, p.address from  `shows` s, `places` p WHERE s.event_id = '$id' AND s.place_id = p.place_id ");
		
		$get_performances = $db->getConnection()->query("SELECT p.name, p.description from performances p, show_performances sp, shows s where p.id=sp.perf_id and sp.show_id='$id'");
		
		$get_users = $db->getConnection()->query("select distinct u.first_name, u.last_name from users u, perf_member pf, performances p, show_performances sp where u.user_id=pf.user_id and pf.perf_id=p.id and sp.show_id=(select s.event_id from shows s where s.event_id='$id')");
		 if (!empty($result)) {
			$response['show'] = array();
			$response['performances'] = array();
			$response['users'] = array();
			if($result->num_rows > 0){
				$show_row = $result->fetch_array();
				
				$show['name'] = $show_row['name'];
				$show['date'] = $show_row['date'];
				$show['description'] = $show_row['description'];
				$show['start_time'] = $show_row['start_time'];
				$show['end_time'] = $show_row['end_time'];
						
				$response['show'] = $show;
				
				
				$response['success'] = 1;		
			}else{
				$response["success"] = 0;
				$response["message"] = "empty result";
			}
			while($perf_row = mysqli_fetch_array($get_performances)){
			 $performances['p_name'] = $perf_row['name'];	
			 $performances['description'] = $perf_row['description'];	
			 
			 $performances['users'] = $perf_row['us'];// сделать запрос на массив пользователей в номер
			 array_push($response['performances'],$performances);
			}
			
			while($user_row = mysqli_fetch_array($get_users)){
			 $users['first_name'] = $user_row['first_name'];	
			 $users['last_name'] = $user_row['last_name'];	
			
			 array_push($response['users'],$users);
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