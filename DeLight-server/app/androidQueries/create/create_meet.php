<?php

require_once $_SERVER['DOCUMENT_ROOT'].'/db/db_connect.php';

$db = new DB_CONNECT();

$response = array();

$json = $_POST["json"];

if (isset($_POST["json"])){
    $json = json_decode($_POST["json"], true);
	
    $place_id = $json["place_id"];
    $date = $json["date"];
    $name = $json["name"];
    $start_time = $json["start_time"];
    $end_time = $json["end_time"];

    $db->getConnection()->query("INSERT INTO meetings(place_id, name,date, start_time, end_time) "
        ."VALUES('$place_id', '$name', '$date', '$start_time', '$end_time')");
    $response["success"] = 1;

} else{
    $response["success"] = 0;
}

echo json_encode($response);

?>